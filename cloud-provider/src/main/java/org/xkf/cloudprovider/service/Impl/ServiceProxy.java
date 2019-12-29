/**
 * FileName: ServiceProxy
 * Author:   13235
 * Date:     2019/9/14 14:13
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovider.service.Impl;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 〈一句话功能简述〉<br>
 * 定义一个代理类
 *
 * @author 13235
 * @create 2019/9/14
 * @since 1.0.0
 */
@Slf4j
public class ServiceProxy<T> implements InvocationHandler {
    private Class<T> interfaces;

    public ServiceProxy(Class<T> interfaces) {
        this.interfaces = interfaces;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getDeclaringClass().equals(interfaces)) {
            log.info("执行您的方法:" + method.getName());
            return method.getName();
        } else {
            return method.invoke(new DefaultService(), args);
        }
    }
}
