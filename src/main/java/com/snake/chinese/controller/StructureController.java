package com.snake.chinese.controller;

import com.base.Constants;
import com.snake.system.controller.BasicController;
import com.base.exception.ServiceException;
import com.base.util.Condition;
import com.base.util.Criteria;
import com.base.util.DateTimeUtils;
import com.base.util.SimpleCriteria;
import com.snake.chinese.model.Structure;
import com.snake.chinese.model.relation.StructureElement;
import com.snake.chinese.service.IStructureService;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by wen on 2015/5/1.
 */
@Controller
@RequestMapping("/book/structure/")
public class StructureController extends BasicController {

    @Resource(name = "structureService")
    private IStructureService structureService;

    @RequestMapping(value = "page")
    public ModelAndView page(Integer pageSize,Integer pageNo,HttpServletRequest request){
        ModelAndView mv = new ModelAndView("/chinese/structure/list");
        String name = request.getParameter("name");
        String structure = request.getParameter("structure");
        Criteria c = new SimpleCriteria();
        if(StringUtils.isNotEmpty(name)){
            c.addCondition(0,new Condition("name_","like","%"+name+"%"));
            mv.addObject("name",name);
        }
        if(StringUtils.isNotEmpty(structure)){
            c.addCondition(0,new Condition("structure_","=",structure));
            mv.addObject("structure", structure);
        }
        try {
            c.setPageNo(pageNo == null?1:pageNo);
            c.setFetchSize(pageSize == null?Constants.PAGE_SIZE:pageSize);
            Page<Structure> page = structureService.getList(c);
            mv.addObject("page",page);
        }catch (ServiceException e){
            logger.error("获取语句结构信息失败",e);
        }
        return mv;
    }

    @RequestMapping(value = "toAdd")
    public ModelAndView toAdd(){
        ModelAndView mv = new ModelAndView("/chinese/structure/add");
        return mv;
    }

    @RequestMapping(value = "add")
    public RedirectView add(Structure structure){
        RedirectView rv = new RedirectView("page");
        try{
            structure.setCreatedTime(DateTimeUtils.getNowDateTime());
            structureService.create(structure);
        }catch (ServiceException e){
            logger.error("add book word structure failed",e);
        }
        return rv;
    }

    @RequestMapping(value = "toEdit")
    public ModelAndView toEdit(Long id){
        ModelAndView mv = new ModelAndView("/chinese/structure/edit");
        try {
            Structure structure = structureService.getObject(id);
            if(null != structure){
                List<StructureElement> elements = structureService.getStructureElementListByStructureId(id);
                mv.addObject("elements", elements);
            }
            mv.addObject("obj", structure);
        }catch (ServiceException e){
            logger.error("get structure failed",e);
        }
        return mv;
    }

    @RequestMapping(value = "edit")
    public RedirectView edit(Structure structure){
        RedirectView rv = new RedirectView("page");
        try{
            structureService.update(structure);
        }catch (ServiceException e){
            logger.error("edit book word structure failed",e);
        }
        return rv;
    }

    @RequestMapping(value = "delete")
    public RedirectView delete(Long id){
        RedirectView rv = new RedirectView("page");
        try{
            structureService.delete(id);
        }catch (ServiceException e){
            logger.error("add book word structure failed",e);
        }
        return rv;
    }

    @ResponseBody
    @RequestMapping(value = "addElement")
    public Object addElement(StructureElement obj){
        String result = RESULT_ERROR;
        try{
            structureService.addStructureElement(obj);
            result = RESULT_SUCCESS;
        }catch (ServiceException e){
            logger.error("add structure element error",e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "removeElement")
    public Object removeElement(StructureElement obj){
        String result = RESULT_ERROR;
        try{
            structureService.removeStructureElement(obj);
            result = RESULT_SUCCESS;
        }catch (ServiceException e){
            logger.error("add structure element error",e);
        }
        return result;
    }

}
