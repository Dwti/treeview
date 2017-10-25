package com.du.treeview.bean;

import com.du.treeview.utiles.annotation.TreeNodeId;
import com.du.treeview.utiles.annotation.TreeNodeLable;
import com.du.treeview.utiles.annotation.TreeNodePid;

/**
 * Created by dutt on 2017/5/23.
 */

public class FileBean {
    @TreeNodeId
    private int id;
    @TreeNodePid
    private int pid;//父id
    @TreeNodeLable
    private  String lable;
    private String desc;

    public FileBean(int id, int pid, String lable) {
        this.id = id;
        this.pid = pid;
        this.lable = lable;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {

        return id;
    }

    public int getPid() {
        return pid;
    }

    public String getLable() {
        return lable;
    }

    public String getDesc() {
        return desc;
    }
}
