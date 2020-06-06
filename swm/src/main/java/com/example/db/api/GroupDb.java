package com.example.db.api;

import com.example.db.core.Group;

import java.util.Map;

public interface GroupDb {
    public Group getGroupById(int id);
    public void delGroupById(int id);
    public void setGroupName(int id,String name);
    public void setGroupUrl(int id,String url);
    public void addGroup(String name,String url);
}
