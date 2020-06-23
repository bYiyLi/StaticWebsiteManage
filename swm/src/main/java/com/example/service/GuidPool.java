package com.example.service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
@Service("guidPool")
public class GuidPool {
    private final Set<String> guidSet=new ConcurrentSkipListSet<>();
    public String getGuid(){
        String s = newGUID();
        if (guidSet.add(s)){
            return s;
        }else {
            return getGuid();
        }
    }
    public void remove(String guid){
        guidSet.remove(guid);
    }
    private String newGUID() {
        StringBuilder uid = new StringBuilder();
        //产生16位的强随机数
        Random rd = new SecureRandom();
        for (int i = 0; i < 16; i++) {
            //产生0-2的3位随机数
            int type = rd.nextInt(3);
            switch (type){
                case 0:
                    //0-9的随机数
                    uid.append(rd.nextInt(10));
                    break;
                case 1:
                    //ASCII在65-90之间为大写,获取大写随机
                    uid.append((char)(rd.nextInt(25)+65));
                    break;
                case 2:
                    //ASCII在97-122之间为小写，获取小写随机
                    uid.append((char)(rd.nextInt(25)+97));
                    break;
                default:
                    break;
            }
        }
        return uid.toString();
    }
}
