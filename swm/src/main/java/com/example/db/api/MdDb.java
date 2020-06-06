package com.example.db.api;

import com.example.db.core.Group;
import com.example.db.core.MdItem;

public interface MdDb {
    public MdItem getMdById(int id);
    public void delMdById(int id);
    public void setMdName(int id,String name);
    public void setMdUrl(int id,String url);
    public void addMd(String name,String url);
}
