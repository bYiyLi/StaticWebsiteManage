package com.example.db.node;


import com.example.db.node.base.AbsNode;
import com.example.db.node.base.NodeType;

public class MdNode extends AbsNode {
    public MdNode(Integer nodeId,String nodeName,String property,String url) {
        super(nodeId,nodeName,property,NodeType.MD_NODE,url);
    }
}
