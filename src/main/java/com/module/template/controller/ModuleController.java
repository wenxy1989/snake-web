package com.module.template.controller;

import com.base.common.controller.AbstractController;
import com.base.common.service.BaseService;
import com.base.utils.FreeMarkerUtils;
import com.module.system.service.IParameterService;
import com.module.template.model.BaseTemplate;
import com.module.template.model.Action;
import com.module.template.service.IActionService;
import com.module.template.model.Attribute;
import com.module.template.service.IAttributeService;
import com.module.template.model.Module;
import com.module.template.service.IModuleService;
import com.module.template.service.ITemplateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/module/")
public class ModuleController extends AbstractController<Module> {
	
	@Resource(name="moduleService")
	private IModuleService service;

	@Resource(name="parameterService")
	private IParameterService parameterService;
	
	@Resource(name="templateService")
	private ITemplateService templateService;
	
	@Resource(name="attributeService")
	private IAttributeService attributeService;
	
	@Resource(name="actionService")
	private IActionService actionService;
	
	@Override
	protected IParameterService getParameterService() {
		return parameterService;
	}

	@Override
	protected BaseService<Module> getService() {
		return service;
	}

	@Override
	protected Class<Module> getClassTemplate() {
		return Module.class;
	}

	@Override
	protected String getJspBasePath() {
		return "template/module/";
	}

	@Override
	protected String getBranchName() {
		return "module";
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
        Module object = this.getService().getObject(this.getClassTemplate(),id);
        mv.addObject("object", object);
        return mv;
    }

    @RequestMapping(value = "create.ftl", method = { RequestMethod.POST })
    public RedirectView create(Module module) {
    	super.create(module);
    	return new RedirectView("list.ftl");
    }

    @RequestMapping(value = "edit.ftl", method = { RequestMethod.POST })
    public RedirectView edit(Module module) {
    	super.edit(module);
    	return new RedirectView("list.ftl");
    }

    @RequestMapping(value = "delete.ftl")
    public RedirectView delete(Module module) {
    	super.delete(module);
    	return new RedirectView("list.ftl");
    }

    @ResponseBody
    @RequestMapping(value = "export_code.ftl")
    public void exportCode(Long id) {
    	Module module = this.getService().getObject(this.getClassTemplate(), id);
    	List<Attribute> attributes = attributeService.getListByModuleId(module.getId());
    	List<Action> actions = actionService.getListByModuleId(module.getId());
    	Map<String,Object> params = new HashMap<String,Object>();
    	params.put("attributes", attributes);
    	params.put("actions", actions);
    	params.put(FreeMarkerUtils.CLASS_NAME, new String(module.getClassName()));
    	params.put(FreeMarkerUtils.MODULE_CODE, module.getClassName().toLowerCase());
    	params.put(FreeMarkerUtils.MODULE_NAME, module.getName());
    	params.put("author", "wenxy");
    	params.put("date", new Date());
    	List<BaseTemplate> templates = templateService.getListByType("base_template");
    	for(BaseTemplate bt : templates){
    		FreeMarkerUtils.getInstance().buildTemplate(bt.getTemplateName(), bt.getModuleName(), params, bt.getSavePathModel(), bt.getSaveFileModel());
    	}
    }
    
}