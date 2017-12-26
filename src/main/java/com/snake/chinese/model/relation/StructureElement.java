package com.snake.chinese.model.relation;

import com.snake.chinese.model.Element;
import com.snake.chinese.model.Structure;

/**
 * 语句结构-元素关联关系
 * Created by wen on 2015/8/23.
 */
public class StructureElement {

    private Long id;
    private Long structureId;
    private Long elementId;
    private Integer order;

    private Structure structure;
    private Element element;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStructureId() {
        return structureId;
    }

    public void setStructureId(Long structureId) {
        this.structureId = structureId;
    }

    public Long getElementId() {
        return elementId;
    }

    public void setElementId(Long elementId) {
        this.elementId = elementId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }
}
