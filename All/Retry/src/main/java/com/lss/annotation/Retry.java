package com.lss.annotation;

public @interface Retry {
    int maxAttempts() default 3;
}
