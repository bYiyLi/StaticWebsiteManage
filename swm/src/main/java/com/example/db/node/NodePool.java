package com.example.db.node;
import com.example.db.core.Group;
import com.example.db.core.Node;
import com.example.db.node.base.NodeType;
import com.example.db.sql.DbNodeMapper;
import com.example.db.sql.SqlFactory;
import com.example.db.node.base.AbsNode;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
@Service("nodePool")
public class NodePool {
    final
    SqlFactory sqlFactory;
    private final ConcurrentHashMap<Integer, AbsNode> nodes;
    public NodePool(SqlFactory sqlFactory) {
        this.nodes = new ConcurrentHashMap<>();
        this.sqlFactory = sqlFactory;
    }
    private AbsNode initNode(Integer nodeId){
        return complementNode(getNodeByDb(nodeId));
    }
    private AbsNode complementNode(AbsNode oldNode){
        if (oldNode.getType().equals(NodeType.GROUP_NODE)){
            SqlSessionFactory factory = sqlFactory.getFactory();
            try (SqlSession sqlSession = factory.openSession()) {
                DbNodeMapper mapper = sqlSession.getMapper(DbNodeMapper.class);
                List<Integer> allChild = mapper.getAllChildById(oldNode.nodeId);
                allChild.forEach((c)->{
                    AbsNode cNode;
                    {
                        AbsNode t1=getNodeByMap(c);
                        if (t1==null){
                            cNode=getNodeByDb(c);
                        }else{
                            cNode=t1;
                        }
                    }
                    ((GroupNode)oldNode).addNode(cNode);
                });
            }
        }
        oldNode.setState("complement");
        return oldNode;
    }
    private AbsNode getNodeByMap(int nodeId){
        return this.nodes.get(nodeId);
    }
    private AbsNode getNodeByDb(int nodeId){
        AbsNode newAbsNode;
        SqlSessionFactory factory = sqlFactory.getFactory();
        try (SqlSession sqlSession = factory.openSession()){
            DbNodeMapper mapper = sqlSession.getMapper(DbNodeMapper.class);
            Node newNode = mapper.getNodeById(nodeId);
            if (newNode.getType().equals("group")){
                newAbsNode = new GroupNode(newNode.getId(), newNode.getName(), newNode.getProperty(), newNode.getUrl());
                this.nodes.put(newAbsNode.nodeId,newAbsNode);
            }else if (newNode.getType().equals("md")){
                newAbsNode=new MdNode(newNode.getId(),newNode.getName(),newNode.getProperty(),newNode.getUrl());
                this.nodes.put(newAbsNode.nodeId,newAbsNode);
            }else {
                newAbsNode=null;
            }
        }
        return newAbsNode;
    }

    public AbsNode getNode(Integer nodeId){
        AbsNode node =getNodeByMap(nodeId);
        if (node==null){
            return initNode(nodeId);
        }else if (node.getState().equals("init")){
            return complementNode(node);
        }else {
            return node;
        }
    }
}
