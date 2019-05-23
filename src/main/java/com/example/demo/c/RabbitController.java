package com.example.demo.c;

import com.alibaba.fastjson.JSON;
import com.example.demo.e.Config;
import com.example.demo.e.VideoInfo;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 無痕剑
 * @date 2019/4/15 22:25
 */
@RestController
public class RabbitController {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@RequestMapping("/rabbit")
	public String rabbit() {
//		String msg1 = JSON.toJSONString(new VideoInfo("标题1", "分类1", "http://sd1.com", "2019-04-11 00:00:00"));
		String msg2 = JSON.toJSONString(new VideoInfo("标题2", "分类2", "http://sd2.com", "2019-04-22 00:00:00"));
//		rabbitTemplate.convertAndSend(Config.VIDEO_TOPIC_EXCHANGE, Config.VIDEO_SAVE, msg1);
		rabbitTemplate.convertAndSend(Config.VIDEO_TOPIC_EXCHANGE, Config.VIDEO_DOWNLOAD, msg2);
		return "success";
	}
}
