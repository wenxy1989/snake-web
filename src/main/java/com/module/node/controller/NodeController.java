package com.module.node.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.base.common.controller.AbstractController;
import com.base.common.service.BaseService;
import com.module.node.model.Node;
import com.module.node.service.INodeService;
import com.module.system.service.ParameterService;

@Controller
@RequestMapping("/node/")
public class NodeController extends AbstractController<Node> {

	@Resource(name="nodeService")
	private INodeService service;

	@Resource(name="parameterService")
	private ParameterService parameterService;

	@Override
	protected ParameterService getParameterService() {
		return parameterService;
	}
	
	@Override
	protected BaseService<Node> getService() {
		return service;
	}

	@Override
	protected Class<Node> getClassTemplate() {
		return Node.class;
	}

	@Override
	protected String getJspBasePath() {
		return "node/node/";
	}

	@Override
	protected String getBranchName() {
		return "node";
	}
	
	@RequestMapping(value = "list.do")
	public ModelAndView list(){
		return this.childs(null);
	}

	@RequestMapping(value = "childs.do")
	public ModelAndView childs(Long id){
		if(null == id){
			id = 0l;
		}
		Node node = new Node();
		node.setParentId(id);
		ModelAndView mv = super.list(node);
		node = service.getObject(Node.class, id);
		mv.addObject("object", node);
		return mv;
	}

	@RequestMapping(value = "brothers.do")
	public RedirectView brothers(Long id){
		if(null == id){
			id = 0l;
		}
		Node node = service.getObject(Node.class, id);
    	RedirectView rv = new RedirectView("childs.do");
    	rv.addStaticAttribute("id", node.getParentId());
		return rv;
	}
	
    @RequestMapping(value = "toCreate.do")
    public ModelAndView toCreate(Node node) {
    	return super.toCreate(node);
    }

    @RequestMapping(value = "toEdit.do")
    public ModelAndView toEdit(Long id) {
    	return super.toEdit(id);
    }

    @RequestMapping(value = "create.do", method = { RequestMethod.POST })
    public RedirectView create(Node node) {
    	RedirectView rv = super.create(node);
    	rv.setUrl("childs.do");
    	rv.addStaticAttribute("id", node.getParentId());
    	return rv;
    }

    @RequestMapping(value = "edit.do", method = { RequestMethod.POST })
    public RedirectView edit(Node node) {
    	RedirectView rv = super.edit(node);
    	rv.setUrl("childs.do");
    	rv.addStaticAttribute("id", node.getParentId());
    	return rv;
    }

    @RequestMapping(value = "delete.do")
    public RedirectView delete(Node node) {
    	RedirectView rv = super.delete(node);
    	rv.setUrl("childs.do");
    	rv.addStaticAttribute("id", node.getParentId());
    	return rv;
    }

	private Node loadNodeDetailsById(Long id){
		Node node = service.getObject(Node.class,id);
		node.setChilds(service.getNodeListByParentId(id));
		return node;
	}
	
	@RequestMapping(value = "details.do")
	public ModelAndView getNodeDetails(Long id){
		ModelAndView mv = new ModelAndView("node/node/details");
		Node node = loadNodeDetailsById(id);
		mv.addObject("node", node);
		return mv;
	}

}
