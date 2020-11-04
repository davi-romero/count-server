package prep.count.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prep.count.dto.RequestCount;
import prep.count.rabbitmq.RabbitMqConfig;

@Service
public class CountMessageServiceBean implements CountMessageService {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Override
	public void sendAggregateMessage(long requestId) {
		rabbitTemplate.convertAndSend(RabbitMqConfig.COUNT_TOPIC_EXCHANGE, 
				RabbitMqConfig.COUNT_AGGREGATE_ROUTER_KEY, requestId);
	}

	@Override
	public void sendSaveMessage(RequestCount requestCount) {
		rabbitTemplate.convertAndSend(RabbitMqConfig.COUNT_TOPIC_EXCHANGE, 
				RabbitMqConfig.COUNT_SAVE_ROUTER_KEY, requestCount);
	}
	
}
