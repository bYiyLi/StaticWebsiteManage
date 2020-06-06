package com.example.db.sql;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
@Service("sqlFactory")
@Scope("singleton")
public class SqlFactory {
    private static SqlSessionFactory sqlSessionFactory;
    public SqlFactory(){
        String resource = "mybatis-config.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  SqlSessionFactory getFactory()  {
        return sqlSessionFactory;
    }
}
