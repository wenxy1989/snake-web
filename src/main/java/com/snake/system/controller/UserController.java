package com.snake.system.controller;

import com.base.exception.ServiceException;
import com.base.util.*;
import com.base.Constants;
import com.snake.system.model.User;
import com.snake.system.service.IUserService;
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
import javax.servlet.http.HttpServletResponse;

/**
 * User: wenxy
 * Date: 2014-5-14
 */
@Controller
@RequestMapping("/user/")
public class UserController extends BasicController {

    @Resource(name="userService")
    private IUserService userService;

    /**
     * 查询条件:用户名、登录名、手机号、性别、民族、角色、所属医院、状态、注册起始截止日期、身份证号
     * @param pageNo
     * @param pageSize
     * @param request
     * @return
     */
    @RequestMapping(value = "page", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView page(Integer pageNo, Integer pageSize,HttpServletRequest request,HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("/system/user/list");
        responseTip(mv,request);

        String userName = request.getParameter("userName");//用户名称
        String loginName = request.getParameter("loginName");//登录名
        String mobile = request.getParameter("mobile");//手机号
        String sex = request.getParameter("sex");
        String status = request.getParameter("status");//状态
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        try {
            Criteria cri = new SimpleCriteria();
//        cri.addCondition(0, new Condition("r.code_", "=", Constants.ROLE_USER));//todo
            if(StringUtils.isNotBlank(userName)){
                cri.addCondition(0, new Condition("user_name", "like", userName + "%"));
                mv.addObject("userName",userName);
            }
            if(StringUtils.isNotBlank(loginName)){
                cri.addCondition(0, new Condition("login_name", "like", loginName + "%"));
                mv.addObject("loginName", loginName);
            }
            if(StringUtils.isNotBlank(mobile)){
                cri.addCondition(0, new Condition("mobile_", "like", mobile + "%"));
                mv.addObject("mobile",mobile);
            }
            if(StringUtils.isNotBlank(sex)){
                cri.addCondition(0, new Condition("sex_", "=", sex));
                mv.addObject("sex", sex);
            }
            if(StringUtils.isNotBlank(status)){
                cri.addCondition(0, new Condition("status_", "=", status));
                mv.addObject("status",status);
            }
            if(StringUtils.isNotBlank(startDate)){
                cri.addCondition(0, new Condition("created_time", ">=", startDate + " 00:00:00"));
                mv.addObject("startDate",startDate);
            }
            if(StringUtils.isNotBlank(endDate)){
                cri.addCondition(0, new Condition("created_time", "<=", endDate + " 23:59:59"));
                mv.addObject("endDate",endDate);
            }

            cri.setPageNo(pageNo == null ? 1 : pageNo);
            cri.setFetchSize(pageSize == null ? Constants.PAGE_SIZE : pageSize);
            cri.addOrder(0,"created_time desc");
            Page<User> page = userService.getList(cri);
            mv.addObject("page",page);
        } catch (ServiceException e) {
            logger.error("获取用户信息失败", e);
        }
        return mv;
    }

    /**
     * 用户详细-页面
     * @param id
     * @return
     */
    @RequestMapping(value = "view")
    public ModelAndView view(Long id){
        ModelAndView mv = new ModelAndView("/system/user/view");
        try{
            User user = userService.getObject(id);
            mv.addObject("user",user);
        }catch (ServiceException e){
            logger.error("获取用户对象失败",e);
        }
        return mv;
    }

    /**
     * 新增用户-页面
     * @return
     */
    @RequestMapping(value = "toAdd")
    public  ModelAndView toAdd(String roleCode,HttpServletRequest request){
        ModelAndView mv = new ModelAndView("/system/user/add");
        return mv;
    }

    /**
     * 新增用户-提交
     * @param user
     * @param request
     * @return
     */
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public RedirectView add(User user,HttpServletRequest request){
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try{
            if(!StringUtils.isBlank(user.getLoginName())){
                User object = userService.getUserByLoginName(user.getLoginName());
                if(object == null){
                    User loginUser = getLoginUser(request);
                    Long creatorId = loginUser.getId();
                    String md5Pwd = MD5Util.encode(user.getLoginPwd().toLowerCase());
                    user.setLoginPwd(md5Pwd);
                    user.setCreatedTime(DateTimeUtils.getNowDateTime());
                    user.setCreatorId(creatorId);
                    user.setStatus(Constants.STATUS_ENABLE);
                    userService.create(user);
                    result = RESULT_ADD_SUCCESS;
                }
            }
        }catch (Exception e){
            logger.error("添加用户信息失败",e);
        }
        rv.addStaticAttribute(OPE_RESULT,result);
        return rv;
    }

    /**
     * 修改用户-页面
     * @param id
     * @return
     */
    @RequestMapping(value = "toEdit", method = RequestMethod.GET)
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("/system/user/edit");
        try {
            User user = userService.getObject(id);
            mv.addObject("user", user);
        } catch (ServiceException e) {
            logger.error("获取用户信息失败.",e);
        }
        return mv;
    }

