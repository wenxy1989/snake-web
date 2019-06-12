package com.snake.template.service;
import com.base.service.BasicService;
import com.snake.template.dao.IActionDao;
import com.snake.template.model.Action;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("actionService")
public class ActionService extends BasicService<Action> implements IActionService {
	
	@Resource(name="actionDao")
	private IActionDao dao;

	@Override
	public IActionDao getDao() {
		return dao;
	}

	public List<Action> getListByModuleId(Long moduleId) throws Exception {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("module_id",moduleId);
            return getDao().find(map);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

}
