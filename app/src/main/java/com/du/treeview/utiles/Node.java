package com.du.treeview.utiles;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dtt on 2017/5/23.
 */

public class Node {
    private int id;
    private int pId=0;//根节点默认是0
    private String name;
    private int level;//树的层级
    private boolean isExpand=false;//是否是展开的
    private int icon;

    private Node parent;
    private List<Node> children=new ArrayList<Node>();//初始化，避免空指针

    public Node(){}
    /**
     * 有参的构造方法

     * @param id
     * @param pId
     * @param name
     */
    public Node(int id, int pId, String name) {
        this.id = id;
        this.pId = pId;
        this.name = name;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return id;
    }


    public void setpId(int pId) {
        this.pId = pId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }



    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public int getpId() {

        return pId;
    }

    public String getName() {
        return name;
    }

    /**
     * 得到当前节点的层级
     * 获得level的一个值，如果父节点是null的就返回0
     * 如果不是空的就获取level+1，儿子几点的level
     * @return
     */
    public int getLevel() {
        return parent==null?0:parent.getLevel()+1;
    }


    public int getIcon() {
        return icon;
    }

    public Node getParent() {
        return parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    /**
     * 把子节点 if(field.getAnnotation(TreeNodeId.class)!=null){
     field.setAccessible(true);//强制调用
     id=field.getInt(t);//抛出异常
     }
     * @param isExpand
     */
    public void setExpand(boolean isExpand) {
        this.isExpand = isExpand;
        if(!isExpand){
            for(Node node:children){
                node.setExpand(false);
            }

        }
    }

    /**
     * 判断是否是根节点
     * @return
     */
    public  boolean isRoot(){
     return  parent==null;
    }

    public boolean isExpand() {

        return isExpand;
    }

    /**
     * 判断父节点是否展开的状态
     * @return
     */
    public boolean isParentExpand(){
        if(parent==null)
            return  false;

        return parent.isExpand();
    }

    /**
     * 判断是否是子节点
     * @return
     */
    public  boolean isLeaf(){
        return  children.size()==0;
    }


}
