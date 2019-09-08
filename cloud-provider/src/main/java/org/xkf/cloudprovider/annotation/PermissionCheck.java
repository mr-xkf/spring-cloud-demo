package org.xkf.cloudprovider.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 *  权限校验注解
 *
 *
 *
 */
@Target(value={ElementType.TYPE,ElementType.METHOD})
@Retention(value= RetentionPolicy.RUNTIME)
public @interface PermissionCheck {

    /**
     * 资源key
     * @return
     */
    String resourceKey();


}
