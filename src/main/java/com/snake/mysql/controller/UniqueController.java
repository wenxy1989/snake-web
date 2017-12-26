package com.snake.mysql.controller;

import com.snake.system.controller.BasicController;
import com.base.exception.ServiceException;
import com.snake.mysql.model.Table;
import com.snake.mysql.model.Unique;
import com.snake.mysql.service.ITableService;
import com.snake.mysql.service.IUniqueService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 房屋信息模块
 * author wenxy
 */
@Controller
@RequestMapping("/mysql/unique/")
public class UniqueController extends BasicController {

    @Resource(name = "uniqueService")
    private IUniqueService uniqueService;

    @Resource(name = "tableService")
    private ITableService tableService;

    @RequestMapping(value = "page")
    public ModelAndView page(Long tableId) {
        ModelAndView mv = new ModelAndView("/mysql/uniuqe/list");
        try {
            Table table = tableService.getObject(tableId);
            mv.addObject("table", table);
            List<Unique> list = uniqueService.getAll();
            mv.addObject("objectList", list);
        } catch (ServiceException e) {
            logger.error("query database unique error", e);
        }
        return mv;
    }

    @RequestMapping(value = "toAdd")
    public ModelAndView toCreate(Long tableId) {
        ModelAndView mv = new ModelAndView("/mysql/uniuqe/add");
        try {
            Table table = tableService.getObject(tableId);
            mv.addObject("table", table);
        } catch (ServiceException e) {
            logger.error("get table error", e);
        }
        return mv;
    }

    @RequestMapping(value = "toEdit")
    public ModelAndView toEdit(Long id) {
        ModelAndView mv = new ModelAndView("/mysql/uniuqe/edit");
        try {
            Unique unique = uniqueService.getObject(id);
            mv.addObject("unique", unique);
        } catch (ServiceException e) {
            logger.error("get unique error", e);
        }
        return mv;
    }

    @RequestMapping(value = "add", method = {RequestMethod.POST})
    public RedirectView create(Unique unique) {
        RedirectView rv = new RedirectView("page");
        try {
            uniqueService.create(unique);
        } catch (ServiceException e) {
            logger.error("create unique error", e);
        }
        rv.addStaticAttribute("tableId", unique.getTableId());
        return rv;
    }

    @RequestMapping(value = "edit", method = {RequestMethod.POST})
    public RedirectView edit(Unique unique) {
        RedirectView rv = new RedirectView("page");
        try {
            uniqueService.update(unique);
        } catch (ServiceException e) {
            logger.error("update unique error", e);
        }
        rv.addStaticAttribute("tableId", unique.getTableId());
        return rv;
    }

    @RequestMapping(value = "delete")
    public RedirectView delete(Long id, Long tableId) {
        RedirectView rv = new RedirectView("page");
        try {
            uniqueService.delete(id);
        } catch (ServiceException e) {
            logger.error("delete unique error", e);
        }
        rv.addStaticAttribute("tableId", tableId);
        return rv;
    }


}