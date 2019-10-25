package com.zy.common.utils;

public class TreeNode {//为了配合treeUi的返回数据格式要求
	
	private long id;//当前节点id
	private String text;//当前节点名称
	private String state;//节点状态（close,open）
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public TreeNode(long id, String text, String state) {
		super();
		this.id = id;
		this.text = text;
		this.state = state;
	}
	public TreeNode() {
		super();
	}
	
	
	

}
