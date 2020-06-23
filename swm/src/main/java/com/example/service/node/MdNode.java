package com.example.service.node;


import com.example.service.node.base.AbsNode;
import com.example.service.node.base.NodeType;

public class MdNode extends AbsNode {
    public MdNode(Integer nodeId,String nodeName) {
        super(nodeId,nodeName,NodeType.MD_NODE);
    }
}
