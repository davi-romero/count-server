package prep.count.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;

import prep.count.dto.RequestCount;
import prep.count.service.CountService;

@RabbitListener(queues = RabbitMqConfig.COUNT_SAVE_QUEUE)
public class CountSaverMessageReceiver {

	@Autowired
	private CountService countService;
	
	@RabbitHandler
	public void receiveMessage(@Payload RequestCount requestCount) {
		System.out.println(requestCount);
		countService.increment(requestCount.getRequestId(), requestCount.getCount());
	}
	
}
