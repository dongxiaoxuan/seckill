package com.geek.ms.pojo.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Time {
	/*
	 * 有时候，我们对日期格式要做特殊的处理，全局的格式化方式无法满足我们需求是，
	 * 使用该方案是非常好的选择，通过 @JsonFormat 注解我们可以更为精准的
	 * 为日期字段格式化，它也是优先级最高的
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime time;
	
	public Time() {
		super();
	}

	public Time(LocalDateTime time) {
		super();
		this.time = time;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Time [time=" + time + "]";
	}
	
}
