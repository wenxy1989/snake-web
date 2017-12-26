package com.module.node.model;

import java.io.Serializable;
import java.util.List;

import com.base.common.model.AbstractModel;

public class Node extends AbstractModel{
	
	private Long parentId;//父id
	private Long fieldId;//所属域id
	private Long createId;//创建者id
	private String explain;//详解
	private Node parent;//父元素
	private List<Node> childs;//子元素
	public Long getParentId() {
		if(null == parentId){
			parentId = 0l;
		}
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	/**
	 * 当域值为空或0时，设置域值为自身id
	 * @return
	 */
	public Long getFieldId() {
		if(null == fieldId || fieldId.equals(0l)){
			fieldId = super.getId();
		}
		return fieldId;
	}
	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}
	public Long getCreateId() {
		return createId;
	}
	public void setCreateId(Long createId) {
		if(null == createId){
			
		}
		this.createId = createId;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public List<Node> getChilds() {
		return childs;
	}
	public void setChilds(List<Node> childs) {
		this.childs = childs;
	}
	@Override
	public Serializable getObjectId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getUniqueIdName() {
		// TODO Auto-generated method stub
		return null;
	}

}