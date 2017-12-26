package com.module.node.controller;

import com.base.common.controller.AbstractController;
import com.base.common.service.BaseService;
import com.module.node.model.NodePurview;
import com.module.node.service.INodePurviewService;
import com.module.system.service.ParameterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/nodepurview/")
public class NodePurviewController extends AbstractController<NodePurview> {

	@Resource(name="nodePurviewService")
	private INodePurviewService service;

	@Resource(name="parameterService")
	private ParameterService parameterService;

	@Override
	protected ParameterService getParameterService() {
		return parameterService;
	}
	
	@Override
	protected BaseService<NodePurview> getService() {
		return service;
	}

	@Override
	protected Class<NodePurview> getClassTemplate() {
		return NodePurview.class;
	}

	@Override
	protected String getJspBasePath() {
		return "node/nodepurview/";
	}

	@Override
	protected String getBranchName() {
		return "nodepurview";
	}
	
	@RequestMapping(value = "list.do")
	public ModelAndView list(){
		return super.list();
	}
	
    @RequestMapping(value = "toCreate.do")
    public ModelAndView toCreate(NodePurview nodePurview) {
    	return super.toCreate(nodePurview);
    }

    @RequestMapping(value = "toEdit.do")
    public ModelAndView toEdit(Long id) {
    	return super.toEdit(id);
    }

    @RequestMapping(value = "create.do", method = { RequestMethod.POST })
    public RedirectView create(NodePurview nodePurview) {
    	RedirectView rv = super.create(nodePurview);
    	rv.setUrl("list.do");
    	return rv;
    }

    @RequestMapping(value = "edit.do", method = { RequestMethod.POST })
    public RedirectView edit(NodePurview nodePurview) {
    	RedirectView rv = super.edit(nodePurview);
    	rv.setUrl("list.do");
    	return rv;
    }

    @RequestMapping(value = "delete.do")
    public RedirectView delete(NodePurview nodePurview) {
    	RedirectView rv = super.delete(nodePurview);
    	rv.setUrl("list.do");
    	return rv;
    }

}
