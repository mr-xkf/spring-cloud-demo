/**
 * FileName: CustomCache
 * Author:   13235
 * Date:     2019/9/8 12:28
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovider.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 〈一句话功能简述〉<br> 
 * 自定义注解缓存值
 *
 * @author 13235
 * @create 2019/9/8
 * @since 1.0.0
 */
@Documented
@Target(value={ElementType.TYPE,ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface CustomCache {

    String key() default "";
}
