package com.snake.chinese.model.relation;

import com.snake.chinese.model.Element;
import com.snake.chinese.model.Type;

/**
 * 要素-类型关系
 * Created by wen on 2015/8/23.
 */
public class ElementType {
    private Long elementId;
    private Long typeId;
    private Element element;
    private Type type;

    public Long getElementId() {
        return elementId;
    }

    public void setElementId(Long elementId) {
        this.elementId = elementId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
