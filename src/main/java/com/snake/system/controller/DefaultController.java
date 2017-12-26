package com.snake.system.controller;

import com.base.exception.ServiceException;
import com.snake.system.model.Index;
import com.snake.system.service.IFunctionService;
import com.snake.system.service.IIndexService;
import com.snake.system.service.IRoleService;
import com.snake.system.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class DefaultController extends BasicController {

    @Resource(name = "indexService")
    private IIndexService indexService;

    @Resource(name = "userService")
    private IUserService userService;

    @Resource(name = "roleService")
    private IRoleService roleService;

    @Resource(name = "functionService")
    private IFunctionService functionService;

    @RequestMapping(value = "/")
    public ModelAndView login() {
        return new ModelAndView("/login");
    }

    @RequestMapping(value = "home")
    public ModelAndView home() {
        return new ModelAndView("/home");
    }

    @RequestMapping(value = "link")
    public RedirectView link(HttpServletRequest request) {
        RedirectView rv = new RedirectView("home");
        Long userId = getLoginUserId(request);
        try {
            Index index = indexService.getObjectByUserId(userId);
            if (null == index) {
                Long roleId = getLoginUserRoleId(request);
                index = indexService.getObjectByRoleId(roleId);
            }
            if (null != index) {
                rv = new RedirectView(index.getUrl(),true);
            }
        } catch (ServiceException e) {
            logger.error("get index url error", e);
        }
        return rv;
    }

    @ResponseBody
    @RequestMapping(value = "session/check")
    public Object getSessionTest(HttpServletRequest request, String string) {
        HttpSession session = request.getSession();
        return session.getId();
    }

    @ResponseBody
    @RequestMapping(value = "session/test")
    public Object sessionTest(HttpServletRequest request, String string) {
        String name = "content";
        HttpSession session = request.getSession();
        System.out.println(session);
        System.out.println(session.getId());
        String content = "" + session.getAttribute(name);
        content += "," + string;
        session.setAttribute(name, content);
        return content;
    }

    @RequestMapping(value = "sessionExpired")
    public ModelAndView sessionExpired() {
        ModelAndView mv = new ModelAndView("/session_expired");
        mv.addObject("result", "sessionExpired");
        return mv;
    }

    @RequestMapping(value = "accessDenied")
    public ModelAndView accessDenied() {
        ModelAndView mv = new ModelAndView("/access_denied");
        return mv;
    }

}