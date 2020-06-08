package com.example.db.core;

import com.example.db.sql.ItemMapper;
import com.example.db.sql.SqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewItemId {
    final
    SqlFactory sqlFactory;

    public NewItemId(SqlFactory sqlFactory) {
        this.sqlFactory = sqlFactory;
    }

    public int getNewId(){
        SqlSessionFactory factory = sqlFactory.getFactory();
        try (SqlSession sqlSession = factory.openSession()){
            ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
            while (true){
                Item item = mapper.getItemById(NewItemId.id);
                if (item==null){
                    return NewItemId.id;
                }
                NewItemId.id++;
            }

        }
    }
    private static int id=0;
}
