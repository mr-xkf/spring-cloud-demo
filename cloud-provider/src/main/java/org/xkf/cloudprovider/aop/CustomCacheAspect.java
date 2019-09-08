/**
 * FileName: CustomCacheAspect
 * Author:   13235
 * Date:     2019/9/8 12:37
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovider.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.xkf.cloudprovider.annotation.CustomCache;

import java.util.Objects;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 13235
 * @create 2019/9/8
 * @since 1.0.0
 */
@Aspect
@Component
public class CustomCacheAspect {

    /**
     * 在方法执行之前对注解进行处理
     *
     * @param pjp
     * @param customCache 注解
     * @return 返回的值
     */

    @Around("@annotation(org.xkf.cloudprovider.annotation.CustomCache)&&@annotation(customCache)")
    public Object dealProcess(ProceedingJoinPoint pjp, CustomCache customCache) {
        Object result = null;
        if (customCache.key() == null) {
            //todo throw error
        }
        //业务处理逻辑
        if (Objects.equals("testKey", customCache.key())) {
            return "Hello World";
        }

        //执行目标方法
        try {
            pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }
}
