/**
 * FileName: MqService
 * Author:   13235
 * Date:     2019/10/2 0:29
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovidertwo.service;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.xkf.cloudprovidertwo.mq.User;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 13235
 * @create 2019/10/2
 * @since 1.0.0
 */
public interface MqService {

    void sendMsg(User user,String type,String routingKey);

    void receviceMsg(User user, Message message, Channel channel);

}
