/**
 * FileName: ServiceProxyFactory
 * Author:   13235
 * Date:     2019/9/16 21:42
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovider.service.Impl;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

/**
 * 〈一句话功能简述〉<br>
 * 定义代理类实现工厂
 *
 * @author 13235
 * @create 2019/9/16
 * @since 1.0.0
 */
public class ServiceProxyFactoryBean<T> implements FactoryBean<T> {
    private Class<T> interfaces;

    public ServiceProxyFactoryBean(Class<T> interfaces) {
        this.interfaces = interfaces;
    }


    @Override
    public T getObject() throws Exception {
        return (T) Proxy.newProxyInstance(interfaces.getClassLoader(), new Class[]{interfaces},
                new ServiceProxy<>(interfaces));
    }

    @Override
    public Class<?> getObjectType() {
        return interfaces;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
