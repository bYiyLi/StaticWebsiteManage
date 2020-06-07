package com.example.db.api;

import com.example.db.core.Md;

public interface MdDb {
    public void addMd(String name,String url);
    public Md getMdById(int id);
    public void delMdById(int id);
    public void setMdName(int id,String name);
    public void setMdUrl(int id,String url);
    public void addMdProperty(int id,String content);
    public void delMdProperty(int id,String content);
}
