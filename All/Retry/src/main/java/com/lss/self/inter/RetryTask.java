package com.lss.self.inter;

public interface  RetryTask {

    Object getRetryResult();

    Boolean getRetryStatus();

    void setRetrySuccess();

    void doTask() throws Throwable;


}
