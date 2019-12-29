/**
 * FileName: MqMsgController
 * Author:   13235
 * Date:     2019/10/2 0:28
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovidertwo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder.Exclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xkf.cloudprovidertwo.mq.User;
import org.xkf.cloudprovidertwo.service.MqService;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2019/10/2
 * @since 1.0.0
 */
@RestController
@RequestMapping("/msg")
@Api(value = "rabbitMq消息服务")
public class MqMsgController {

    @Autowired
    private MqService mqService;


    @GetMapping("/sendMsg/{type}")
    @ApiOperation(value = "发送消息")
    @ApiResponse(code = 200, message = "成功！", response = String.class)
    public String sendMsg(@PathVariable("type") String type, @RequestParam("routingKey") String routingKey) {
        User user = new User();
        user.setUsername("hha");
        user.setPassword("xxxxx");
        mqService.sendMsg(user, type, routingKey);
        return "success!";
    }


    public static void main(String[] args) {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage
                ("org.xkf.cloudprovidertwo.controller"))
                .setScanners(new MethodAnnotationsScanner());
        configurationBuilder.setInputsFilter(new Exclude("application.properties"));
        Reflections reflections = new Reflections(configurationBuilder);
        Set<Method> methodsAnnotatedWith = reflections.getMethodsAnnotatedWith(ApiOperation.class);
        methodsAnnotatedWith.stream().forEach(m -> {
            Api annotation = m.getDeclaringClass().getAnnotation(Api.class);
            System.out.println("值是:" + annotation.value());
        });
    }
}
