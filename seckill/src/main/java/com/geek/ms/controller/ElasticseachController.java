package com.geek.ms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.geek.ms.pojo.po.EsCourse;
import com.geek.ms.pojo.po.Page;

@Controller
@RequestMapping(value = "/search")
public class ElasticseachController {
	
	@Autowired
	private RestHighLevelClient client;
	   /**
	* 搜索
	*
	* @param model model
	* @param page  当前页码
	* @return 模板路径/themes/{theme}/index
	*/
	@RequestMapping
	   public String searchPage(Model model,
	            @RequestParam("keyword") String keyword) {
	   //默认显示20条
	   Integer size = 20;
	   //所有日志数据，分页
	   Page posts = new Page(0, size);
	  // Page<Post> posts = postService.searchByKeywords(HtmlUtil.escape(keyword), pageable);
	   //search request
	   SearchRequest searchRequest = new SearchRequest("blog");
	   //search builder
	   SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
	   sourceBuilder.query(QueryBuilders.matchQuery("name", keyword));
	   sourceBuilder.from(0);
	   sourceBuilder.size(size);
	   sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
	   //sort
	   sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
	   //highlight
	   HighlightBuilder highlightBuilder = new HighlightBuilder();
	   HighlightBuilder.Field highlightTitle = new HighlightBuilder.Field("name");
	   highlightTitle.preTags("<span class=\"highlight\">");
	   highlightTitle.postTags("</span>");
	   highlightBuilder.field(highlightTitle);
	   sourceBuilder.highlighter(highlightBuilder);
	   // add builder into request
	   searchRequest.indices("blog");
	   searchRequest.source(sourceBuilder);
	   //response
	   SearchResponse searchResponse = null;
	   try {
	       searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
	   } catch (IOException e) {
	       e.printStackTrace();
	   }
	   TimeValue took = searchResponse.getTook();
	   //search hits
	   SearchHits hits = searchResponse.getHits();
	   long totalHits = hits.getTotalHits();
	   SearchHit[] searchHits = hits.getHits();
	   List<EsCourse> postList = new ArrayList<>();
	   posts.setTotal((int) totalHits);
	   for (SearchHit hit : searchHits) {
	       String str = hit.getSourceAsString();
	       EsCourse esPost = JSONObject.parseObject(str, EsCourse.class);
	       Map<String, HighlightField> highlightFields = hit.getHighlightFields();
	       HighlightField highlight = highlightFields.get("name");
	       if (highlight != null) {
	           Text[] fragments = highlight.fragments();
	           String fragmentString = fragments[0].string();
	           esPost.setName(fragmentString);
	       }
	       postList.add(esPost);
	   }
	   posts.setRecords(postList);
	   model.addAttribute("is_index", true);
	   model.addAttribute("posts", posts);
	   model.addAttribute("prefix", "/search");
	   model.addAttribute("suffix", "?keyword=" + keyword);
	   model.addAttribute("time", took);
	   return "search";
	   }

}
