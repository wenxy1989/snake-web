package com.snake.template.service;


import com.base.exception.ServiceException;
import com.base.service.IBasicService;
import com.snake.template.model.TemplateConfig;

import java.util.List;


public interface ITemplateService extends IBasicService<TemplateConfig> {

    List<TemplateConfig> getListByFrameId(Long groupId) throws ServiceException;
}
