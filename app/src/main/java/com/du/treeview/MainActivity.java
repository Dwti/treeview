package com.du.treeview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.du.treeview.adapter.SimpleTreeListAdapter;
import com.du.treeview.bean.FileBean;

import java.util.ArrayList;
import java.util.List;

/**
 *  1.原理 listview 的item +paddingleft(level) +expand
    2.把系统的数据bean 转换成node
    3.反射+注解！！！！！
    4.绑定父子关系（设置节点间的关联关系）
    5.过滤可显示的节点数据
 **/

public class MainActivity extends AppCompatActivity {
    private ListView mTree;
    private SimpleTreeListAdapter<FileBean> mAdapter;
    private List<FileBean> mDatas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTree= (ListView) findViewById(R.id.id_listview);
        initDatas();
        try {
            mAdapter=new SimpleTreeListAdapter<FileBean>(mTree,MainActivity.this,mDatas,0);
            mTree.setAdapter(mAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void initDatas() {
        mDatas=new ArrayList<FileBean>();
        FileBean bean=new FileBean(1,0,"第1章有理数");
        mDatas.add(bean);
        bean=new FileBean(2,0,"第2章正式的加减");
        mDatas.add(bean);
        bean=new FileBean(3,0,"第3章一元一次方程");
        mDatas.add(bean);
        bean=new FileBean(4,1,"1.1正式和负数");
        mDatas.add(bean);
        bean=new FileBean(5,1,"1.2有理数");
        mDatas.add(bean);
        bean=new FileBean(6,1,"1.3有理数的加减法");
        mDatas.add(bean);
        bean=new FileBean(7,2,"2.1整式");
        mDatas.add(bean);
        bean=new FileBean(7,2,"2.1整式的加减法");
        mDatas.add(bean);
        bean=new FileBean(7,5,"1.2.1有理数");
        mDatas.add(bean);





    }

}
