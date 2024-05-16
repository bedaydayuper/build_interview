package com.zhangpeng.buildinterview.service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 执行具体的计算任务
 */
public class RandomOddNumberTask implements Runnable {
    private final AtomicInteger oddCount = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            if (ThreadLocalRandom.current().nextInt(10000) % 2 != 0) {
                oddCount.incrementAndGet();
            }
        }
    }

    public int getOddCount() {
        return oddCount.get();
    }
}
