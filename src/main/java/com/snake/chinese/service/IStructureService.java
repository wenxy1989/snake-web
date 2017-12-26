package com.snake.chinese.service;

import com.base.exception.ServiceException;
import com.base.service.IBasicService;
import com.snake.chinese.model.Structure;
import com.snake.chinese.model.relation.StructureElement;

import java.util.List;

/**
 * Created by wen on 2015/5/1.
 */
public interface IStructureService extends IBasicService<Structure> {
    List<StructureElement> getStructureElementListByStructureId(Long id) throws ServiceException;

    void addStructureElement(StructureElement obj) throws ServiceException;

    void removeStructureElement(StructureElement obj) throws ServiceException;
}
