package com.base.common.controller;

import com.base.common.model.AbstractModel;
import com.base.common.service.BaseService;
import com.base.utils.TimeTools;
import com.module.system.service.IParameterService;
import com.module.system.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public abstract class AbstractController<T extends AbstractModel> {
	
	protected static final String RESULT_SUCCESS = "success";
	protected static final String RESULT_ERROR = "error";
	
	protected static Log log = LogFactory.getLog(AbstractController.class);
	
//	private String basePath = null;
//	private String indexRedirect = null;
	
//	public int getInt(String name){
//		String value = request.getParameter(name);
//		value = (String)request.getAttribute(name);
//		int number = 0;
//		if(null != value){
//			try{
//				number = Integer.parseInt(value);
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//		}
//		return number;
//	}
	
//	public String getBasePath(HttpServletRequest request){
//		if(null != request && null == basePath){
//			StringBuffer sb = new StringBuffer(request.getScheme());
//			sb.append("://");
//			sb.append(request.getServerName());
//			sb.append(":");
//			sb.append(request.getServerPort());
//			sb.append(request.getContextPath());
//			basePath = sb.toString();
//		}
//		return basePath;
//	}

	
	protected abstract Class<T> getClassTemplate();
	protected abstract BaseService<T> getService();
	protected abstract IParameterService getParameterService();
	protected abstract String getJspBasePath();
	protected abstract String getBranchName();
	
	public static User sysUser(){
		User user = null;
		try{
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(principal instanceof UserDetails){
				user = (User)principal;
			}else{
				user = new User();
				user.setName(principal.toString());
			}
		}catch(Exception e){
			log.error(e);
			e.printStackTrace();
		}
    	return user;
	}
	
	protected StringBuffer getBaseUrl(HttpServletRequest request){
		StringBuffer sb = new StringBuffer("redirect:");
		if(null != request){
			sb.append(request.getScheme());
			sb.append("://");
			sb.append(request.getServerName());
			sb.append(":");
			sb.append(request.getServerPort());
			sb.append(request.getContextPath());
			sb.append("/");
		}
		return sb;
	}
	
	protected RedirectView getIndexRedirect(){
		return new RedirectView("list.do");
	}
	
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView(this.getJspBasePath() + "list");
		List<T> list = this.getService().getAll(this.getClassTemplate());
		mv.addObject("objectList", list);
		return mv;
	}
	
	public ModelAndView list(T object){
		ModelAndView mv = new ModelAndView(this.getJspBasePath() + "list");
		List<T> list = this.getService().getList(object);
		mv.addObject("objectList", list);
		mv.addObject("object",object);
		return mv;
	}
	
    public ModelAndView toCreate() {
    	ModelAndView mv = new ModelAndView(this.getJspBasePath() + "create");
        return mv;
    }
	
    public ModelAndView toCreate(T object) {
    	ModelAndView mv = new ModelAndView(this.getJspBasePath() + "create");
    	mv.addObject("object", object);
        return mv;
    }
    
    public ModelAndView toEdit(Long id) {
        ModelAndView mv = new ModelAndView(this.getJspBasePath() + "edit");
        T object = this.getService().getObject(this.getClassTemplate(),id);
        mv.addObject("object", object);
        return mv;
    }
    
    public RedirectView create(T object){
    	try{
	    	object.setCreateId(sysUser().getId());
	    	object.setCreateTime(TimeTools.now());
	        this.getService().create(object);
    	}catch(Exception e){
    		log.error(e);
    		e.printStackTrace();
    	}
        return new RedirectView("list.do");
    }

    public RedirectView edit(T object){
        this.getService().update(object);
        return new RedirectView("list.do");
    }

    public RedirectView delete(T objcet) {
        this.getService().delete(objcet);
        return new RedirectView("list.do");
    }

}
