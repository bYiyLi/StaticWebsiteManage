package com.example.service.node;

public class text {
    public static void main(String[] args) {
        GroupNode groupNode=new GroupNode(1,"home");
        groupNode.addNode(new GroupNode(2,"a2"));
        groupNode.addNode(new GroupNode(3,"a3"));
        groupNode.addNode(new MdNode(4,"m2"));
        groupNode.addNode(new MdNode(5,"m2"));
        groupNode.addProperty("who","yi");
        groupNode.addProperty("date","2014");
        System.out.println(groupNode.toString());
    }
}
