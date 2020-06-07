package com.example.db.api;
import com.example.db.core.Group;
public interface GroupDb {
    /**
     * 通过id获取某个组的内容
     * @param id
     * @return
     */
    public Group getGroupById(int id);

    /**
     * 通过id删除组下所有内容
     * @param id
     */
    public void delGroupById(int id);

    /**
     * 设置组的名字
     * @param id
     * @param name
     */
    public void setGroupName(int id,String name);

    /**
     * 设置组的显示url地址
     * @param id
     * @param url
     */
    public void setGroupUrl(int id,String url);

    /**
     * 添加组
     * @param name
     * @param url
     */
    public void addGroup(String name,String url);

    /**
     * 添加组的属性
     * @param id
     * @param content
     */
    public void addGroupProperty(int id,String content);

    /**
     * 删除组的属性
     * @param id
     * @param content
     */
    public void delGroupProperty(int id,String content);

    /**
     * 添加组中的子数据
     * @param id
     * @param cid
     */
    public boolean addChildren(int id,String type,int cid);

    /**
     * 删除组的子数据
     * @param id
     * @param cid
     */
    public void delChildren(int id,int cid);
}
