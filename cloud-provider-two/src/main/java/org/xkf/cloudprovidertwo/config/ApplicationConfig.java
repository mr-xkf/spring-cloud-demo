/**
 * FileName: ApplicationConfig
 * Author:   13235
 * Date:     2019/10/22 16:42
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovidertwo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.xkf.cloudprovidertwo.controller.PropertiesFileConfig;
import org.xkf.cloudprovidertwo.entity.Dog;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2019/10/22
 * @since 1.0.0
 */
@Configuration
@ComponentScan(value = "org.xkf.cloudprovidertwo",
        excludeFilters = {@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
                MyFilterType.class, RabbitMqConfig.class, PropertiesFileConfig.class
        })})
@Slf4j
public class ApplicationConfig {

    @Bean
    public Dog dog() {
        log.info("dog开始创建对象");
        Dog dog = new Dog();
        dog.setAge(12);
        return dog;
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new
                AnnotationConfigApplicationContext(ApplicationConfig.class);
        //Object bean = applicationContext.getBean("applicationConfig");
        Dog dog = (Dog) applicationContext.getBean("dog");
        dog.say();
        System.out.println(dog.toString());
        applicationContext.close();

    }
}
