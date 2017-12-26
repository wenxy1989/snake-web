package com.module.novel.model;

import java.io.Serializable;
import java.util.List;

public class Novel extends NovelElement {
	
	private String author;
	private List<Element> elements;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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

	@Override
	public List<Element> getElements() {
		return this.elements;
	}

	@Override
	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

	@Override
	public Long getBelongId() {
		return this.getId();
	}

}
