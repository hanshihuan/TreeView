package com.piccfs.myapplication.utils;

import com.piccfs.myapplication.R;
import com.piccfs.myapplication.annntation.NodeId;
import com.piccfs.myapplication.annntation.NodeLable;
import com.piccfs.myapplication.annntation.NodePId;
import com.piccfs.myapplication.domain.Node;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class TreeHelper {
	public static <T> List<Node> convertDatas2Nodes(List<T>datas) throws IllegalAccessException, IllegalArgumentException{
		List<Node> nodes = new ArrayList();
		Node node = null;

		for(T t :datas){
			node = new Node();
			//反射+注解
			Class clazz = t.getClass();
			int id = -1;
			int Pid = -1;
			String lable = null;
			Field [] fields = clazz.getDeclaredFields();
			for(Field field:fields){
				if(field.getAnnotation(NodeId.class)!=null){
					field.setAccessible(true);//访问权限
					id = field.getInt(t);
				}
				if(field.getAnnotation(NodePId.class)!=null){
					field.setAccessible(true);//访问权限
					Pid = field.getInt(t);
				}
				if(field.getAnnotation(NodeLable.class)!=null){
					field.setAccessible(true);//访问权限
					lable = (String) field.get(t);
				}
			}
			node = new Node(id, Pid, lable);
			nodes.add(node);
		}

		//找到每一个note的父节点和子节点
		for(int i = 0;i<nodes.size();i++){
			Node n = nodes.get(i);
			for(int j =i+1;j<nodes.size();j++){
				Node m = nodes.get(j);
				if(m.getIPid() == n.getId()){
					n.getChildren().add(m);
					m.setParent(n);
				}else if(m.getId() == n.getIPid()){
					m.getChildren().add(n);
					n.setParent(m);
				}
			}
		}
		//设置图标
		for(Node n: nodes){
			setNodeIcon(n);
		}

		return nodes;
	}
	public static <T> List<Node>getSortedNodes(List<T>datas,int defaultExpandLevel) throws IllegalAccessException, IllegalArgumentException{
		List<Node>result = new ArrayList();
		List<Node>nodes = convertDatas2Nodes(datas);
		List<Node> roots = getRootNodes(nodes);
		for(Node node:roots){
			addNode(result,node,defaultExpandLevel,1);
		}
		return result;

	}
	/**
	 * 排序：把一个节点的所有子节点放到result中
	 * @param result
	 * @param node
	 * @param defaultExpandLevel
	 * @param i
	 */
	private static void addNode(List<Node> result, Node node,int defaultExpandLevel, int currentLevel) {
		// TODO Auto-generated method stub
		result.add(node);
		if(defaultExpandLevel>= currentLevel){
			node.setOpen(true);
		}
		if(node.isLeaf()){
			return;
		}
		for(int i = 0;i<node.getChildren().size();i++){
			addNode(result, node.getChildren().get(i), defaultExpandLevel, currentLevel+1);
		}

	}
	private static List<Node> getRootNodes(List<Node> nodes) {
		// TODO Auto-generated method stub
		List<Node>roots = new ArrayList();
		for(Node node:nodes){
			if(node.isRoot()){
				roots.add(node);
			}
		}
		return roots;
	}

	public static List<Node>filterVisibleNodes(List<Node>nodes){
		List<Node> result = new ArrayList();
		for(Node node:nodes){
			if(node.isRoot()||node.isParentOpen()){
				setNodeIcon(node);
				result.add(node);
			}
		}
		return result;
	}
	private static void setNodeIcon(Node n) {
		// TODO Auto-generated method stub
		if(n.getChildren().size()>0&&n.isOpen()){
			n.setIcon(R.drawable.tree_ex);
		}else if(n.getChildren().size()>0&& !n.isOpen()){
			n.setIcon(R.drawable.tree_ec);
		}else{
			n.setIcon(-1);
		}

	}
}
