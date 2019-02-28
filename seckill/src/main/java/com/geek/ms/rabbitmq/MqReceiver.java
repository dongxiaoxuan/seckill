package com.geek.ms.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.geek.ms.model.StudentCouresInfo;
import com.geek.ms.service.SeckillCouresService;
import com.geek.ms.util.BeanString;

@Service
public class MqReceiver {
	
	@Autowired
	private SeckillCouresService seckillCouresService;

	//private Logger logger = LoggerFactory.getLogger(MqReceiver.class);
	
	@RabbitListener(queues = "${seckill.queue.name}", containerFactory = "singleListenerContainer")
	public void receiveSeckill(@Payload byte[] message) {
		try {
			StudentCouresInfo s = BeanString.stringToBean(new String(message,"UTF-8"), StudentCouresInfo.class);
			System.out.println("消费端：" +s.getCouresInfoId()+ "," + s.getUserId());
			seckillCouresService.seckillCoures(s.getCouresInfoId(), s.getUserId());
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
