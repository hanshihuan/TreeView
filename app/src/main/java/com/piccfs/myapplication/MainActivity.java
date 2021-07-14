package com.piccfs.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.piccfs.myapplication.adapter.SimpleTreeViewAdapter;
import com.piccfs.myapplication.domain.FileBean;
import com.piccfs.myapplication.domain.Node;
import com.piccfs.myapplication.domain.OrgBean;
import com.piccfs.myapplication.utils.TreeViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private SimpleTreeViewAdapter<FileBean> adapter;
    private List<FileBean> datas;

    private List<OrgBean> datas2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    public void init(){
        lv =  findViewById(R.id.listview);
        initData();

    }
    public void initData(){
        datas = new ArrayList();
        FileBean file = new FileBean(1, 0, "根目录1");
        datas.add(file);
        file = new FileBean(2, 0, "根目录2");
        datas.add(file);
        file = new FileBean(3, 0, "根目录3");
        datas.add(file);
        file = new FileBean(4, 1, "根目录1-1");
        datas.add(file);
        file = new FileBean(5, 1, "根目录1-2");
        datas.add(file);
        file = new FileBean(6, 2, "根目录2-1");
        datas.add(file);
        file = new FileBean(7, 6, "根目录2-1-1");
        datas.add(file);
        file = new FileBean(8, 3, "根目录3-1");
        datas.add(file);
        file = new FileBean(9, 3, "根目录3-2");
        datas.add(file);
        file = new FileBean(10, 9, "根目录3-2-1");
        datas.add(file);




       /* datas2 = new ArrayList();
        OrgBean org = new OrgBean(1, 0, "目录1");
        datas2.add(org);
        org = new OrgBean(2, 0, "目录2");
        datas2.add(org);
        org = new OrgBean(3, 0, "目录3");
        datas2.add(org);
        org = new OrgBean(4, 1, "目录1-1");
        datas2.add(org);
        org = new OrgBean(5, 1, "目录1-2");
        datas2.add(org);
        org = new OrgBean(6, 2, "目录2-1");
        datas2.add(org);
        org = new OrgBean(7, 6, "目录2-1-1");
        datas2.add(org);
        org = new OrgBean(8, 3, "目录3-1");
        datas2.add(org);
        org = new OrgBean(9, 3, "目录3-2");
        datas2.add(org);
        org = new OrgBean(10, 9, "目录3-2-1");
        datas2.add(org);*/
        try {
            if(datas == null){
                return;
            }
            adapter = new SimpleTreeViewAdapter(lv, MainActivity.this, datas, 0);
            lv.setAdapter(adapter);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        adapter.setClickListener(new TreeViewAdapter.setOnitemClickListener() {

            @Override
            public void onClick(Node node, int position) {
                // TODO Auto-generated method stub
                Toast.makeText(MainActivity.this, node.getLable(), Toast.LENGTH_SHORT).show();
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           final int position, long id) {
                // TODO Auto-generated method stub
                final EditText et = new EditText(MainActivity.this);
                new AlertDialog.Builder(MainActivity.this).setTitle("Add Node").setView(et).setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        if(!TextUtils.isEmpty(et.getText().toString())){

                            adapter.addExtraNode(position,et.getText().toString());
                        }
                    }
                }).setNeutralButton("取消", null).show();
                return true;
            }
        });
    }



}

