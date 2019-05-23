package com.example.demo.e;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 無痕剑
 * @date 2019/4/15 22:27
 */
@Configuration
public class Config {

	public final static String VIDEO_SAVE = "video.save";

	public final static String VIDEO_DOWNLOAD = "video.download";

	public final static String VIDEO_TOPIC_EXCHANGE = "video.topic.exchange";

	public final static String ROUTING_KEY = "video.#";

	@Bean("downloadQueue")
	public Queue downloadQueue() {
		return new Queue(VIDEO_DOWNLOAD);
	}

	@Bean("saveQueue")
	public Queue saveQueue() {
		return new Queue(VIDEO_SAVE);
	}

	@Bean("videoTopicExchange")
	TopicExchange videoTopicExchange() {
		return new TopicExchange(VIDEO_TOPIC_EXCHANGE);
	}

	@Bean
	Binding bindingExchangeSave(Queue saveQueue,
	                            TopicExchange videoTopicExchange) {
		return BindingBuilder.bind(saveQueue).to(videoTopicExchange).with(ROUTING_KEY);
	}

	@Bean
	Binding bindingExchangeDownload(Queue downloadQueue,
	                                TopicExchange videoTopicExchange) {
		return BindingBuilder.bind(downloadQueue).to(videoTopicExchange).with(ROUTING_KEY);
	}
}
