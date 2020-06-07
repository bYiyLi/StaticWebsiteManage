package com.example.db.core;

import com.example.db.api.MdDb;
import com.example.db.sql.SqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mdHandle")
public class MdHandle  implements MdDb {
    final
    SqlFactory sqlFactory;

    public MdHandle(SqlFactory sqlFactory) {
        this.sqlFactory = sqlFactory;
    }

    @Override
    public Md getMdById(int id) {
        SqlSessionFactory factory = sqlFactory.getFactory();
        try (SqlSession sqlSession = factory.openSession()) {

        }
        return null;
    }

    @Override
    public void delMdById(int id) {

    }

    @Override
    public void setMdName(int id, String name) {

    }

    @Override
    public void setMdUrl(int id, String url) {

    }

    @Override
    public void addMdProperty(int id, String content) {

    }

    @Override
    public void delMdProperty(int id, String content) {

    }

    @Override
    public void addMd(String name, String url) {

    }
}
