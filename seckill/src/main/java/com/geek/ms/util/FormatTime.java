package com.geek.ms.util;

import java.text.SimpleDateFormat;

public class FormatTime {

	public static String getFormatTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String[] nowArr = format.format(System.currentTimeMillis()).split("-");
		String year = nowArr[0];
		int month = Integer.parseInt(nowArr[1]);
		if(month >= 3 && month < 9) {
			return year + "-03-01";
		}else {
			return year + "09-01";
		}
	}
	
}
