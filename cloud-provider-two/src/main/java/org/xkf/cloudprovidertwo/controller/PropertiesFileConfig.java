/**
 * FileName: PropertiesFileConfig
 * Author:   13235
 * Date:     2019/10/20 21:55
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovidertwo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;

import java.util.Arrays;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 13235
 * @create 2019/10/20
 * @since 1.0.0
 */
@Slf4j
public class PropertiesFileConfig   implements BeanFactoryPostProcessor,EnvironmentAware {

    @Autowired
    private AbstractEnvironment environment;
  /*  @Override
    protected void loadProperties(Properties props) throws IOException {
        super.loadProperties(props);
        InputStream resourceAsStream = this.getClass().getResourceAsStream("/application.properties");
        props.load(resourceAsStream);
        Set<Object> objects = props.keySet();
        for (Object object : objects) {
            String key= object.toString();
            if ("spring.datasource.password".equals(key)) {
                props.setProperty(key, "root");
            }
        }
    }*/

    @Override
    public void setEnvironment(Environment environment) {

        String[] activeProfiles = environment.getActiveProfiles();
        System.out.println("activeProfiles:"+Arrays.toString(activeProfiles));
        String[] defaultProfiles = environment.getDefaultProfiles();
        System.out.println("defaultProfiles"+Arrays.toString(defaultProfiles));
        String property = environment.getProperty("spring.datasource.password");
        String s = environment.resolvePlaceholders("spring.datasource.password");

        System.out.println(property);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        MutablePropertySources propertySources = environment.getPropertySources();




    }
}
