package com.example.db.core;

import java.util.List;

public class Md {
    int id;
    String name;
    String url;
    List<String> property;

    @Override
    public String toString() {
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

        return '{' + "\"id\":\"" + id + "\"," +
                "\"name\":\"" + name + "\"," +
                "\"url\":\"" + url + "\"," +
                "\"propertyList\":" + propertyStr.toString() + '}';
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
