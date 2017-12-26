package com.module.novel.model;

import com.base.common.model.AbstractModel;

import java.io.Serializable;
import java.util.List;

public class NovelElement extends AbstractModel implements Element {
	
	private Long belongId;//所属范型id
	private String path;//元素访问路径

    public Long getBelongId() {
        return belongId;
    }

    public void setBelongId(Long belongId) {
        this.belongId = belongId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Element> getElements() {
        return null;
    }

    public void setElements(List<Element> elements) {

    }

    public Long getNovelId() {
        return null;
    }

    public void setNovelId(Long novelId) {

    }

    @Override
    public Serializable getObjectId() {
        return null;
    }

    @Override
    public String getUniqueIdName() {
        return null;
    }
}
