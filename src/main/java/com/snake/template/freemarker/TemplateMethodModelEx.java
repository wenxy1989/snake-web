package com.snake.template.freemarker;

import com.base.util.StringTools;
import com.snake.template.dao.FrameDao;
import freemarker.template.TemplateModelException;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by HP on 2016/12/8.
 */
public class TemplateMethodModelEx implements freemarker.template.TemplateMethodModelEx {
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
                if ("template_frame_list".equals(argument2)) {
                    result = FrameDao.getFinalList();
                }
            } else if ("get".equals(command) && StringUtils.isNotBlank(argument2) && StringUtils.isNotBlank(argument3)) {
                Long id = Long.valueOf(argument3);
                if ("template_frame".equals(argument2)) {
                    result = FrameDao.getFinalObject(id);
                }
            } else if ("replace_model".equals(command) && StringUtils.isNotBlank(argument2) && StringUtils.isNotBlank(argument3)) {
                result = StringTools.parsePath(argument2,"app", argument3);
            }
        }
        return result;
    }
}
