package com.geek.ms.service.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.baidu.speech.restapi.common.DemoException;
import com.baidu.speech.restapi.ttsdemo.TtsMain;
import com.geek.ms.service.SpeechService;
import com.geek.ms.util.OpenWav;

@Service
public class SpeechServiceImpl implements SpeechService{

	@Override
	//@Async
	public void speech(String text) {
		try {
			TtsMain.speech(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DemoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OpenWav.speech();
	}

}
