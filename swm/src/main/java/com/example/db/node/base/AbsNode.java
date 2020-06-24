package com.example.db.node.base;

/**
 *
 */
public abstract class AbsNode {
    private  String property;
    private final NodeType type;
    public  Integer nodeId;
    protected  String nodeName;
    private String state;
    private String url;
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public AbsNode(Integer nodeId, String nodeName, String property, NodeType type, String url){
        this.state="init";
        this.property=property;
        this.type=type;
        this.nodeId=nodeId;
        this.url=url;
        this.nodeName=nodeName;
    }
    public String getNodeName() {
        return nodeName;
    }
    public NodeType getType() {
        return type;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
