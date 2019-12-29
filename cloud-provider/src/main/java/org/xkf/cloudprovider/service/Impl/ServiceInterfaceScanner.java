/**
 * FileName: ServiceInterfaceScanner
 * Author:   13235
 * Date:     2019/9/16 21:46
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovider.service.Impl;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.AnnotationMetadata;
import org.xkf.cloudprovider.service.BaseService;

import java.util.Arrays;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br>
 * 定义接口扫描类
 *
 * @author 13235
 * @create 2019/9/16
 * @since 1.0.0
 */
public class ServiceInterfaceScanner extends ClassPathBeanDefinitionScanner {


    public ServiceInterfaceScanner(BeanDefinitionRegistry beanDefinitionRegistry) {
        //false 不加载默认的TypeFilter
        super(beanDefinitionRegistry, false);
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        this.addAllFilter();
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        if (beanDefinitionHolders.isEmpty()) {
            throw new NullPointerException("no interfaces");
        }
        this.createBeanDefinition(beanDefinitionHolders);
        return beanDefinitionHolders;
    }

    private void createBeanDefinition(Set<BeanDefinitionHolder> beanDefinitionHolders) {
        for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
            GenericBeanDefinition beanDefinition = (GenericBeanDefinition) beanDefinitionHolder.getBeanDefinition();
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanDefinition.getBeanClassName());
            beanDefinition.setBeanClass(ServiceProxyFactoryBean.class);
            beanDefinition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
        }

    }


    //扫描所有类
    private void addAllFilter() {
        addIncludeFilter((metadataReader, metadataReaderFactory) -> true);

    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        AnnotationMetadata metadata = beanDefinition.getMetadata();
        String[] interfaceNames = metadata.getInterfaceNames();
        return metadata.isInterface() && metadata.isIndependent() && Arrays.asList(interfaceNames).contains
                (BaseService.class.getName());
    }

}
