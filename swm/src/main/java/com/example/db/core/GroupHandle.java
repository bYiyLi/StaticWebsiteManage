package com.example.db.core;
import com.example.db.api.GroupDb;
import com.example.db.sql.ItemMapper;
import com.example.db.sql.ItemPropertyMapper;
import com.example.db.sql.SqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("groupHandle")
public class GroupHandle implements GroupDb {
    final
    NewItemId newItemId;

    final
    SqlFactory sqlFactory;

    public GroupHandle(SqlFactory sqlFactory, NewItemId newItemId) {
        this.sqlFactory = sqlFactory;
        this.newItemId = newItemId;
    }


    @Override
    public Group getGroupById(int id) {
        SqlSessionFactory factory = sqlFactory.getFactory();
        Group group;
        try (SqlSession sqlSession = factory.openSession()){
            ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
            Item item = mapper.getItemById(id);
            if (item==null||!item.getType().equals("group")){
                return null;
            }
            group=new Group(item.getId(),item.getName(),item.getUrl());

            ItemPropertyMapper propertyMapper = sqlSession.getMapper(ItemPropertyMapper.class);
            List<ItemProperty> itemProperty = propertyMapper.getItemPropertyById(id);
            List<String> propertyList=new ArrayList<>();
            List<Group> groupList=new ArrayList<>();
            List<Md> mdList=new ArrayList<>();
            for (ItemProperty ip:itemProperty){
                switch (ip.getType()){
                    case "group":{
                        groupList.add(new Group(Integer.parseInt(ip.content),ip.name));
                        break;
                    }
                    case "md":{
                        mdList.add(new Md(Integer.parseInt(ip.content),ip.name, item.getUrl()));
                        break;
                    }
                    case "property":{
                        propertyList.add(ip.name+"#"+ip.content);
                    }
                }
            }
            group.setProperty(propertyList);
            group.setGroups(groupList);
            group.setMds(mdList);
        }
        return group;
    }

    @Override
    public void delGroupById(int id) {
        SqlSessionFactory factory = sqlFactory.getFactory();
        try (SqlSession sqlSession = factory.openSession()){
            ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
            mapper.delItemById(id);
            sqlSession.commit();
            ItemPropertyMapper propertyMapper = sqlSession.getMapper(ItemPropertyMapper.class);
            Map<String,Object> map= new HashMap<>();
            map.put("id",id);
            propertyMapper.delItemProperty(map);
            sqlSession.commit();
        }
    }

    @Override
    public void setGroupName(int id, String name) {
        SqlSessionFactory factory = sqlFactory.getFactory();
        try (SqlSession sqlSession = factory.openSession()){
            ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
            Map<String,Object> map=new HashMap<>();
            map.put("name",name);
            mapper.setItem(id,map);
            sqlSession.commit();
        }
    }

    @Override
    public void setGroupUrl(int id, String url) {
        SqlSessionFactory factory = sqlFactory.getFactory();
        try (SqlSession sqlSession = factory.openSession()){
            ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
            Map<String,Object> map=new HashMap<>();
            map.put("url",url);
            mapper.setItem(id,map);
            sqlSession.commit();
        }
    }

    @Override
    public void addGroup(String name, String url) {
        SqlSessionFactory factory = sqlFactory.getFactory();
        try (SqlSession sqlSession = factory.openSession()){
            ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
            mapper.addItem(new Item(newItemId.getNewId(),name,"group",url));
            sqlSession.commit();
        }
    }

    @Override
    public void addGroupProperty(int id, String content) {
        SqlSessionFactory factory = sqlFactory.getFactory();
        try (SqlSession sqlSession = factory.openSession()){
            ItemPropertyMapper propertyMapper = sqlSession.getMapper(ItemPropertyMapper.class);
            String[] split = content.split("#");
            if (split.length>1){
                propertyMapper.addItemProperty(new ItemProperty(id,split[0].trim(),"property",split[1].trim()));
            }else {
                propertyMapper.addItemProperty(new ItemProperty(id," ","property",content.trim()));
            }
        }
    }

    @Override
    public void delGroupProperty(int id, String content) {
        SqlSessionFactory factory = sqlFactory.getFactory();
        try (SqlSession sqlSession = factory.openSession()){
            ItemPropertyMapper propertyMapper = sqlSession.getMapper(ItemPropertyMapper.class);
            String[] split = content.split("#");
            Map<String,Object> map=new HashMap<>();
            map.put("id",id);
            map.put("type","property");
            if (split.length>1){
                map.put("content",split[1].trim());
                map.put("name",split[0].trim());
            }else {
                map.put("content",split[0].trim());
            }
            propertyMapper.delItemProperty(map);
            sqlSession.commit();
        }
    }

    @Override
    public boolean addChildren(int id,String type,int cid) {
        SqlSessionFactory factory = sqlFactory.getFactory();
        try (SqlSession sqlSession = factory.openSession()) {
            ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
            Item item = mapper.getItemById(cid);
            if (item==null){
                return false;
            }
            ItemPropertyMapper propertyMapper = sqlSession.getMapper(ItemPropertyMapper.class);
            propertyMapper.addItemProperty(new ItemProperty(id,item.getName(),item.getType(),String.valueOf(cid)));
            sqlSession.commit();
        }
        return true;
    }

    @Override
    public void delChildren(int id,int cid) {
        SqlSessionFactory factory = sqlFactory.getFactory();
        try (SqlSession sqlSession = factory.openSession()) {
            ItemPropertyMapper propertyMapper = sqlSession.getMapper(ItemPropertyMapper.class);
            Map<String,Object> map=new HashMap<>();
            map.put("id",id);
            map.put("content",String.valueOf(cid));
            propertyMapper.delItemProperty(map);
            sqlSession.commit();
        }
    }

}
