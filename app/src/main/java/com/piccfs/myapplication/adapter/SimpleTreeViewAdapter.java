package com.piccfs.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.piccfs.myapplication.R;
import com.piccfs.myapplication.domain.Node;
import com.piccfs.myapplication.utils.TreeHelper;
import com.piccfs.myapplication.utils.TreeViewAdapter;

import java.util.List;

public class SimpleTreeViewAdapter<T> extends TreeViewAdapter<T> {
	private ViewHolder holder;
	

	public SimpleTreeViewAdapter(ListView listview, Context context,
			List<T> datas, int defaultExpendLevel)
			throws IllegalAccessException, IllegalArgumentException {
		super(listview, context, datas, defaultExpendLevel);
	}

	@Override
	public View getConvertView(Node node, int position, View converView,
							   ViewGroup parent) {
		holder = null;
		if(converView == null){
			converView = mInflater.inflate(R.layout.item_adapter, parent,false);
			holder = new ViewHolder();
			holder.iv_icon =  converView.findViewById(R.id.iv_icon);
			holder.tv_lable =  converView.findViewById(R.id.tv_lable);
			converView.setTag(holder);
		}else{
			holder = (ViewHolder) converView.getTag();
		}
		if(node.getIcon() == -1){
			holder.iv_icon.setVisibility(View.INVISIBLE);
		}else{
			holder.iv_icon.setVisibility(View.VISIBLE);
			holder.iv_icon.setImageResource(node.getIcon());
		}
		holder.tv_lable.setText(node.getLable());
		
		return converView;
	}
	private class ViewHolder{
		private TextView tv_lable;
		private ImageView iv_icon;
	}
	/**
	 * 动态插入节点
	 * @param position
	 * @param string
	 */
	public void addExtraNode(int position, String string)
	{
		Node node = mVisibleNodes.get(position);
		int indexOf = mAllNotes.indexOf(node);
		// Node
		Node extraNode = new Node(-1, node.getId(), string);
		extraNode.setParent(node);
		node.getChildren().add(extraNode);
		mAllNotes.add(indexOf + 1, extraNode);

		mVisibleNodes = TreeHelper.filterVisibleNodes(mAllNotes);
		notifyDataSetChanged();

	}

}
