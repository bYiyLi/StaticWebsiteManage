package com.example.db.core;

public class ItemProperty {
    int id;
    String name;
    String type;
    String content;


    @Override
    public String toString() {
        return "ItemProperty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public ItemProperty(int id, String name, String type, String content) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.content = content;
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

    public String getType() {
        return type;
    }
}
