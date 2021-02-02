package Annotation;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName MyAnnotation.java
 * @Description 自定义注解
 * @createTime 2021年02月02日 20:14:00
 */
@Retention(RetentionPolicy.RUNTIME)//想用反射必须是运行时,默认class
@Target({METHOD,TYPE,PARAMETER})//不写atrget代表可以写在任意位置
public @interface MyAnnotation {
    int id() default 0;//配置参数
    String name() default "sadas";
    double [] scors() default {};
}
