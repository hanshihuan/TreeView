package com.piccfs.myapplication.domain;


import com.piccfs.myapplication.annntation.NodeId;
import com.piccfs.myapplication.annntation.NodeLable;
import com.piccfs.myapplication.annntation.NodePId;

public class FileBean {
	public FileBean(int id, int pId, String name) {
		super();
		this.id = id;
		PId = pId;
		this.name = name;
	}
	@NodeId
	private int id;
	@NodePId
	private int PId;
	@NodeLable
	private String name;

}
