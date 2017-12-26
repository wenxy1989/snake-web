package com.${application?uncap_first}.${module?uncap_first}.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.base.common.Constants;
import com.base.exception.ServiceException;

import com.base.util.DateTimeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.system.user.model.User;

import com.${application?uncap_first}.${module?uncap_first}.model.${module?cap_first};
import com.${application?uncap_first}.${module?uncap_first}.service.I${module?cap_first}Service;

import java.util.List;

/**
 * ${module }
 * author {author }
**/
@Controller
@RequestMapping("/${module?uncap_first}")
public class ${module?cap_first}Controller {
	
	protected static Log logger = LogFactory.getLog(${module?cap_first}Controller.class);
	
	@Resource(name="${module?uncap_first}Service")
	private I${module?cap_first}Service ${module?uncap_first}Service;
	
	private User loginUser(HttpServletRequest request){
		return (User)request.getSession().getAttribute(Constants.USER_SESSON_KEY);
	}
	
	@RequestMapping(value = "/")
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView("${application?uncap_first}/${module?uncap_first}/list");
		try{
			List<${module?cap_first}> list = ${module?uncap_first}Service.getAll();
			mv.addObject("list", list);
		}catch(ServiceException e){
			logger.error(e);
		}
		return mv;
	}
	
    @RequestMapping(value = "/toCreate")
    public ModelAndView toCreate() {
    	ModelAndView mv = new ModelAndView("${application?uncap_first}/${module?uncap_first}/add");
        return mv;
    }
	
    @RequestMapping(value = "/toEdit")
    public ModelAndView toEdit(Long id) {
        ModelAndView mv = new ModelAndView("${application?uncap_first}/${module?uncap_first}/edit");
		try{
	        ${module?cap_first} ${module?uncap_first} = ${module?uncap_first}Service.getObject(id);
	        mv.addObject("${module?uncap_first}", ${module?uncap_first});
		}catch(ServiceException e){
			logger.error(e);
		}
        return mv;
    }

	<#list attributes as attribute>
	<#if attribute.useType == 'onetoone'>
    @RequestMapping(value = "/toEditBy${attribute.code?cap_first}")
    public ModelAndView toEditBy${attribute.code?cap_first}(${attribute.javaType} ${attribute.code?uncap_first}) {
    	ModelAndView mv = new ModelAndView("${application?uncap_first}/${module?uncap_first}/edit");
		try{
	        ${module?cap_first} ${module?uncap_first} = ${module?uncap_first}Service.getObjectBy${attribute.code?cap_first}(id);
	        mv.addObject("${module?uncap_first}", ${module?uncap_first});
		}catch(ServiceException e){
			logger.error(e);
		}
        return mv;
    }

    @RequestMapping(value = "deleteBy${attribute.code?cap_first}")
    public RedirectView deleteBy${attribute.code?cap_first}(${attribute.javaType} ${module?uncap_first}) {
		try{
        	${module?uncap_first}Service.deleteBy${attribute.code?cap_first}(${module?uncap_first});
		}catch(ServiceException e){
			logger.error(e);
		}
    	return new RedirectView("/${module?uncap_first}/");
    }
	</#if>
	</#list>

    @RequestMapping(value = "/create", method = { RequestMethod.POST })
    public RedirectView create(${module?cap_first} ${module?uncap_first},HttpServletRequest request) {
    	try{
	    	${module?uncap_first}.setCreatorId(loginUser(request).getId());
	    	${module?uncap_first}.setCreatedTime(DateTimeUtils.getDateTime());
	        ${module?uncap_first}Service.create(${module?uncap_first});
    	}catch(ServiceException e){
    		logger.error(e);
    	}
    	return new RedirectView("/${module?uncap_first}/");
    }

    @RequestMapping(value = "/edit", method = { RequestMethod.POST })
    public RedirectView edit(${module?cap_first} ${module?uncap_first}) {
		try{
        	${module?uncap_first}Service.update(${module?uncap_first});
		}catch(ServiceException e){
			logger.error(e);
		}
    	return new RedirectView("/${module?uncap_first}/");
    }
    
    <#if actions?exists>
	<#list actions as action>
	/**
	 *${action.name}
	 */
    @RequestMapping(value = "${action.code?lower_case}")
    public ${action.returnType} ${action.code?lower_case}(${action.paramTypes}) {
    <#if action.actionSql?exists>
    	return service.${action.code?lower_case}(${action.paramNames});
    </#if>
    }
    </#list>
    </#if>
    
}