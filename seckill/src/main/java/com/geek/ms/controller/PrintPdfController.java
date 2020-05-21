package com.geek.ms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.geek.ms.pojo.vo.Product;
import com.geek.ms.util.ViewPDF;


@Controller
@RequestMapping("/printPdf")
public class PrintPdfController {

    @RequestMapping
    public ModelAndView printPdf(String str, String courseName){
    	System.out.println("courseName" + courseName);
    	String[] strArr = str.split(";");
    	List<Product> products = new ArrayList<>();
    	for(int i = 0; i < strArr.length; ++ i) {
    		String[] strchildArr = strArr[i].split(",");
    		Product p = new Product();
    		p.setNumber(strchildArr[0]);p.setRealName(strchildArr[1]);p.setScore(strchildArr[2]);
    		products.add(p);
    	}
        Map<String, Object> model = new HashMap<>();
        model.put("courseName", courseName);
        model.put("sheet", products);
        return new ModelAndView(new ViewPDF(), model);
    }
}