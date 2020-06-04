package com.example.service.lock;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 排他锁
 * 用于监控模块，同步模块资源互斥
 * 将’锁‘配进sping boot容器
 */
@Service(value = "synLock")
@Scope(value = "singleton")
public class SynLock {
    private ReentrantLock reentrantLock;
    public SynLock(){
        this.reentrantLock=new ReentrantLock(true);
    }
    public Lock getLock(){
        return this.reentrantLock;
    }
}
