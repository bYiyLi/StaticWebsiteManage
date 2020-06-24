package com.example.db.core;

public class NodeMapper {
    private int pid;
    private int cid;

    public NodeMapper(int pid, int cid) {
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
