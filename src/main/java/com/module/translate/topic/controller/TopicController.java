package com.module.translate.topic.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.base.common.controller.AbstractController;
import com.base.common.service.BaseService;
import com.module.system.service.ParameterService;
import com.module.translate.topic.service.ITopicService;
import com.module.translate.topic.Topic;

@Controller
@RequestMapping("/topic/")
public class TopicController extends AbstractController<Topic> {
	
	@Resource(name="topicService")
	private ITopicService service;

	@Resource(name="parameterService")
	private ParameterService parameterService;

	@Override
	protected ParameterService getParameterService() {
		return parameterService;
	}

	@Override
	protected BaseService<Topic> getService() {
		return service;
	}

	@Override
	protected Class<Topic> getClassTemplate() {
		return Topic.class;
	}

	@Override
	protected String getJspBasePath() {
		return "translate/topic/";
	}

	@Override
	protected String getBranchName() {
		return "topic";
	}
	
    @RequestMapping(value="list.ftl",method = {RequestMethod.GET})
    public ModelAndView list(Topic topic) {
        return super.list(topic);
    }
	
    @RequestMapping(value="toCreate.ftl")
    public ModelAndView toCreate() {
        ModelAndView mv = super.toCreate();
        return mv;
    }

    @RequestMapping(value = "toEdit.ftl")
    public ModelAndView toEdit(Long id) {
    	return super.toEdit(id);
    }

    @RequestMapping(value = "create.ftl", method = { RequestMethod.POST })
    public RedirectView create(Topic topic) {
    	super.create(topic);
        return new RedirectView("list.ftl");
    }

    @RequestMapping(value = "edit.ftl", method = { RequestMethod.POST })
    public RedirectView edit(Topic topic) {
    	super.edit(topic);
        return new RedirectView("list.ftl");
    }

    @RequestMapping(value = "delete.ftl")
    public RedirectView delete(Topic topic) {
    	super.delete(topic);
        return new RedirectView("list.ftl");
    }

    @RequestMapping(value = "manage.ftl")
    public ModelAndView manage() {
    	return new ModelAndView(this.getJspBasePath()+"/manager");
    }

    @ResponseBody
    @RequestMapping(value = "load_all.ftl")
    public Object loadAll(){
    	return service.getAll(Topic.class);
    }
    
}