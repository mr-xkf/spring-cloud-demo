/**
 * FileName: MethodValidatorConfig
 * Author:   13235
 * Date:     2019/9/14 14:24
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovider.config;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.client.RestTemplate;
import org.xkf.cloudprovider.service.ProxyRegister;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2019/9/14
 * @since 1.0.0
 */
@Configuration
public class MethodValidatorConfig {

    @Value("${my.scan.packages}")
    private String packages;

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    @Bean
    public ProxyRegister proxyRegister() {
        return new ProxyRegister("org.xkf.cloudprovider.service");
    }

    @Bean
    public RegexpMethodPointcutAdvisor regexpMethodPointcutAdvisor() {
        RegexpMethodPointcutAdvisor pointcutAdvisor = new RegexpMethodPointcutAdvisor();
        pointcutAdvisor.setAdvice((MethodInterceptor) methodInvocation -> null);
        pointcutAdvisor.setPattern("xxx");
        return pointcutAdvisor;
    }

    @Bean
    @Scope("prototype")
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        return restTemplate;
    }
}
