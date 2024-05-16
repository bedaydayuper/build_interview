package com.zhangpeng.buildinterview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExecutorService {
    private OddTaskExecutor oddTaskExecutor;

    @Autowired
    public ExecutorService(OddTaskExecutor oddTaskExecutor) {
        this.oddTaskExecutor = oddTaskExecutor;
    }
}
