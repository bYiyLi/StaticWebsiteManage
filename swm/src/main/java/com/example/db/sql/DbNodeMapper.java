package com.example.db.sql;

import com.example.db.core.Node;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DbNodeMapper {
    Node getNodeById(@Param("id") int id);
    int setNodeByMap(@Param("id")int id,@Param("map") Map<String,Object> map);
    void delNodeById(@Param("id") int id);
    int addNode(@Param("node")Node node);
    List<Integer> getAllChildById(@Param("pid") int id);
}
