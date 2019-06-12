package com.snake.system.controller;

import com.base.Constants;
import com.snake.system.security.MySecurityMetadataSource;
import com.base.util.*;
import com.snake.system.model.Function;
import com.snake.system.service.IFunctionService;
import com.snake.system.service.IRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * User: wenxy
 * Date: 2014-5-14
 */
@Controller
@RequestMapping("/function/")
public class FunctionController extends BasicController {

    @Resource(name="functionService")
    private IFunctionService functionService;

    @Resource(name="roleService")
    private IRoleService roleService;

    /**
     * 菜单树管理页面
     * @return
     */
    @RequestMapping(value = "page")
    public ModelAndView page(Integer pageNo,Integer pageSize,HttpServletRequest request){
        ModelAndView mv = new ModelAndView("/system/function/list");
        responseTip(mv,request);
        try{
            String typeStr = request.getParameter("type");
            String parentIdStr = request.getParameter("parentId");
            String name = request.getParameter("name");
            String url = request.getParameter("url");
            Criteria c = new SimpleCriteria();
            if(StringUtils.isBlank(parentIdStr)){
                parentIdStr = "0";
            }
            if(StringUtils.isBlank(typeStr)){
                typeStr = "0";
            }
            Long parentId = Long.valueOf(parentIdStr);
            Integer type = Integer.valueOf(typeStr);
            c.addCondition(0,new Condition("parent_id","=",parentId));
            if(type > 0) {
                c.addCondition(0, new Condition("type_", "=", type));
            }
            mv.addObject("parentId", parentId);
            mv.addObject("type",type);
            if(StringUtils.isNotBlank(name)){
                c.addCondition(0,new Condition("name_","like",name+"%"));
                mv.addObject("name",name);
            }
            if(StringUtils.isNotBlank(url)){
                c.addCondition(0,new Condition("url_","like",url+"%"));
                mv.addObject("url",url);
            }
            c.setPageNo(pageNo == null ? 1 : pageNo);
            c.setFetchSize(pageSize == null ? Constants.PAGE_SIZE:pageSize);
            c.addOrder(0,"order_ asc");
            Page<Function> page = functionService.getList(c);
            mv.addObject("page",page);
            Function function = functionService.getObject(parentId);
            mv.addObject("function",function);
        }catch (Exception e){
            logger.error("获取菜单信息失败",e);
        }
        return mv;
    }

    @RequestMapping(value = "manage")
    public ModelAndView manage(String selectTid,HttpServletRequest request){
        ModelAndView mv = new ModelAndView("/system/function/manage");
        String ope_result = request.getParameter(OPE_RESULT);
        if(StringUtils.isNotBlank(ope_result)){
            mv.addObject(OPE_RESULT,ope_result + "," + System.currentTimeMillis());
        }
        try{
            List<Function> list = functionService.getAll();
            mv.addObject("funList", JsonUtil.parseJson(list));
            //todo
            mv.addObject("selectTid",selectTid);//设置选中的tree节点id
            mv.addObject("list",list);
        }catch (Exception e){
            logger.error("获取菜单信息失败",e);
        }
        return mv;
    }

    @RequestMapping(value = "toAdd")
    public ModelAndView toAdd(Long parentId){
        ModelAndView mv = new ModelAndView("/system/function/add");
        try{
            Integer maxOrder = functionService.getMaxOrderByParentId(parentId);
            mv.addObject("order",maxOrder+1);
            mv.addObject("parentId",parentId);
        }catch (Exception e){
            logger.error("获取菜单信息失败",e);
        }
        return mv;
    }

    /**
     * 添加菜单-提交
     * @param function
     * @param request
     * @return
     */
    @RequestMapping(value = "add")
    public RedirectView add(Function function,HttpServletRequest request){
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try{
            function.setCreatedTime(DateTimeUtils.getNowDateTime());
            functionService.create(function);
            MySecurityMetadataSource.addFunction(function);
            result = RESULT_ADD_SUCCESS;
        }catch (Exception e){
            logger.error("添加菜单信息失败",e);
        }
        rv.addStaticAttribute("parentId",function.getParentId());
        rv.addStaticAttribute(OPE_RESULT,result);
        return rv;
    }

    /**
     * 修改菜单-页面
     * @param id
     * @return
     */
    @RequestMapping(value = "toEdit", method = RequestMethod.GET)
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("/system/function/edit");
        try {
            Function obj = functionService.getObject(id);
            mv.addObject("obj", obj);
        } catch (Exception e) {
            logger.error("获取菜单信息失败.",e);
        }
        return mv;
    }

    /**
     * 修改菜单-提交
     * @param function
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public RedirectView update(Function function) {
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try{
            if (!StringUtils.isBlank(function.getName())){
                functionService.update(function);
                MySecurityMetadataSource.updateFunction(function);
                result = RESULT_EDIT_SUCCESS;
            }
        }catch(Exception e){
            logger.error("修改菜单信息失败",e);
        }
        rv.addStaticAttribute("parentId",function.getParentId());
        rv.addStaticAttribute(OPE_RESULT,result);
        return rv;
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @RequestMapping(value = "delete")
    public RedirectView delete(Long id){
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try{
            functionService.deleteFunctionRole(id);
            functionService.delete(id);
            MySecurityMetadataSource.removeFunctionById(id);
            result = RESULT_DELETE_SUCCESS;
        }catch(Exception e){
            logger.error("删除菜单信息失败",e);
        }
        rv.addStaticAttribute(OPE_RESULT,result);
        return  rv;
    }

    /**
     * 检查菜单代码是否存在
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "checkCode")
    public Object checkCode(String code){
        String result = RESULT_ERROR;
        try{
            Function function = functionService.getObjectByCode(code);
            if(function != null){
                result = RESULT_SUCCESS;
            }
        }catch (Exception e){
            logger.error("根据代码获取菜单信息失败",e);
        }
        return result;
    }

    /**
     * 更新菜单的parentId和level
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateParent")
    public Object updateParent(HttpServletRequest request){
        String result = RESULT_ERROR;
        try{
            Long id = new Long(request.getParameter("id"));
            Long parentId = new Long(request.getParameter("parentId"));
            Integer level = new Integer(request.getParameter("level"));
            Function function = new Function();
            function.setId(id);
            function.setParentId(parentId);
            function.setLevel(level);
            functionService.update(function);
            result = RESULT_SUCCESS;
        }catch(Exception e){
            logger.error("update parent error",e);
        }
        return result;
    }

    /**
     * 根据id获取菜单信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "details")
    public Object details(Long id){
        Function function = null;
        try{
            function = functionService.getObject(id);
        }catch (Exception e){
            logger.error("get details error",e);
        }
        return function;
    }

}