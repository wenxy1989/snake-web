package com.snake.system.controller;

import com.base.Constants;
import com.snake.system.security.MySecurityMetadataSource;
import com.base.util.Condition;
import com.base.util.Criteria;
import com.base.util.DateTimeUtils;
import com.base.util.SimpleCriteria;
import com.snake.system.model.Function;
import com.snake.system.service.IFunctionService;
import com.snake.system.model.Role;
import com.snake.system.service.IRoleService;
import com.snake.system.model.User;
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
import java.util.ArrayList;
import java.util.List;

/**
 * User: wenxy
 * Date: 2014-5-14
 */
@Controller
@RequestMapping("/role/")
public class RoleController extends BasicController {

    @Resource(name="roleService")
    private IRoleService roleService;

    @Resource(name="functionService")
    private IFunctionService functionService;

    @RequestMapping(value = "page", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView page(Integer pageNo, Integer size,HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/system/role/list");
        String ope_result = request.getParameter(OPE_RESULT);
        if(StringUtils.isNotBlank(ope_result)){
            mv.addObject(OPE_RESULT,ope_result + "," + System.currentTimeMillis());
        }
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        Criteria cri = new SimpleCriteria();
        if(StringUtils.isNotBlank(name)){
            cri.addCondition(0, new Condition("name_","like",name+"%"));
            mv.addObject("name",name);
        }
        if(StringUtils.isNotBlank(code)){
            cri.addCondition(0, new Condition("code_","like",code+"%"));
            mv.addObject("code",code);
        }
        cri.setPageNo(pageNo==null?1:pageNo);
        cri.setFetchSize(size==null?Constants.PAGE_SIZE:size);
        try {
            Page page = roleService.getList(cri);
            mv.addObject("page",page);
        } catch (Exception e) {
            logger.error("获取角色信息失败", e);
        }
        return mv;
    }

    @RequestMapping(value = "toAdd")
    public  ModelAndView toAdd(){
        ModelAndView mv = new ModelAndView("/system/role/add");
        return mv;
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public RedirectView add(Role role,HttpServletRequest request){
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try{
            if(!StringUtils.isBlank(role.getCode()) && !StringUtils.isBlank(role.getName())){
                Role object = roleService.getObjectByCode(role.getCode());
                if(object != null){
                    result = RESULT_EXISTS;
                }else{
                    User loginUser = getLoginUser(request);
                    Long creatorId = loginUser.getId();
                    role.setCreatedTime(DateTimeUtils.getNowDateTime());
                    role.setCreatorId(creatorId);
                    role.setStatus(Constants.STATUS_ENABLE);
                    roleService.create(role);
                    result = RESULT_ADD_SUCCESS;
                }
            }
        }catch (Exception e){
            logger.error("添加角色信息失败",e);
        }
        rv.addStaticAttribute(OPE_RESULT,result);
        return rv;
    }

    @RequestMapping(value = "toEdit", method = RequestMethod.GET)
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("/system/role/edit");
        try {
            Role role = roleService.getObject(id);
            mv.addObject("role", role);
        } catch (Exception e) {
            logger.error("获取角色信息失败.",e);
        }
        return mv;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public RedirectView update(Role role) {
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try{
            if (!StringUtils.isBlank(role.getName())){
                roleService.update(role);
                result = RESULT_EDIT_SUCCESS;
            }
        }catch(Exception e){
            logger.error("修改角色信息失败",e);
        }
        rv.addStaticAttribute(OPE_RESULT,result);
        return rv;
    }

    @RequestMapping(value = "delete")
    public RedirectView delete(Long id){
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try{
            roleService.delete(id);
            result = RESULT_DELETE_SUCCESS;
        }catch(Exception e){
            logger.error("删除角色信息失败",e);
        }
        rv.addStaticAttribute(OPE_RESULT,result);
        return  rv;
    }

    @RequestMapping(value = "enable")
    public RedirectView enable(Long id){
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try{
            Role role = new Role();
            role.setId(id);
            role.setStatus(Constants.STATUS_ENABLE);
            roleService.update(role);
            result = "enable_role_success";
        }catch(Exception e){
            logger.error("启用角色失败",e);
        }
        rv.addStaticAttribute(OPE_RESULT,result);
        return  rv;
    }

    @RequestMapping(value = "disable")
    public RedirectView disable(Long id){
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try{
            //roleService.testTran(new Long(6));
            Role role = new Role();
            role.setId(id);
            role.setStatus(Constants.STATUS_DISABLE);
            roleService.update(role);
            result = "disable_role_success";
        }catch(Exception e){
            logger.error("禁用角色失败",e);
        }
        rv.addStaticAttribute(OPE_RESULT,result);
        return  rv;
    }

    @RequestMapping(value = "functionAuthorize")
    public ModelAndView functionAuthorize(Long id){
        ModelAndView mv = new ModelAndView("/system/role/functionAuthorize");
        try{
            List<Function> functions = functionService.getAll();
            Role role = roleService.getObject(id);
            mv.addObject("functions",functions);
            mv.addObject("role",role);
        }catch(Exception e){
            logger.error("获取全部菜单信息失败",e);
        }
        return mv;
    }

    @ResponseBody
    @RequestMapping(value="/loadFunctionIdsById")
    public Object loadFunctionIdsById(Long id){
        List<Long> ids = null;
        try{
            ids = roleService.loadFunctionIdsById(id);
        }catch (Exception e){
            logger.error("根据角色id获取的菜单ids",e);
        }
        return ids;
    }

    @ResponseBody
    @RequestMapping(value = "loadMyList")
    public Object loadMyList(HttpServletRequest request){
        List<Role> roles = new ArrayList<Role>();
        try{
            User loginUser = (User)request.getSession().getAttribute(Constants.USER_SESSON_KEY);
            String userRole = loginUser.getRole().getCode();
            List<Role> list = roleService.getAll();
            for(Role role:list){
                String roleCode = role.getCode();
                if(userRole.equals(Constants.ROLE_ADMIN) && roleCode.equals(Constants.ROLE_ADMIN)){//管理员不能查看监控中心角色
                    continue;
                }
                roles.add(role);
            }
        }catch (Exception e){
            logger.error("获取所有角色信息失败",e);
        }
        return roles;
    }

    @ResponseBody
    @RequestMapping(value = "checkCode")
    public Object checkCode(String code){
        String result = RESULT_ERROR;
        try{
            Role role = roleService.getObjectByCode(code);
            if(role != null){
                result = RESULT_SUCCESS;
            }
        }catch (Exception e){
            logger.error("根据编码获取角色信息失败",e);
        }
        return result;
    }

    @RequestMapping(value = "updateRoleFunction")
    public Object updateRoleFunction(Long id,String addFunctionIds,String removeFunctionIds){
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try{
            Role role = roleService.getObject(id);
            if(!StringUtils.isBlank(addFunctionIds)){
                String[] Ids = addFunctionIds.split(",");
                for(String functionId:Ids){
                    try{
                        functionService.addFunctionRole(new Long(functionId), id);
                        Function function = functionService.getObject(new Long(functionId));
                        MySecurityMetadataSource.addSecurityConfig(function.getUrl(),role.getCode());
                    }catch (Exception e){
                        logger.error("添加角色菜单授权失败",e);
                    }
                }
            }
            if(!StringUtils.isBlank(removeFunctionIds)){
                String[] Ids = removeFunctionIds.split(",");
                for(String idStr:Ids){
                    try{
                        Long functionId = new Long(idStr);
                        functionService.removeFunctionRole(functionId, id);
                        Function function = functionService.getObject(functionId);
                        MySecurityMetadataSource.removeSecurityConfig(function.getUrl(),role.getCode());
                    }catch (Exception e){
                        logger.error("移除角色菜单授权失败",e);
                    }
                }
            }
            result = "role_authorization_success";
        }catch (Exception e){
            logger.error("根据角色id更新角色菜单授权失败",e);
        }
        rv.addStaticAttribute(OPE_RESULT,result);
        return rv;
    }

}