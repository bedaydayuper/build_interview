package com.zhangpeng.buildinterview.service;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class OddTaskExecutor {
    private final BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();
    private final AtomicBoolean isCancelled = new AtomicBoolean(false);
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    FileWriter writer;

    @PostConstruct
    public void initWrite() {
        try {
            writer = new FileWriter("output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @PreDestroy
    public void destroyWrite() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交任务
     * @param task
     */
    public void submitTask(Runnable task) {
        try {
            taskQueue.put(task);
            if (!executorService.isShutdown()) {
                executorService.execute(this::processQueue);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 执行
     */
    private void processQueue() {
        try {
            while (!taskQueue.isEmpty() && !isCancelled.get()) {
                try {
                    Runnable task = taskQueue.take();
                    task.run();
                    // 这里假设任务是RandomOddNumberTask的实例，可以将其结果写入文件
                    if (task instanceof RandomOddNumberTask) {
                        int oddCount = ((RandomOddNumberTask) task).getOddCount();
                        // 将oddCount写入文件
                        writer.append("Task " + Thread.currentThread().getName() + " completed with count " + oddCount + "\n");
                    }
                } catch (InterruptedException e) {
                    // 如果线程被中断，可能是因为取消操作
                    Thread.currentThread().interrupt();
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    /**
     * 取消任务
     */
    public void cancelTasks() {
        isCancelled.set(true);
        executorService.shutdownNow(); // 尝试停止所有正在执行的任务
    }
}
