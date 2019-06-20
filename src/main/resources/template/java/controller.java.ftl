package com.${application?uncap_first}.${model.javaName?uncap_first}.controller;

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

import com.${application?uncap_first}.${model.javaName?uncap_first}.model.${model.javaName?cap_first};
import com.${application?uncap_first}.${model.javaName?uncap_first}.service.I${model.javaName?cap_first}Service;

import java.util.List;

/**
 * ${model.javaName }
 * author {author }
**/
@Controller
@RequestMapping("/${model.javaName?uncap_first}")
public class ${model.javaName?cap_first}Controller {
	
	protected static Log logger = LogFactory.getLog(${model.javaName?cap_first}Controller.class);
	
	@Resource(name="${model.javaName?uncap_first}Service")
	private I${model.javaName?cap_first}Service ${model.javaName?uncap_first}Service;
	
	private User loginUser(HttpServletRequest request){
		return (User)request.getSession().getAttribute(Constants.USER_SESSON_KEY);
	}
	
	@RequestMapping(value = "/")
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView("${application?uncap_first}/${model.javaName?uncap_first}/list");
		try{
			List<${model.javaName?cap_first}> list = ${model.javaName?uncap_first}Service.getAll();
			mv.addObject("list", list);
		}catch(ServiceException e){
			logger.error(e);
		}
		return mv;
	}
	
    @RequestMapping(value = "/toCreate")
    public ModelAndView toCreate() {
    	ModelAndView mv = new ModelAndView("${application?uncap_first}/${model.javaName?uncap_first}/add");
        return mv;
    }
	
    @RequestMapping(value = "/toEdit")
    public ModelAndView toEdit(Long id) {
        ModelAndView mv = new ModelAndView("${application?uncap_first}/${model.javaName?uncap_first}/edit");
		try{
	        ${model.javaName?cap_first} ${model.javaName?uncap_first} = ${model.javaName?uncap_first}Service.getObject(id);
	        mv.addObject("${model.javaName?uncap_first}", ${model.javaName?uncap_first});
		}catch(ServiceException e){
			logger.error(e);
		}
        return mv;
    }

	<#list attributes as attribute>
	<#if attribute.useType == 'onetoone'>
    @RequestMapping(value = "/toEditBy${attribute.code?cap_first}")
    public ModelAndView toEditBy${attribute.code?cap_first}(${attribute.javaType} ${attribute.code?uncap_first}) {
    	ModelAndView mv = new ModelAndView("${application?uncap_first}/${model.javaName?uncap_first}/edit");
		try{
	        ${model.javaName?cap_first} ${model.javaName?uncap_first} = ${model.javaName?uncap_first}Service.getObjectBy${attribute.code?cap_first}(id);
	        mv.addObject("${model.javaName?uncap_first}", ${model.javaName?uncap_first});
		}catch(ServiceException e){
			logger.error(e);
		}
        return mv;
    }

    @RequestMapping(value = "deleteBy${attribute.code?cap_first}")
    public RedirectView deleteBy${attribute.code?cap_first}(${attribute.javaType} ${model.javaName?uncap_first}) {
		try{
        	${model.javaName?uncap_first}Service.deleteBy${attribute.code?cap_first}(${model.javaName?uncap_first});
		}catch(ServiceException e){
			logger.error(e);
		}
    	return new RedirectView("/${model.javaName?uncap_first}/");
    }
	</#if>
	</#list>

    @RequestMapping(value = "/create", method = { RequestMethod.POST })
    public RedirectView create(${model.javaName?cap_first} ${model.javaName?uncap_first},HttpServletRequest request) {
    	try{
	    	${model.javaName?uncap_first}.setCreatorId(loginUser(request).getId());
	    	${model.javaName?uncap_first}.setCreatedTime(DateTimeUtils.getDateTime());
	        ${model.javaName?uncap_first}Service.create(${model.javaName?uncap_first});
    	}catch(ServiceException e){
    		logger.error(e);
    	}
    	return new RedirectView("/${model.javaName?uncap_first}/");
    }

    @RequestMapping(value = "/edit", method = { RequestMethod.POST })
    public RedirectView edit(${model.javaName?cap_first} ${model.javaName?uncap_first}) {
		try{
        	${model.javaName?uncap_first}Service.update(${model.javaName?uncap_first});
		}catch(ServiceException e){
			logger.error(e);
		}
    	return new RedirectView("/${model.javaName?uncap_first}/");
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