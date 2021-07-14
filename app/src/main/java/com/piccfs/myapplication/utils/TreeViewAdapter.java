package com.piccfs.myapplication.utils;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.piccfs.myapplication.domain.Node;


public abstract class TreeViewAdapter <T>extends BaseAdapter{
	protected Context context;
	protected List<Node> mAllNotes;
	protected List<Node> mVisibleNodes;
	protected LayoutInflater mInflater;
	protected ListView listview;
	//设置note的点击回调
	public setOnitemClickListener listener;
	public interface setOnitemClickListener{
		 void onClick(Node node,int position);
	}
	public void setClickListener(setOnitemClickListener listener) {
		this.listener = listener;
	}

	public TreeViewAdapter(ListView listview,Context context,List<T> datas,int defaultExpendLevel) throws IllegalAccessException, IllegalArgumentException{
		this.context = context;
		this.mAllNotes = TreeHelper.getSortedNodes(datas, defaultExpendLevel);
		this.mVisibleNodes = TreeHelper.filterVisibleNodes(mAllNotes);
		mInflater = LayoutInflater.from(context);
		this.listview = listview;
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				// TODO Auto-generated method stub
				expandOrCollapse(position);
				if(listener != null){
					listener.onClick(mVisibleNodes.get(position),position);
				}
			}


		});

	}

	private void expandOrCollapse(int position) {
		// TODO Auto-generated method stub
		Node n = mVisibleNodes.get(position);
		if(n!=null){
			if(n.isLeaf())return;
			n.setOpen(!n.isOpen());
			mVisibleNodes = TreeHelper.filterVisibleNodes(mAllNotes);
			notifyDataSetChanged();
		}

	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(mVisibleNodes == null)
			return 0;
		return mVisibleNodes.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub

		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Node  node = mVisibleNodes.get(position);
		convertView = getConvertView(node, position, convertView, parent);
		convertView.setPadding(node.getLevel()*30, 3, 3, 3);
		return convertView;
	}
	public abstract View getConvertView(Node node,int position,View converView,ViewGroup parent);


}
