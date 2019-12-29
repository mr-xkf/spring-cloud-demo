/**
 * FileName: Dog
 * Author:   13235
 * Date:     2019/10/22 16:21
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovidertwo.entity;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2019/10/22
 * @since 1.0.0
 */
public class Dog implements BeanPostProcessor, InitializingBean, DisposableBean {

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Dog() {
        System.out.println("Dog...无参构造方法！");
    }

    public void say() {
        System.out.println("狗子叫方法。。。。");
    }

    @PostConstruct
    public void psc() {
        System.out.println("dog中@postConstruct.......");
    }

    @PreDestroy
    public void pdt() {
        System.out.println("dog中@PreDestroy.....");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("dog中destroy.....");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("dog中afterPropertiesSet。。。。。");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("实例化前。。。。。。");
        return bean;

    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("实例化之后。。。。。。");
        return bean;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
