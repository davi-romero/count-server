package prep.count.rabbitmq;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

	public static final String COUNT_TOPIC_EXCHANGE = "count-exchange";
	public static final String COUNT_AGGREGATE_QUEUE = "count-aggregate-queue";
	public static final String COUNT_SAVE_QUEUE = "count-save-queue";
	public static final String COUNT_AGGREGATE_ROUTER_KEY = "aggregate.key";
	public static final String COUNT_SAVE_ROUTER_KEY = "save.key";

	@Bean
	Queue queue1() {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("x-queue-mode", "lazy");
		return new Queue(COUNT_AGGREGATE_QUEUE, true, false, false, args);
	}
	
	@Bean
	Queue queue2() {
		return new Queue(COUNT_SAVE_QUEUE, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(COUNT_TOPIC_EXCHANGE);
	}

	@Bean
	Binding bindingQueue1(Queue queue1, TopicExchange exchange) {
		return BindingBuilder.bind(queue1).to(exchange).with(COUNT_AGGREGATE_ROUTER_KEY);
	}
	
	@Bean
	Binding bindingQueue2(Queue queue2, TopicExchange exchange) {
		return BindingBuilder.bind(queue2).to(exchange).with(COUNT_SAVE_ROUTER_KEY);
	}
	
	@Bean
	public RabbitListenerContainerFactory<SimpleMessageListenerContainer> 
			prefetchContainerFactory(ConnectionFactory rabbitConnectionFactory) {
	    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
	    factory.setConnectionFactory(rabbitConnectionFactory);
	    factory.setMessageConverter(new Jackson2JsonMessageConverter());
	    factory.setPrefetchCount(2000);
	    return factory;
	}
	
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(producerJackson2MessageConverter());
		return template;
	}
	
	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
	    return new Jackson2JsonMessageConverter();
	}
	
	@Bean
    public CountAggregatorMessageReceiver aggregateReceiver1() {
        return new CountAggregatorMessageReceiver();
    }

	@Bean
    public CountSaverMessageReceiver saveReceiver1() {
        return new CountSaverMessageReceiver();
    }

}
