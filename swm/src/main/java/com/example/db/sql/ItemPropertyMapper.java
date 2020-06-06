package com.example.db.sql;

import com.example.db.core.ItemProperty;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemPropertyMapper {
    public List<ItemProperty> getItemPropertyById(@Param("id") int id);
    public int addItemProperty(ItemProperty itemProperty);
    public void delItemProperty(Map<String,Object> map);
}
