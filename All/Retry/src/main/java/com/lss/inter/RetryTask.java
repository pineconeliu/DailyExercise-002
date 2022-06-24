package com.lss.inter;

public interface  RetryTask {

    Object getRetryResult();

    Boolean getRetryStatus();

    void setRetrySuccess();

    void doTask() throws Throwable;


}
