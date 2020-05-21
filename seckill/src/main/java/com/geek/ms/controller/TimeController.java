package com.geek.ms.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geek.ms.pojo.vo.Time;

@RestController
public class TimeController {

	@GetMapping("time")
	public Time query() {
		Time time = new Time();
		time.setTime(LocalDateTime.now());
		return time;
	}
}
