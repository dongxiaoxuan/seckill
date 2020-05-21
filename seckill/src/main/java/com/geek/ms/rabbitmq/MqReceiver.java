package com.geek.ms.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.geek.ms.pojo.vo.StudentCourseInfo;
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
			StudentCourseInfo s = BeanString.stringToBean(new String(message,"UTF-8"), StudentCourseInfo.class);
			System.out.println("消费端：" +s.getCouresInfoId()+ "," + s.getUserId());
			seckillCouresService.seckillCoures(s.getCouresInfoId(), s.getUserId());
			seckillCouresService.addOwnCourseCountListToRedis(s.getUserId());//当前学生当前选课周期选课量+1
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//高并发测试用例
	//@RabbitListener(queues = "${seckill.queue.name}", containerFactory = "singleListenerContainer")
	//public void receiveSeckill2(@Payload byte[] message) {
	//	try {
	//		String s = new String(message,"UTF-8");
	//		System.out.println(s);
	//	}catch (Exception e) {
	//		e.printStackTrace();
	//	}
	//	
	//}
	
}
