package com.piccfs.myapplication.domain;

import com.piccfs.myapplication.annntation.NodeId;
import com.piccfs.myapplication.annntation.NodeLable;
import com.piccfs.myapplication.annntation.NodePId;

import java.util.ArrayList;


public class Node {

	public Node() {

	}

	public Node(int id, int iPid, String lable) {
		super();
		this.id = id;
		IPid = iPid;
		this.lable = lable;
	}
	@NodeId
	private int id;
	@NodePId
	private int IPid;
	@NodeLable
	private String lable;
	private boolean isOpen;
	private int level;
	private int icon;
	private Node parent;
	private ArrayList<Node> children = new ArrayList<Node>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIPid() {
		return IPid;
	}
	public void setIPid(int iPid) {
		IPid = iPid;
	}
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
	public boolean isOpen() {
		return isOpen;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
		if(!isOpen){
			for(Node node: children){
				node.setOpen(false);
			}
		}
	}
	/**
	 * 得到节点层级
	 * @return
	 */
	public int getLevel() {
		return parent == null ? 0:parent.getLevel()+1;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public ArrayList<Node> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<Node> children) {
		this.children = children;
	}
	/**
	 * 判断当前节点是否是根节点
	 */
	public boolean isRoot(){
		return parent == null;
	}
	/**
	 * 判断父节点是否是展开的
	 */
	public boolean isParentOpen(){
		if(parent == null){
			return false;
		}
		return parent.isOpen();
	}
	/**
	 * 判断是否是叶子节点
	 */
	public boolean isLeaf(){
		return children.size() == 0;
	}

}
