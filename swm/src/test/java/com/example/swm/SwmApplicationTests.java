package com.example.swm;

import com.example.db.core.*;
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
    }

}
