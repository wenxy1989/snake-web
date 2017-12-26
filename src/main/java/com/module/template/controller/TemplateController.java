package com.module.template.controller;

import com.base.common.controller.AbstractController;
import com.base.common.service.BaseService;
import com.base.utils.FreeMarkerUtils;
import com.module.system.service.ParameterService;
import com.module.template.model.BaseTemplate;
import com.module.template.service.ITemplateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/template/")
public class TemplateController extends AbstractController<BaseTemplate> {
	
	@Resource(name="templateService")
	private ITemplateService service;

	@Resource(name="parameterService")
	private ParameterService parameterService;

	@Override
	protected ParameterService getParameterService() {
		return parameterService;
	}

	@Override
	protected BaseService<BaseTemplate> getService() {
		return service;
	}

	@Override
	protected Class<BaseTemplate> getClassTemplate() {
		return BaseTemplate.class;
	}

	@Override
	protected String getJspBasePath() {
		return "template/";
	}

	@Override
	protected String getBranchName() {
		return "template";
	}
	
	@RequestMapping(value = "list.ftl")
	public ModelAndView list(){
		return super.list();
	}
	
    @RequestMapping(value = "toCreate.ftl")
    public ModelAndView toCreate() {
    	return super.toCreate();
    }

    @RequestMapping(value = "toEdit.ftl")
    public ModelAndView toEdit(Long id) {
        ModelAndView mv = new ModelAndView(this.getJspBasePath() + "edit");
        BaseTemplate object = this.getService().getObject(this.getClassTemplate(),id);
        mv.addObject("object", object);
        return mv;
    }

    @RequestMapping(value = "create.ftl", method = { RequestMethod.POST })
    public RedirectView create(BaseTemplate template) {
    	super.create(template);
    	return new RedirectView("list.ftl");
    }

    @RequestMapping(value = "edit.ftl", method = { RequestMethod.POST })
    public RedirectView edit(BaseTemplate template) {
    	super.edit(template);
    	return new RedirectView("list.ftl");
    }

    @RequestMapping(value = "delete.ftl")
    public RedirectView delete(BaseTemplate template) {
    	super.delete(template);
    	return new RedirectView("list.ftl");
    }
    
    

    @ResponseBody
    @RequestMapping(value = "export_code.ftl")
    public void exportCode(Long id) {
    	BaseTemplate template = this.getService().getObject(this.getClassTemplate(), id);
    	Map<String,Object> params = new HashMap<String,Object>();
    	params.put(FreeMarkerUtils.MODULE_NAME, template.getModuleName());
    	FreeMarkerUtils.getInstance().buildTemplate(template.getTemplateName(), template.getModuleName(), params, template.getSavePathModel(), template.getSaveFileModel());
    }
    
}