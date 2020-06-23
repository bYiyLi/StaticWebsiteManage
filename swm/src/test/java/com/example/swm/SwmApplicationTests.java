package com.example.swm;

import com.example.db.core.*;
import com.example.service.GuidPool;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SwmApplicationTests {
    @Autowired
    GroupHandle groupHandle;
    @Test
    void setGroupHandlecontextLoads() {
        groupHandle.setGroupName(3,"aaaaaaaaa1");
        groupHandle.setGroupUrl(3,"/asdfasdfasdf");
        groupHandle.delGroupById(99);
    }
    @Test
    void contextLoads() {
        Group group = new Group(90,"YI","/yi");
        ArrayList<Group> groups = new ArrayList<>();
        groups.add(new Group(90,"YI","/yi"));
        groups.add(new Group(90,"YI","/yi"));
        groups.add(new Group(90,"YI","/yi"));
        group.setGroups(groups);

        ArrayList<Md> mdArrayList = new ArrayList<>();
        mdArrayList.add(new Md(90,"YI","/yi"));
        mdArrayList.add(new Md(90,"YI","/yi"));
        mdArrayList.add(new Md(90,"YI","/yi"));
        group.setMds(mdArrayList);

        ArrayList<String> p=new ArrayList<>();
        p.add("a#a1");
        p.add("a#a1");
        p.add("a#a1");
        p.add("a#a1");
        group.setProperty(p);
        System.out.println(group.toString());
    } @Test
    void c1ontextLoads() {
        Md group = new Md(90,"YI","/yi");
        ArrayList<String> p=new ArrayList<>();
        p.add("a#a1");
        p.add("a#a1");
        p.add("a#a1");
        p.add("a#a1");
        group.setProperty(p);
        System.out.println(group.toString());
    }
    @Autowired
    GuidPool guidPool;
    @Test
    void c1osntextLoads() {
        for (int n=0;n<1000;n++){
            System.out.println(guidPool.getGuid());
        }
    }

}
