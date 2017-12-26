package com.module.novel.model;

import java.util.List;

public interface Element {

	public void setNovelId(Long novelId);
	public Long getNovelId();
    public void setElements(List<Element> elements);
    public List<Element> getElements();
}
