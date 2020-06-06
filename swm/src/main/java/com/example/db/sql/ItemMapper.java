package com.example.db.sql;

import com.example.db.core.Item;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface ItemMapper {
    public Item getItemById(@Param("id") int id);
    public void addItem(Item item);
    public void delItemById(@Param("id") int id);
    public void setItem(@Param("id") int id, @Param("param") Map<String,Object> map);
}
