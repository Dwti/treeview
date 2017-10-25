package com.du.treeview.utiles;
import com.du.treeview.R;
import com.du.treeview.utiles.annotation.TreeNodeId;
import com.du.treeview.utiles.annotation.TreeNodeLable;
import com.du.treeview.utiles.annotation.TreeNodePid;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by dtt on 2017/5/23.
 */

public class TreeHelper {
    /**
     * 把数据库数据或者sever数据转化成树的数据node
     * @param datas    1
     * @param <T>
     * @throws IllegalAccessException
     * @return
     */
    public static <T> List<Node> convertDatas2Nodes(List<T> datas) throws IllegalAccessException {

        List<Node> nodes=new ArrayList<Node>();
        Node node=null;
        for(T t:datas){
            int id=-1;
            int pid=-1;
            String lable=null;
            node =new Node();
            Class clazz =t.getClass();//声明Class，获取t的class
            Field[] fields=clazz.getDeclaredFields();
            for(Field field:fields){
                if(field.getAnnotation(TreeNodeId.class)!=null){
                    field.setAccessible(true);//强制调用
                    id=field.getInt(t);//抛出异常,反射
                }
                if(field.getAnnotation(TreeNodePid.class)!=null){
                    field.setAccessible(true);//强制调用
                    pid=field.getInt(t);//抛出异常
                }
                if(field.getAnnotation(TreeNodeLable.class)!=null){
                    field.setAccessible(true);//强制调用
                    lable=field.get(t).toString();//抛出异常
                }

            }
            node =new Node(id,pid,lable);
            nodes.add(node);

        }
        /**
         * 设置node间的节点关系
         */
        for(int i=0;i<nodes.size();i++){
            Node n=nodes.get(i);
            for(int j=i+1;j<nodes.size();j++){
                Node m=nodes.get(j);
                if(m.getpId()==n.getId()){
                    n.getChildren().add(m);
                    m.setParent(n);
                }else if(m.getId()==n.getpId()){
                    m.getChildren().add(n);
                    n.setParent(m);
                }

            }

        }
        for(Node n:nodes){
            setNodeIcon(n);
        }
        return nodes;
    }
   public  static <T> List<Node> getSortedNodes(List<T> datas,int defalultExpandLevel) throws IllegalAccessException {
       List<Node> result=new ArrayList<Node>();
       List<Node> nodes=convertDatas2Nodes(datas);
       //获取根节点
       List<Node> rootNodes= getRootNodes(nodes);
       for(Node node:rootNodes){
           addNode(result,node,defalultExpandLevel,1);

       }
       return result;
   }

    /**
     * 把一个节点的所有孩子节点都放到result中去
     * @param result
     * @param node
     * @param defalultExpandLevel
     * @param currentLevel
     */
    private static void addNode(List<Node> result, Node node, int defalultExpandLevel,int currentLevel) {
        result.add(node);
        if(defalultExpandLevel>=currentLevel){
            node.setExpand(true);
        }
        if(node.isLeaf()){
            return;
        }
        for(int i=0;i<node.getChildren().size();i++){
           addNode(result,node.getChildren().get(i),defalultExpandLevel,currentLevel+1);//递归把孩子节点放到result中
        }

    }

    /**
     * 过滤所有的显示（可见的）的节点
     * @param nodes
     * @return
     */
    public static List<Node> filterVisibleNodes(List<Node> nodes){
        List<Node> result=new ArrayList<Node>();
        for(Node node:nodes){
          if(node.isRoot()||node.isParentExpand()){
              setNodeIcon(node);//改变图标
              result.add(node);
          }
        }
        return result;
    }

    /**
     * 从所有的节点中过滤根节点
     * @param nodes
     * @return
     */
    private static List<Node> getRootNodes(List<Node> nodes) {
        List<Node> root=new ArrayList<Node>();
        for(Node node:nodes){
            if(node.isRoot()){
                root.add(node);
            }
        }
        return root;
    }

    /**
     * 设置节点展开收起的图标
     * @param n
     */
    private static void setNodeIcon(Node n) {
        if(n.getChildren().size()>0&&n.isExpand()){
            n.setIcon(R.mipmap.ecpand);
        }else if(n.getChildren().size()>0&&!n.isExpand()){
            n.setIcon(R.mipmap.expand);
        }
        else {
            n.setIcon(-1);//没有图标
        }
    }


}
