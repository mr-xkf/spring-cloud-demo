/**
 * FileName: RabbitMqConfig
 * Author:   13235
 * Date:     2019/10/1 13:57
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovidertwo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2019/10/1
 * @since 1.0.0
 */
@Configuration
public class RabbitMqConfig {


    /**
     *
     *  x-dead-letter-exchange
     *  x-dead-letter-routing-key
     *  x-message-ttl
     *
     */
    public static final String TEST_QUEUE = "test.queue";
    public static final String TEST_DIRECT_EXCAHNGE = "test.direct.exchange";
    public static final String TEST_TOPIC_EXCHANGE = "test.topic.exchange";
    public static final String TEST_FANOUT_EXCHANGE = "test.fanout.exchange";
    public static final String TEST_HEADER_EXCHANGE = "test.header.exchange";

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        ObjectMapper objectMapper = new ObjectMapper();
        Jackson2JsonMessageConverter messageConverter = new Jackson2JsonMessageConverter(objectMapper);
        rabbitTemplate.setMessageConverter(messageConverter);
        rabbitTemplate.afterPropertiesSet();
        return rabbitTemplate;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }
    @Bean
    public Queue testQueue() {
        return new Queue(TEST_QUEUE, true);
    }

    @Bean
    public Exchange directExchange() {
        return ExchangeBuilder.directExchange(TEST_DIRECT_EXCAHNGE).durable(true).build();
    }

    @Bean
    public Exchange topicExchange() {
        return ExchangeBuilder.topicExchange(TEST_TOPIC_EXCHANGE).durable(true).build();
    }

    @Bean
    public Exchange fanoutExchange() {
        return ExchangeBuilder.fanoutExchange(TEST_FANOUT_EXCHANGE).durable(true).build();
    }

    @Bean
    public Exchange headerExchange() {
        Map<String, Object> map = new HashMap<>();
        map.put("key", "key");
        map.put("key2", "key2");
        return ExchangeBuilder.headersExchange(TEST_HEADER_EXCHANGE).durable(true).withArguments(map).build();
    }

    @Bean
    public Binding bindingDirectExchange() {
        return BindingBuilder.bind(testQueue()).to(directExchange()).with("test").noargs();
    }

    @Bean
    public Binding bindingHeaderExchange() {
        return BindingBuilder.bind(testQueue()).to(headerExchange()).with("test.*").noargs();
    }

    @Bean
    public Binding bindingFanoutExchange() {
        return BindingBuilder.bind(testQueue()).to(fanoutExchange()).with("test.aa.bb").noargs();
    }

    @Bean
    public Binding bindingTopicExchange() {
        return BindingBuilder.bind(testQueue()).to(topicExchange()).with("test.topic.#").noargs();
    }


}
