package com.example.db.core;

import java.util.List;

public class Group {
    int id;
    String name;
    String url;
    List<Group> groups;
    List<Md> mds;
    List<String> property;
    public Group(int id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public Group(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public Group() {
    }

    public List<String> getProperty() {
        return property;
    }

    public void setProperty(List<String> property) {
        this.property = property;
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

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<Md> getMds() {
        return mds;
    }

    public void setMds(List<Md> mds) {
        this.mds = mds;
    }
}
