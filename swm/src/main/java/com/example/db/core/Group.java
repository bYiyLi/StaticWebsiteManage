package com.example.db.core;

import java.util.List;

public class Group {
    int id;
    String name;
    String url;
    List<Group> groups;
    List<Md> mds;
    List<String> property;
    @Override
    public String toString() {
        StringBuilder groupStr=new StringBuilder();
        groupStr.append("[");
        if (groups!=null){
            for (Group i:groups){
                groupStr.append("{\"id\":\"").append(i.getId())
                        .append("\",\"name\":\"").append(i.getName())
                        .append("\",\"url\":\"").append(i.getUrl()).append("\"},");
            }
            groupStr.delete(groupStr.length()-1, groupStr.length());
        }
        groupStr.append("]");

        StringBuilder propertyStr=new StringBuilder();
        propertyStr.append("[");
        if (property!=null){
            for (String i:property){
                String[] split = i.split("#");
                if (split.length>1){
                    propertyStr.append("{\"name\":\"").append(split[0])
                            .append("\",\"content\":\"").append(split[1]).append("\"},");
                }else {
                    propertyStr.append("{\"name\":\"").append(" ")
                            .append("\",\"content\":\"").append(split[0]).append("\"},");
                }
            }
            propertyStr.delete(propertyStr.length()-1, propertyStr.length());
        }
        propertyStr.append("]");

        StringBuilder mdStr=new StringBuilder();
        mdStr.append("[");
        if (mds!=null){
            for (Md i:mds){
                mdStr.append("{\"id\":\"").append(i.getId())
                        .append("\",\"name\":\"").append(i.getName())
                        .append("\",\"url\":\"").append(i.getUrl()).append("\"},");
            }
            mdStr.delete(mdStr.length()-1, mdStr.length());
        }
        mdStr.append("]");
        return '{' + "\"id\":\"" + id + "\"," +
                "\"name\":\"" + name + "\"," +
                "\"url\":\"" + url + "\"," +
                "\"propertyList\":" + propertyStr.toString() + "," +
                "\"groupList\":" + groupStr.toString() + "," +
                "\"mdList\":" + mdStr.toString() + '}';
    }
    public Group(int id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public Group(int id, String name) {
        this.id = id;
        this.name = name;
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
