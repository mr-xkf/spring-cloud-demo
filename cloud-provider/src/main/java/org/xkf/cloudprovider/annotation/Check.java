/**
 * FileName: Check
 * Author:   13235
 * Date:     2019/9/8 12:32
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovider.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 〈一句话功能简述〉<br>
 * 自定义注解校验
 *
 * @author 13235
 * @create 2019/9/8
 * @since 1.0.0
 */
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
public @interface Check {

    String message() default "非指定的参数";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] paramsValue() default {};


}
