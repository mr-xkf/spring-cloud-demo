/**
 * FileName: ProxyRegister
 * Author:   13235
 * Date:     2019/9/16 23:52
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovider.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.xkf.cloudprovider.service.Impl.ServiceInterfaceScanner;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2019/9/16
 * @since 1.0.0
 */
@Slf4j
public class ProxyRegister implements BeanDefinitionRegistryPostProcessor {

    private String packages;

    public ProxyRegister(String packages) {
        this.packages = packages;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws
            BeansException {
    log.info("configurableListableBeanFactory:{}",configurableListableBeanFactory);

    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        log.info("BeanDefinitionRegistry:{}", beanDefinitionRegistry);
        ServiceInterfaceScanner scanner = new ServiceInterfaceScanner(beanDefinitionRegistry);
        scanner.scan(packages);
    }
}
