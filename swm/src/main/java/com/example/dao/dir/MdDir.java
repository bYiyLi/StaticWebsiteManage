package com.example.dao.dir;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Service("mdDir")
@Scope("singleton")
public class MdDir {
    public MdDir() throws IOException {
        Properties pro = new Properties();
        FileInputStream fileInputStream = new FileInputStream("base.properties");
        pro.load(fileInputStream);
        String property = pro.getProperty("monitor-dir");
        System.out.println(property);
    }
}
