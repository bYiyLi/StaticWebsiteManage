package com.example.db.node;

import com.example.db.node.base.AbsNode;
import com.example.db.node.base.NodeType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GroupNode extends AbsNode {
    private final ConcurrentHashMap<Integer,AbsNode> children;
    public GroupNode(Integer nodeId,String nodeName,String property,String url) {
        super(nodeId,nodeName, property,NodeType.GROUP_NODE,url);
        this.children=new ConcurrentHashMap<>();
    }
    public void addNode(AbsNode value){
        children.put(value.nodeId, value);
    }
    public boolean delNode(Integer key){
        return children.remove(key) != null;
    }
    public ConcurrentHashMap<Integer, AbsNode> getChildrenNode() {
        return children;
    }
    @Override
    public String toString() {
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
                "\"property\":"+this.getProperty()+
                ",\"list\":"+childrenStr.toString()+
                '}';
    }
}