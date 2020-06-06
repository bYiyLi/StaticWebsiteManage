package com.example.swm;

import com.example.db.core.*;
import com.example.db.sql.ItemMapper;
import com.example.db.sql.ItemPropertyMapper;
import com.example.db.sql.SqlFactory;
import com.example.service.lock.SynLock;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<String> p=new ArrayList<>();
        p.add("p0");
        p.add("p1");
        p.add("p2");
        p.add("p3");
        List<MdItem> m=new ArrayList<>();
        m.add(new MdItem(10,"m0"));
        m.add(new MdItem(11,"m1"));
        m.add(new MdItem(12,"m2"));
        List<Group> g=new ArrayList<>();
        g.add(new Group(20,"g0"));
        g.add(new Group(21,"g1"));
        g.add(new Group(22,"g2"));
        g.add(new Group(23,"g3"));
        group.setMds(m);
        group.setGroups(g);
        group.setProperty(p);
        Group groupById = groupHandle.getGroupById(2);
        System.out.println(groupById.toString());
        groupById.getGroups().forEach(System.out::println);
        groupById.getMds().forEach(System.out::println);
        groupById.getProperty().forEach(System.out::println);
    }

}
