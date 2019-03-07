package com.geek.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.geek.ms.service.SpeechService;

@Controller
@RequestMapping("/speech")
public class SpeechController {

	@Autowired
	private SpeechService speechService;
	
	@RequestMapping("/score")
	@ResponseBody
	public String speech(String text) {
		speechService.speech(text);
		return "小助手载入成绩";
	}
	
}
