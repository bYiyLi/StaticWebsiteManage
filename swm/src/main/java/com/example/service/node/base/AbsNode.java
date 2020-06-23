package com.example.service.node.base;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
public abstract class AbsNode {
    private final ConcurrentHashMap<String,String> property;
    private final NodeType type;
    public final Integer nodeId;
    protected final String nodeName;
    public AbsNode(Integer nodeId,String nodeName,NodeType type){
        this.type=type;
        this.nodeId=nodeId;
        this.nodeName=nodeName;
        property=new ConcurrentHashMap<>();
    }

    public String getNodeName() {
        return nodeName;
    }

    public NodeType getType() {
        return type;
    }
    public boolean addProperty(String key, String value){
        return property.put(key, value) == null;
    }
    public boolean delProperty(String key){
        return property.remove(key) != null;
    }
    public ConcurrentHashMap<String, String> getProperty() {
        return property;
    }
}
