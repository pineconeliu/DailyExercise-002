package com.lss.inter;

import com.lss.annotation.Retry;

public interface RetryStrategy {

    /**
     * 初始化一些参数配置
     */
    void initArgs (Retry retry,RetryTask retryTask);

    /**
     * 重试策略
     */
    void retryTask();
}
