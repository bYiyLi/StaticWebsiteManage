package com.example.db.api;
import com.example.db.core.Group;
public interface GroupDb {
    public boolean isGroupType(int id);
    public Group getGroupById(int id);
    public void delGroupById(int id);
    public void setGroupName(int id,String name);
    public void setGroupUrl(int id,String url);
    public Integer addGroup(String name,String url);
    public void addGroupProperty(int id,String content);
    public void delGroupProperty(int id,String content);
    public boolean addChildren(int id,String type,int cid);
    public void delChildren(int id,int cid);
}