    /**
     * 修改用户-提交
     * @param user
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public RedirectView update(User user,HttpServletResponse response) {
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try{
            if (!StringUtils.isBlank(user.getUserName())){
                userService.update(user);
                result = RESULT_EDIT_SUCCESS;
            }
        }catch(Exception e){
            logger.error("修改用户信息失败",e);
        }
        rv.addStaticAttribute(OPE_RESULT,result);//设置操作结果
        return rv;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping(value = "delete")
    public RedirectView delete(Long id){
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try{
            userService.delete(id);
            result = RESULT_DELETE_SUCCESS;
        }catch(Exception e){
            logger.error("删除用户信息失败",e);
        }
        rv.addStaticAttribute(OPE_RESULT,result);
        return  rv;
    }

    /**
     * 检查用户登录名是否存在
     * @param loginName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "checkLoginName")
    public Object checkLoginName(String loginName){
        String result = RESULT_ERROR;
        try{
            User user = userService.getUserByLoginName(loginName);
            if(user != null){
                result =RESULT_SUCCESS;
            }
        }catch (ServiceException e){
            logger.error("check login name error",e);
        }
        return result;
    }

    /**
     * 检查用户邮箱是否存在
     * @param email
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "checkEmail")
    public Object checkEmail(String email){
        String result = RESULT_ERROR;
        try{
            User user = userService.getUserByEmail(email);
            if(user != null){
                result = RESULT_SUCCESS;
            }
        }catch (ServiceException e){
            logger.error("check email error",e);
        }
        return result;
    }

    /**
     * 检查用户手机号是否存在
     * @param mobile
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "checkMobile")
    public Object checkMobile(String mobile){
        String result = RESULT_ERROR;
        try{
            User user = userService.getUserByMobile(mobile);
            if(user != null){
                result = RESULT_SUCCESS;
            }
        }catch (ServiceException e){
            logger.error("check mobile error",e);
        }
        return result;
    }

    /**
     * 禁用用户
     * @param id
     * @return
     */
    @RequestMapping(value = "disable")
    public RedirectView disable(Long id){
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try{
            User user = new User();
            user.setId(id);
            user.setStatus(Constants.STATUS_DISABLE);
            userService.update(user);
            result = "disable_user_success";
        }catch (ServiceException e){
            logger.error("disable user error",e);
        }
        rv.addStaticAttribute(OPE_RESULT,result);
        return  rv;
    }

    /**
     * 启用用户
     * @param id
     * @return
     */
    @RequestMapping(value = "enable")
    public RedirectView enable(Long id){
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try{
            User user = new User();
            user.setId(id);
            user.setStatus(Constants.STATUS_ENABLE);
            userService.update(user);
            result = "enable_user_success";
        }catch (ServiceException e){
            logger.error("启用用户失败",e);
        }
        rv.addStaticAttribute(OPE_RESULT,result);
        return  rv;
    }

    /**
     * 重置用户密码为用户登录名
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "resetPwd")
    public Object resetPwd(Long id){
        String result = RESULT_ERROR;
        try{
            User user = userService.getObject(id);
            User updateUser = new User();
            updateUser.setId(id);
            String md5Pwd = MD5Util.encode(user.getLoginName().toLowerCase());
            updateUser.setLoginPwd(md5Pwd);
            userService.update(user);
            result = RESULT_SUCCESS;
        }catch (ServiceException e){
            logger.error("重置用户密码失败",e);
        }
        return  result;
    }

    /**
     * 验证输入的密码是否与当前登录用户密码相同
     * @param loginPwd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "checkMyPwd")
    public Object checkMyPwd(String loginPwd,HttpServletRequest request){
        String result = RESULT_ERROR;
        String md5Pwd = MD5Util.encode(loginPwd.toLowerCase());
        if (md5Pwd.equals(getLoginUser(request).getLoginPwd())){
            result = RESULT_SUCCESS;
        }
        return  result;
    }

    /**
     * 更新个人信息-页面
     * @return
     */
    @RequestMapping(value = "myInfo")
    public ModelAndView toMyInfo(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("/system/user/my_info");
        String ope_result = request.getParameter(OPE_RESULT);
        if(StringUtils.isNotBlank(ope_result)){
            mv.addObject(OPE_RESULT,ope_result + "," + System.currentTimeMillis());
        }
        try{
            User user = userService.getObject(getLoginUser(request).getId());
            mv.addObject("user",user);
        }catch (ServiceException e){
            logger.error("查找我的信息失败",e);
        }
        return mv;
    }

    /**
     * 更新个人信息-提交
     */
    @RequestMapping(value = "updateMyInfo")
    public RedirectView updateMyInfo(User user){
        RedirectView rv = new RedirectView("myInfo");
        String result = RESULT_ERROR;
        try{
            userService.update(user);
            result = "update_my_info_success";
        }catch (ServiceException e){
            logger.error("更新我的信息失败",e);
        }
        rv.addStaticAttribute(OPE_RESULT, result);
        return rv;
    }

    @RequestMapping(value = "myPassword")
    public ModelAndView toUpdateMyPwd(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("/system/user/my_password");
        try{
            User user = userService.getObject(getLoginUser(request).getId());
            mv.addObject("user",user);
        }catch (ServiceException e){
            logger.error("查找我的信息失败",e);
        }
        return mv;
    }

    @RequestMapping(value = "updateMyPwd")
    public RedirectView updateMyPwd(HttpServletRequest request){
        RedirectView rv = new RedirectView("/logout",true);
        String result = RESULT_ERROR;
        try{
            String oldPwd = request.getParameter("oldPwd");
            String newPwd = request.getParameter("newPwd");
            String md5Pwd = MD5Util.encode(oldPwd.toLowerCase());
            User loginUser = getLoginUser(request);
            if (md5Pwd.equals(loginUser.getLoginPwd())){
                User user = new User();
                user.setId(loginUser.getId());
                md5Pwd = MD5Util.encode(newPwd.toLowerCase());
                user.setLoginPwd(md5Pwd);
                userService.update(user);
                result = "update_my_pwd_success";
            }
        }catch (ServiceException e){
            logger.error("重置我的密码失败",e);
        }
        rv.addStaticAttribute(OPE_RESULT,result);
        return rv;
    }

}