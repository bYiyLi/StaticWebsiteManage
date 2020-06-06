package com.example.db.core;

import java.util.List;

public class MdItem {
    int id;
    String name;
    String url;
    List<String> property;
    List<Integer> groups;

    public MdItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "MdItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", property=" + property +
                ", groups=" + groups +
                '}';
    }
}
