package com.module.node.controller;

import com.base.common.controller.AbstractController;
import com.base.common.service.BaseService;
import com.module.node.model.NodeGroup;
import com.module.node.service.INodeGroupService;
import com.module.system.service.ParameterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/nodegroup/")
public class NodeGroupController extends AbstractController<NodeGroup> {

	@Resource(name="nodeGroupService")
	private INodeGroupService service;

	@Resource(name="parameterService")
	private ParameterService parameterService;

	@Override
	protected ParameterService getParameterService() {
		return parameterService;
	}
	
	@Override
	protected BaseService<NodeGroup> getService() {
		return service;
	}

	@Override
	protected Class<NodeGroup> getClassTemplate() {
		return NodeGroup.class;
	}

	@Override
	protected String getJspBasePath() {
		return "node/nodegroup/";
	}

	@Override
	protected String getBranchName() {
		return "nodegroup";
	}
	
	@RequestMapping(value = "list.do")
	public ModelAndView list(){
		return super.list();
	}
	
    @RequestMapping(value = "toCreate.do")
    public ModelAndView toCreate(NodeGroup nodeGroup) {
    	return super.toCreate(nodeGroup);
    }

    @RequestMapping(value = "toEdit.do")
    public ModelAndView toEdit(Long id) {
    	return super.toEdit(id);
    }

    @RequestMapping(value = "create.do", method = { RequestMethod.POST })
    public RedirectView create(NodeGroup nodeGroup) {
    	RedirectView rv = super.create(nodeGroup);
    	return rv;
    }

    @RequestMapping(value = "edit.do", method = { RequestMethod.POST })
    public RedirectView edit(NodeGroup nodeGroup) {
    	RedirectView rv = super.edit(nodeGroup);
    	rv.setUrl("list.do");
    	return rv;
    }

    @RequestMapping(value = "delete.do")
    public RedirectView delete(NodeGroup nodeGroup) {
    	RedirectView rv = super.delete(nodeGroup);
    	rv.setUrl("list.do");
    	return rv;
    }

}
