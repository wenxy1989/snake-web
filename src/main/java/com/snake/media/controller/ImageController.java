package com.snake.media.controller;

import com.base.Constants;
import com.base.exception.ServiceException;
import com.base.util.Condition;
import com.base.util.Criteria;
import com.base.util.DateTimeUtils;
import com.base.util.SimpleCriteria;
import com.snake.media.model.Image;
import com.snake.media.service.IImageService;
import com.snake.system.controller.BasicController;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * Created by wen on 2015/5/1.
 */
@Controller
@RequestMapping("/image/")
public class ImageController extends BasicController {

    @Resource(name = "imageService")
    private IImageService imageService;

    @RequestMapping(value = "page")
    public ModelAndView page(Integer pageSize,Integer pageNo,HttpServletRequest request){
        ModelAndView mv = new ModelAndView("/media/image/list");
        String ext = request.getParameter("ext");
        String name = request.getParameter("name");
        Criteria c = new SimpleCriteria();
        if(StringUtils.isNotEmpty(ext)){
            c.addCondition(0,new Condition("name_","like","%"+ext));
        }
        if(StringUtils.isNotEmpty(name)){
            c.addCondition(0,new Condition("file_path","like","%"+name+"%"));
        }
        try {
            c.setPageNo(pageNo == null?1:pageNo);
            c.setFetchSize(pageSize == null?Constants.PAGE_SIZE:pageSize);
            Page<Image> page = imageService.getList(c);
            mv.addObject("page",page);
        }catch (ServiceException e){
            logger.error("获取所有视频信息失败",e);
        }
        return mv;
    }

    @RequestMapping(value = "list")
    public ModelAndView list(Long id){
        ModelAndView mv = new ModelAndView("/media/image/list");
        try {
            Image image = imageService.getObject(id);
            List<Image> list = imageService.getListLike(image.getFilePath());
            mv.addObject("list",list);
        }catch (ServiceException e){
            logger.error("获取所有视频信息失败",e);
        }
        return mv;
    }

    private void saveFile(File file) {
        Image image = new Image();
        image.setName(file.getName());
        image.setFilePath(file.getAbsolutePath());
        image.setCreatedTime(DateTimeUtils.getTime());
        try {
            imageService.create(image);
        }catch (ServiceException e){
            logger.error("创建新的文件信息失败",e);
        }
    }

    private void listDir(File dir){
        if(dir.exists()){
            if(dir.isDirectory()){
                File[] files = dir.listFiles();
                for(File file:files) {
                    String name = file.getName();
                    String ext = name.substring(name.lastIndexOf(".")+1);
                    if(file.isDirectory()){
                        listDir(file);
                    }else if(Constants.IMAGE_NAME_EXT.contains(ext)){
                        saveFile(file);
                    }
                }
            }
        }
    }

    @RequestMapping(value = "toInitDir")
    public ModelAndView toInitDir(){
        return new ModelAndView("media/image/select_dir");
    }

    @RequestMapping(value = "initDir")
    public RedirectView initDir(String dir){
        RedirectView rv = new RedirectView("page");
        if(StringUtils.isEmpty(dir)){
            dir = "M:\\photos\\风俗媚娘";
        }
        listDir(new File(dir));
        return rv;
    }

    @RequestMapping(value = "details")
    public ModelAndView details(Integer pageSize,Integer start,HttpServletRequest request){
        ModelAndView mv = new ModelAndView("/media/image/details");
        String ext = request.getParameter("ext");
        String name = request.getParameter("name");
        Criteria c = new SimpleCriteria();
        if(StringUtils.isNotEmpty(ext)){
            c.addCondition(0,new Condition("name_","like","%"+ext));
        }
        if(StringUtils.isNotEmpty(name)){
            c.addCondition(0,new Condition("file_path","like","%"+name+"%"));
        }
        try {
            c.setFetchSize(pageSize == null? 5 :pageSize);
            c.setStart(start == null ? 0 : start);
            Page<Image> page = imageService.getList(c);
            mv.addObject("page",page);
        }catch (ServiceException e){
            logger.error("获取所有视频信息失败",e);
        }
        return mv;
    }

}
