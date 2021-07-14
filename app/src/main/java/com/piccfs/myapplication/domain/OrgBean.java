package com.piccfs.myapplication.domain;


import com.piccfs.myapplication.annntation.NodeId;
import com.piccfs.myapplication.annntation.NodeLable;
import com.piccfs.myapplication.annntation.NodePId;

public class OrgBean {
	
	public OrgBean(int ip, int pIp, String name) {
		super();
		this.ip = ip;
		PIp = pIp;
		this.name = name;
	}
	@NodeId
	private int ip;
	@NodePId
	private int PIp;
	@NodeLable
	private String name;

}
