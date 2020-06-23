package com.example.service.node;

import com.example.service.node.base.AbsNode;
import com.example.service.node.base.NodeType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GroupNode extends AbsNode {
    private final ConcurrentHashMap<Integer,AbsNode> children;
    public GroupNode(Integer nodeId,String nodeName) {
        super(nodeId,nodeName, NodeType.GROUP_NODE);
        this.children=new ConcurrentHashMap<>();
    }
    public boolean addNode(AbsNode value){
        return children.put(value.nodeId,value)==null;
    }
    public boolean delNode(Integer key){
        return children.remove(key) != null;
    }
    public ConcurrentHashMap<Integer, AbsNode> getChildrenNode() {
        return children;
    }
    @Override
    public String toString() {
        StringBuilder propertyStr=new StringBuilder();
        propertyStr.append('[');
        for (Map.Entry<String, String> item:getProperty().entrySet()){
            propertyStr.append("{\"name\":\"").append(item.getKey())
                    .append("\",\"content\":\"").append(item.getValue())
                    .append("\"},");
        }
        if (propertyStr.length()>2){
            propertyStr.delete(propertyStr.length()-1, propertyStr.length());
        }
        propertyStr.append(']');

        StringBuilder childrenStr=new StringBuilder();
        childrenStr.append('[');
        for (Map.Entry<Integer,AbsNode> item:children.entrySet()){
            String type;
            switch (item.getValue().getType()){
                case MD_NODE:type="md";break;
                case GROUP_NODE:type="group";break;
                default:
                    type=" ";
            }
            childrenStr.append("{\"id\":\"").append(item.getKey())
                    .append("\",\"name\":\"").append(item.getValue().getNodeName())
                    .append("\",\"type\":\"").append(type)
                    .append("\"},");
        }
        if (childrenStr.length()>2){
            childrenStr.delete(childrenStr.length()-1, childrenStr.length());
        }
        childrenStr.append(']');
        return "{" +
                "\"id\":\"" + this.nodeId +
                "\",\"name\":\"" + this.nodeName +
                "\",\"type\":\"group\"," +
                "\"property\":"+propertyStr.toString()+
                ",\"list\":"+childrenStr.toString()+
                '}';
    }
}