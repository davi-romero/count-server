package prep.count.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import prep.count.service.CountAggregatorService;

@RabbitListener(queues = RabbitMqConfig.COUNT_AGGREGATE_QUEUE, containerFactory = "prefetchContainerFactory")
public class CountAggregatorMessageReceiver {

	@Autowired
	private CountAggregatorService countAggregatorService;
	
	@RabbitHandler
	public void receiveMessage(Long requestId) {
		countAggregatorService.aggregate(requestId);
	}
	
}
