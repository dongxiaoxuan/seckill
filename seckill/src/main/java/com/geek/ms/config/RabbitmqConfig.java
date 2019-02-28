package com.geek.ms.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


@Configuration
public class RabbitmqConfig {
	private static final Logger log= LoggerFactory.getLogger(RabbitmqConfig.class);
	
	@Autowired
	private Environment env;
	
	@Autowired
	private CachingConnectionFactory connectionFactory;
	
	@Autowired
	private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;
	
	/**
	 * 单一消费者
	 * @return
	 */
	@Bean(name = "singleListenerContainer")
	public SimpleRabbitListenerContainerFactory listenerContainer(){
	    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
	    factory.setConnectionFactory(connectionFactory);
	    factory.setMessageConverter(new Jackson2JsonMessageConverter());
	    factory.setConcurrentConsumers(1);
	    factory.setMaxConcurrentConsumers(1);
	    factory.setPrefetchCount(1);
	    factory.setTxSize(1);
	    factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
	    return factory;
	}
	
	/**
	 * 多个消费者
	 * @return
	 */
	@Bean(name = "multiListenerContainer")
	public SimpleRabbitListenerContainerFactory multiListenerContainer(){
	    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
	    factoryConfigurer.configure(factory,connectionFactory);
	    factory.setMessageConverter(new Jackson2JsonMessageConverter());
	    factory.setAcknowledgeMode(AcknowledgeMode.NONE);
	    factory.setConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.simple.concurrency",int.class));
	    factory.setMaxConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.simple.max-concurrency",int.class));
	    factory.setPrefetchCount(env.getProperty("spring.rabbitmq.listener.simple.prefetch",int.class));
	    return factory;
	}

	@Bean
	public RabbitTemplate rabbitTemplate(){
	    connectionFactory.setPublisherConfirms(true);
	    connectionFactory.setPublisherReturns(true);
	    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
	    rabbitTemplate.setMandatory(true);
	    rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
	        @Override
	        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
	            log.info("消息发送成功:correlationData({}),ack({}),cause({})",correlationData,ack,cause);
	        }
	    });
	    rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
	        @Override
	        public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
	            log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}",exchange,routingKey,replyCode,replyText,message);
	        }
	    });
	    return rabbitTemplate;
	}
	
	@Bean
	public Queue queue(){
		return new Queue(env.getProperty("seckill.queue.name"), true);
	}
	
	@Bean
	public FanoutExchange fanoutExchange(){
		return new FanoutExchange(env.getProperty("seckill.exchange.name"), true, false);
	}
	
	@Bean
	public Binding fanoutBind(){
		return BindingBuilder.bind(queue()).to(fanoutExchange());
	}
}
