package com.lss.self.inter;

import com.lss.self.annotation.Retry;

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
