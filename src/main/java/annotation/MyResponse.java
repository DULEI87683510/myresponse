package annotation;

import java.lang.annotation.*;

/**
 * @ClassName MyResponse
 * @Description TODO
 * @Author DL
 * @Date 2019/6/21 15:36
 * @Version 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyResponse {
    Class<? extends Result> value() default MyResult.class;
}
