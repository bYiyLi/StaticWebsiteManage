package com.example.db.core;

import java.util.List;

public class Md {
    int id;
    String name;
    String url;
    List<String> property;

    @Override
    public String toString() {
        return "MdItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", property=" + property +
                '}';
    }
    public Md(int id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url=url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getProperty() {
        return property;
    }

    public void setProperty(List<String> property) {
        this.property = property;
    }



}
