/**
 * FileName: XController
 * Author:   13235
 * Date:     2019/9/15 1:39
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
 * 〈〉
 *
 * @author 13235
 * @create 2019/9/15
 * @since 1.0.0
 */
@Target(value= ElementType.TYPE)
@Documented
@Retention(value= RetentionPolicy.RUNTIME)
public @interface XController {
    String value() default "";
}
