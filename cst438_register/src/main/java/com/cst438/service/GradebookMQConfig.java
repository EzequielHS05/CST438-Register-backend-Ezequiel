package com.cst438.service;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
@ConditionalOnProperty(prefix = "gradebook", name = "service", havingValue = "MQ")
public class GradebookMQConfig  implements RabbitListenerConfigurer{
	

	@Bean
	public Queue gradebookQueue() {
		return new Queue("gradebook-queue", true);
	}
	
	@Bean
	public Queue registrationQueue() {
		return new Queue("registration-queue", true);
	}

	
	// converter rabbit template to convert objects to JSON 
	@Bean
	public RabbitTemplate rabbitTemplate( ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter( new Jackson2JsonMessageConverter() );
		return rabbitTemplate;
	}
	
	@Bean 
	public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
		return new MappingJackson2MessageConverter();
	}
	
	@Bean
	public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
		DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
		factory.setMessageConverter(consumerJackson2MessageConverter());
		return factory;
	}
	
	@Override
	public void configureRabbitListeners( RabbitListenerEndpointRegistrar registrar ) {
		registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
	}

}
