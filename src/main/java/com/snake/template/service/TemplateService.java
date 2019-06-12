package com.snake.template.service;
import com.base.service.BasicService;
import com.base.util.HashMaps;
import com.snake.template.dao.ITemplateDao;
import com.snake.template.model.TemplateConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("templateService")
public class TemplateService extends BasicService<TemplateConfig> implements ITemplateService {

    @Resource(name = "templateDao")
    private ITemplateDao dao;

    @Override
    public ITemplateDao getDao() {
        return dao;
    }

    @Override
    public List<TemplateConfig> getListByFrameId(Long frameId) throws Exception {
        return getDao().selectListByFrameId(frameId);
    }
}
