package com.lss.self.annotation;

import com.lss.self.inter.AbstractRetryLitener;
import com.lss.self.inter.RetryListener;
import com.lss.self.inter.RetryStrategy;
import com.lss.self.inter.impl.FastRetryStrategy;

import java.lang.annotation.*;

/**
 * @author 10380
 */
@Documented
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Retry {
    //最大重试次数
    int maxAttempts() default 3;

    int delay() default 3000;

    Class<? extends Throwable>[] value() default {};

    Class<? extends RetryListener> listener() default AbstractRetryLitener.class;

    Class<? extends RetryStrategy>  strategy() default FastRetryStrategy.class;



}
