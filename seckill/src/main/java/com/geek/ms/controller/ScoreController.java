package com.geek.ms.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.geek.ms.pojo.po.Years;
import com.geek.ms.pojo.vo.ScoreByYears;
import com.geek.ms.service.ScoreService;

@Controller
@RequestMapping("/score")
public class ScoreController {
	
	@Autowired
	private ScoreService scoreService;

	@RequestMapping
	public String into(Model model) {
		Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
		List<Years> years = scoreService.loadAllYears();
		List<ScoreByYears> scores = scoreService.queryScore(userId);
		model.addAttribute("years", years);
		model.addAttribute("scores", scores);
		return "score";
	}
	
}
