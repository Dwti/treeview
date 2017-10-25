package com.du.treeview.utiles.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.du.treeview.utiles.Node;
import com.du.treeview.utiles.TreeHelper;

import java.util.List;

/**
 * 设置为抽象的类，不必要重复写代码
 * Created by dtt on 2017/5/24.
 */

public abstract class TreeListViewAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<Node> mAllNodes;
    protected List<Node> mVisibleNodes;
    protected LayoutInflater mInflater;
    protected ListView mTree;

    /**
     * 定义接口，共外部使用,设置node的点击回调
     */
   public interface OnTreeNodeClickListener{
       void onClick(Node node,int position);
   }
   private OnTreeNodeClickListener mLister;

    public void setmLister(OnTreeNodeClickListener mLister) {
        this.mLister = mLister;
    }

    public TreeListViewAdapter(ListView tree, Context  context, List<T> datas, int defalutExpandLevel) throws IllegalAccessException {
        mContext=context;
        mInflater=LayoutInflater.from(mContext);
        mAllNodes= TreeHelper.getSortedNodes(datas,defalutExpandLevel);
        mVisibleNodes=TreeHelper.filterVisibleNodes(mAllNodes);
        mTree=tree;
        mTree.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                expandOrCollapse(position);
                if(mLister!=null){
                    mLister.onClick(mVisibleNodes.get(position),position);
                }
            }
        });

    }

    /**
     * 点击收缩或者展开
     * @param position
     */
    private void expandOrCollapse(int position) {
        Node n=mVisibleNodes.get(position);
        if(n!=null){
            if(n.isLeaf())return;
            n.setExpand(!n.isExpand());
            mVisibleNodes=TreeHelper.filterVisibleNodes(mAllNodes);
            notifyDataSetChanged();//通知数据改变
        }

    }
//获取个题count呢？？？就是没回去所有没有结果
    @Override
    public int getCount() {
        return mVisibleNodes.size();
    }

    @Override
    public Object getItem(int position) {

//        return null;
        return mVisibleNodes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Node node =mVisibleNodes.get(position);
        convertView=getContextView(node,position,convertView,parent);
        //设置内边距
        convertView.setPadding(node.getLevel()*30,3,3,3);

        return convertView;
    }
    //抽象的方法
    public abstract View getContextView(Node node,int position,View contextView,ViewGroup parent);


}
