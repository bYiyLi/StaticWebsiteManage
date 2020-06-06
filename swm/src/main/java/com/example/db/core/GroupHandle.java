package com.example.db.core;

import com.example.db.api.GroupDb;
import com.example.db.sql.ItemMapper;
import com.example.db.sql.ItemPropertyMapper;
import com.example.db.sql.SqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("groupDb")
public class GroupHandle implements GroupDb {
    private static int id=0;
    final
    SqlFactory sqlFactory;

    public GroupHandle(SqlFactory sqlFactory) {
        this.sqlFactory = sqlFactory;
    }

    @Override
    public Group getGroupById(int id) {
        SqlSessionFactory factory = sqlFactory.getFactory();
        Group group;
        try (SqlSession session = factory.openSession()) {
            ItemMapper mapper = session.getMapper(ItemMapper.class);
            Item item = mapper.getItemById(id);
            if (item==null||(!item.getType().equals("group"))){
                return null;
            }
            group = new Group();
            group.setId(id);
            group.setName(item.getName());
            group.setUrl(item.getUrl());
            ItemPropertyMapper propertyMapper = session.getMapper(ItemPropertyMapper.class);
            List<ItemProperty> itemProperty = propertyMapper.getItemPropertyById(id);
            List<String> propertyList=new ArrayList<>();
            List<Group> groupList=new ArrayList<>();
            List<MdItem> mdItemList=new ArrayList<>();
            for (ItemProperty ip:itemProperty){
                switch (ip.getType()){
                    case "property":{
                        propertyList.add(ip.content);
                        break;
                    }
                    case "group":{
                        groupList.add(new Group(Integer.parseInt(ip.content),ip.name));
                        break;
                    }
                    case "md":{
                        mdItemList.add(new MdItem(Integer.parseInt(ip.content),ip.name));
                    }
                }
            }
            group.setProperty(propertyList);
            group.setGroups(groupList);
            group.setMds(mdItemList);
        }
        return group;
    }

    @Override
    public void delGroupById(int id) {
        SqlSessionFactory factory = sqlFactory.getFactory();
        try (SqlSession session = factory.openSession()) {
            ItemPropertyMapper propertyMapper = session.getMapper(ItemPropertyMapper.class);
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("id",id);
            propertyMapper.delItemProperty(hashMap);
            session.commit();
            ItemMapper mapper = session.getMapper(ItemMapper.class);
            mapper.delItemById(id);
            session.commit();
        }
    }

    @Override
    public void setGroupName(int id, String name) {
        SqlSessionFactory factory = sqlFactory.getFactory();
        try (SqlSession session = factory.openSession()) {
            ItemMapper mapper = session.getMapper(ItemMapper.class);
            Map<String,Object> map=new HashMap<>();
            map.put("name",name);
            mapper.setItem(id,map);
            session.commit();
        }
    }

    @Override
    public void setGroupUrl(int id, String url) {
        SqlSessionFactory factory = sqlFactory.getFactory();
        try (SqlSession session = factory.openSession()) {
            ItemMapper mapper = session.getMapper(ItemMapper.class);
            Map<String,Object> map=new HashMap<>();
            map.put("url",url);
            mapper.setItem(id,map);
            session.commit();
        }
    }
    @Override
    public void addGroup(String name,String url) {
        SqlSessionFactory factory = sqlFactory.getFactory();
        try (SqlSession session = factory.openSession()) {
            ItemMapper mapper = session.getMapper(ItemMapper.class);
            mapper.addItem(new Item(getNewId(),name,"group",url));
            session.commit();
        }
    }
    private int getNewId(){
        SqlSessionFactory factory = sqlFactory.getFactory();
        try (SqlSession session = factory.openSession()) {
            ItemMapper mapper = session.getMapper(ItemMapper.class);
            while (true){
                if (mapper.getItemById(id)==null){
                    return id;
                }
                id++;
            }
        }
    }
}
