package com.example.swm;

import com.example.dao.dir.MdDir;
import com.example.service.lock.SynLock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.concurrent.locks.Lock;

@SpringBootTest
class SwmApplicationTests {
    @Autowired
    SynLock synLock;
    @Autowired
    MdDir mdDir;
    @Test
    void contextLoads() {
        mdDir.toString();
    }

}
