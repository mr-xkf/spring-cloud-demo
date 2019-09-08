/**
 * FileName: PermissionCheckInterceptor
 * Author:   13235
 * Date:     2019/9/8 12:35
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovider.interceptor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.xkf.cloudprovider.annotation.PermissionCheck;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2019/9/8
 * @since 1.0.0
 */
@Slf4j
public class PermissionCheckInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = ((HandlerMethod) handler);
        PermissionCheck permissionCheck = findPermissionCheck(handlerMethod);
        //如果没有权限访问则跳过
        if (Objects.isNull(permissionCheck)) {
            return true;
        }
        String resourceKey = permissionCheck.resourceKey();
        if (Objects.equals("testKey", resourceKey)) {
            log.info("通过验证！");
            return true;
        }
        return false;
    }

    private PermissionCheck findPermissionCheck(HandlerMethod handlerMethod) {
        //首先从方法上查找注解
        PermissionCheck methodAnnotation = handlerMethod.getMethodAnnotation(PermissionCheck.class);
        if (Objects.isNull(methodAnnotation)) {
            //从类上查找
            methodAnnotation = handlerMethod.getBeanType().getAnnotation(PermissionCheck.class);
        }
        return methodAnnotation;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
