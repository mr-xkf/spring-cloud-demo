/**
 * FileName: MqServiceImpl
 * Author:   13235
 * Date:     2019/10/2 0:31
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovidertwo.service.Impl;


import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.xkf.cloudprovidertwo.config.RabbitMqConfig;
import org.xkf.cloudprovidertwo.mq.User;
import org.xkf.cloudprovidertwo.service.MqService;

import java.io.IOException;
import java.util.UUID;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2019/10/2
 * @since 1.0.0
 */
@Service
@Slf4j
public class MqServiceImpl implements MqService {

    public static final String DIRECT = "direct";

    public static final String TOPIC = "topic";

    public static final String FANOUT = "fanout";

    public static final String HEADERS = "headers";

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Override
    public void sendMsg(User user, String type, String routingKey) {
        CorrelationData data = new CorrelationData();
        data.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        String exchangeName = RabbitMqConfig.TEST_DIRECT_EXCAHNGE;
        if (DIRECT.equals(type)) {
            exchangeName = RabbitMqConfig.TEST_DIRECT_EXCAHNGE;
        } else if (TOPIC.equals(type)) {
            exchangeName = RabbitMqConfig.TEST_TOPIC_EXCHANGE;
        } else if (FANOUT.equals(type)) {
            exchangeName = RabbitMqConfig.TEST_FANOUT_EXCHANGE;
        } else if (HEADERS.equals(type)) {
            exchangeName = RabbitMqConfig.TEST_HEADER_EXCHANGE;
        }
        rabbitTemplate.convertAndSend(exchangeName, routingKey, user, data);
    }

    @Override
    @RabbitListener(queues = {RabbitMqConfig.TEST_QUEUE})
    public void receviceMsg(@Payload User user, Message message, Channel channel) {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        System.out.println("接收到的消息:" + user.toString());
        try {
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
