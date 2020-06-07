package com.example.db.core;

import java.util.List;

public class Md {
    int id;
    String name;
    String url;
    List<String> property;
    List<Integer> groups;

    public Md(int id, String name) {
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
