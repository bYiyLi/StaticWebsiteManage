package com.example.controller.web_Socket;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;

@Service("blogJson")
public class BlogJson {
    public String getJson(String type,String id,String url) throws IOException {
        File file = ResourceUtils.getFile("classpath:static/"+url);
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder result = new StringBuilder();
        result.append("{\"type\":\"").append(type).append("\",\"div_id\":\"").append(id).append("\",\"content\":\"");
        String s=null;
        while((s = br.readLine())!=null){//使用readLine方法，一次读一行
            result.append(s.replaceAll("\"", "\\\\\""));
        }
        br.close();
        result.append("\"}");
        return result.toString();
    }
}
