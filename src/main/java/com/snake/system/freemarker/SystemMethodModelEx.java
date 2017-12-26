package com.snake.system.freemarker;

import com.base.Constants;
import com.snake.system.security.MySecurityMetadataSource;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by wen on 2015/7/14.
 */
public class SystemMethodModelEx implements TemplateMethodModelEx {

    public Object exec(List arguments) throws TemplateModelException {
        Object result = null;
        if(null != arguments && arguments.size() > 0){
            String command = arguments.get(0).toString();
            String argument2 = null;
            String argument3 = null;
            if(arguments.size() > 1){
                argument2 = arguments.get(1)+"";
            }
            if(arguments.size() > 2){
                argument3 = arguments.get(2)+"";
            }
            if("functionList".equals(command)){
                result = MySecurityMetadataSource.getFunctionList();
            }else if("check_security".equals(command)){
                String function = argument2;
                String role = argument3;
                if(StringUtils.isNotBlank(role) && StringUtils.isNotBlank(function)){
                    result = Constants.ROLE_ADMIN.equals(role) || MySecurityMetadataSource.checkUrl(function,role);
                }else{
                    result = false;
                }
            }else if("check_security_id".equals(command)){
                Long functionId = Long.valueOf(argument2);
                Long roleId = Long.valueOf(argument3);
                if(roleId > 0 && functionId > 0){
                    result = 1== roleId || MySecurityMetadataSource.checkId(functionId, roleId);
                }else{
                    result = false;
                }
            }
        }
        return result;
    }
}
