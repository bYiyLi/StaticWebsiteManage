package com.example.db.core;

import com.example.db.api.MdDb;
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

@Service("mdHandle")
public class MdHandle  implements MdDb {
    final
    NewItemId newItemId;
    final
    SqlFactory sqlFactory;

    public MdHandle(SqlFactory sqlFactory, NewItemId newItemId) {
        this.sqlFactory = sqlFactory;
        this.newItemId = newItemId;
    }

    @Override
    public Md getMdById(int id) {
        SqlSessionFactory factory = sqlFactory.getFactory();
        Md md;
        try (SqlSession sqlSession = factory.openSession()) {
            ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
            Item item = mapper.getItemById(id);
            if (item==null||!item.getType().equals("md")){
                return null;
            }
            md=new Md(item.getId(),item.getName(),item.getUrl());
            ItemPropertyMapper propertyMapper = sqlSession.getMapper(ItemPropertyMapper.class);
            List<ItemProperty> itemProperty = propertyMapper.getItemPropertyById(id);
            List<String> propertyList=new ArrayList<>();
            for (ItemProperty ip:itemProperty){
                if (ip.getType().equals("property")){
                    propertyList.add(ip.name+"#"+ip.content);
                }
            }
            md.setProperty(propertyList);
            return md;
        }
    }

    @Override
    public void delMdById(int id) {
        SqlSessionFactory factory = sqlFactory.getFactory();
        try (SqlSession sqlSession = factory.openSession()) {
            ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
            Map<String, Object> map = new HashMap<>();
            mapper.delItemById(id);
            sqlSession.commit();
            ItemPropertyMapper propertyMapper = sqlSession.getMapper(ItemPropertyMapper.class);
            map.put("id", id);
            propertyMapper.delItemProperty(map);
            sqlSession.commit();
        }
    }

    @Override
    public void setMdName(int id, String name) {
        SqlSessionFactory factory = sqlFactory.getFactory();
        try (SqlSession sqlSession = factory.openSession()){
            Map<String,Object> map=new HashMap<>();
            ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
            map.put("name",name);
            mapper.setItem(id,map);
            sqlSession.commit();
        }
    }

    @Override
    public void setMdUrl(int id, String url) {
        SqlSessionFactory factory = sqlFactory.getFactory();
        try (SqlSession sqlSession = factory.openSession()){
            Map<String,Object> map=new HashMap<>();
            ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
            map.put("url",url);
            mapper.setItem(id,map);
            sqlSession.commit();
        }
    }

    @Override
    public void addMdProperty(int id, String content) {
        String[] split = content.split("#");
        SqlSessionFactory factory = sqlFactory.getFactory();
        try (SqlSession sqlSession = factory.openSession()){
            ItemPropertyMapper propertyMapper = sqlSession.getMapper(ItemPropertyMapper.class);
            if (split.length>1){
                propertyMapper.addItemProperty(new ItemProperty(id,split[0].trim(),"property",split[1].trim()));
            }else {
                propertyMapper.addItemProperty(new ItemProperty(id," ","property",content.trim()));
            }
        }
    }

    @Override
    public void delMdProperty(int id, String content) {
        Map<String,Object> map=new HashMap<>();
        SqlSessionFactory factory = sqlFactory.getFactory();
        try (SqlSession sqlSession = factory.openSession()){
            ItemPropertyMapper propertyMapper = sqlSession.getMapper(ItemPropertyMapper.class);
            String[] split = content.split("#");
            map.put("type","property");
            map.put("id",id);
            if (split.length>1){
                map.put("name",split[0].trim());
                map.put("content",split[1].trim());
            }else {
                map.put("content",split[0].trim());
            }
            propertyMapper.delItemProperty(map);
            sqlSession.commit();
        }
    }

    @Override
    public Integer addMd(String name, String url) {
        SqlSessionFactory factory = sqlFactory.getFactory();
        try (SqlSession sqlSession = factory.openSession()){
            ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
            int newId = newItemId.getNewId();
            mapper.addItem(new Item(newId,name,"md",url));
            sqlSession.commit();
            return newId;
        }
    }
}
