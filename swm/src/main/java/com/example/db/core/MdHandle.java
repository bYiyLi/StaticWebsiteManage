package com.example.db.core;

import com.example.db.api.MdDb;
import org.springframework.stereotype.Service;

@Service("mdHandle")
public class MdHandle  implements MdDb {
    @Override
    public Group getMdById(int id) {
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
    public void addMd(String name, String url) {

    }
}
