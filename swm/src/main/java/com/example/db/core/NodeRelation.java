package com.example.db.core;

public class NodeRelation {
    private int pid;
    private int cid;

    public NodeRelation(int pid, int cid) {
        this.pid = pid;
        this.cid = cid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
