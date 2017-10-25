package com.du.treeview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.du.treeview.R;
import com.du.treeview.utiles.Node;
import com.du.treeview.utiles.adapter.TreeListViewAdapter;

import java.util.List;

/**
 * Created by dtt on 2017/5/24.
 */

public class SimpleTreeListAdapter <T> extends TreeListViewAdapter {
    public SimpleTreeListAdapter(ListView tree, Context context, List<T> datas, int defalutExpandLevel) throws IllegalAccessException {
        super(tree, context, datas, defalutExpandLevel);
    }

    @Override
    public View getContextView(Node node, int position, View contextView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(contextView==null){
            contextView=mInflater.inflate(R.layout.list_item,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.mIcon= (ImageView) contextView.findViewById(R.id.id_item_icon);
            viewHolder.mContext= (TextView) contextView.findViewById(R.id.id_item_text);
            contextView.setTag(viewHolder);

        }else {
            viewHolder= (ViewHolder) contextView.getTag();
        }
        if(node.getIcon()==-1){
            viewHolder.mIcon.setVisibility(View.INVISIBLE);
        }else{
            viewHolder.mIcon.setVisibility(View.VISIBLE);
            viewHolder.mIcon.setImageResource(node.getIcon());
            }
        viewHolder.mContext.setText(node.getName());
        return contextView;
    }
    private class  ViewHolder{
        ImageView mIcon;
        TextView mContext;

    }
}
