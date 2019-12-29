/**
 * FileName: WebConfig
 * Author:   13235
 * Date:     2019/9/8 13:40
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovider.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.xkf.cloudprovider.interceptor.PermissionCheckInterceptor;

/**
 * 〈一句话功能简述〉<br>
 *  421121197004262023
 *
 * @author 13235
 * @create 2019/9/8
 * @since 1.0.0
 */
@Component
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PermissionCheckInterceptor())
                .addPathPatterns("/api/test");
    }

}
