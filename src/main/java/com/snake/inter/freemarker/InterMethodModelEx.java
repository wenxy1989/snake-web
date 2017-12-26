package com.snake.inter.freemarker;

import com.snake.inter.dao.GroupDao;
import com.snake.inter.dao.ModelDao;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by HP on 2016/12/8.
 */
public class InterMethodModelEx implements TemplateMethodModelEx {
    public Object exec(List arguments) throws TemplateModelException {
        Object result = null;
        if (null != arguments && arguments.size() > 0) {
            String command = arguments.get(0).toString();
            String argument2 = null;
            if (arguments.size() > 1) {
                argument2 = arguments.get(1) + "";
            }
            String argument3 = null;
            if (arguments.size() > 2) {
                argument3 = arguments.get(2) + "";
            }
            if ("resource".equals(command) && StringUtils.isNotBlank(argument2)) {
                if ("inter_group_list".equals(argument2)) {
                    result = GroupDao.getFinalList();
                } else if ("inter_model_list".equals(argument2)) {
                    result = ModelDao.getFinalList();
                } else if ("inter_model_map".equals(argument2)) {
                    result = ModelDao.getFinalMap();
                } else if ("inter_group_map".equals(argument2)) {
                    result = GroupDao.getFinalMap();
                }
            }else if("get".equals(command) && StringUtils.isNotBlank(argument2) && StringUtils.isNotBlank(argument3)){
                Long id = Long.valueOf(argument3);
                if ("inter_group".equals(argument2)) {
                    result = GroupDao.getFinalObject(id);
                } else if ("inter_model".equals(argument2)) {
                    result = ModelDao.getFinalObject(id);
                }
            }
        }
        return result;
    }
}
