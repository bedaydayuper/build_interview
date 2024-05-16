package com.zhangpeng.buildinterview.controller;

import com.zhangpeng.buildinterview.service.OddTaskExecutor;
import com.zhangpeng.buildinterview.service.RandomOddNumberTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TaskController {

    @Autowired
    OddTaskExecutor oddTaskExecutor;

    @GetMapping("/submit")
    public ResponseEntity<?> submitTasks() {
        for (int i = 0; i < 2000; i++) {
            oddTaskExecutor.submitTask(new RandomOddNumberTask());
        }
        return ResponseEntity.ok("Tasks submitted.");
    }

    @GetMapping("/cancel")
    public ResponseEntity<?> cancelTasks() {
        oddTaskExecutor.cancelTasks();
        // 这里可以返回已经执行完的任务的部分结果，但具体实现取决于你如何跟踪和存储这些结果
        return ResponseEntity.ok("Tasks cancelled.");
    }

}
