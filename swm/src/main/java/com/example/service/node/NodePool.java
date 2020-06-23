package com.example.service.node;
import com.example.service.node.base.AbsNode;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
@Service("nodePool")
public class NodePool {
    private final ConcurrentHashMap<Integer, AbsNode> nodes;
    public NodePool() {
        this.nodes = new ConcurrentHashMap<>();
    }
    private AbsNode initNode(Integer nodeId){

        return null;
    }
    public AbsNode getNode(Integer nodeId){
        AbsNode node = nodes.get(nodeId);
        if (node==null){
            return initNode(nodeId);
        }else{
            return node;
        }
    }
}
