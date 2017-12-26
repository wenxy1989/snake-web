package com.snake.media.controller;

import com.base.Constants;
import com.base.exception.ServiceException;
import com.base.util.Condition;
import com.base.util.Criteria;
import com.base.util.DateTimeUtils;
import com.base.util.SimpleCriteria;
import com.snake.media.model.Video;
import com.snake.media.service.IVideoService;
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
@RequestMapping("/video/")
public class VideoController extends BasicController {

    @Resource(name = "videoService")
    private IVideoService videoService;

    @RequestMapping(value = "page")
    public ModelAndView page(Integer pageSize,Integer pageNo,HttpServletRequest request){
        ModelAndView mv = new ModelAndView("/media/video/list");
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
            Page<Video> page = videoService.getList(c);
            mv.addObject("page",page);
        }catch (ServiceException e){
            logger.error("获取所有视频信息失败",e);
        }
        return mv;
    }

    @RequestMapping(value = "list")
    public ModelAndView list(Long id){
        ModelAndView mv = new ModelAndView("/media/video/list");
        try {
            Video video = videoService.getObject(id);
            List<Video> list = videoService.getListLike(video.getFilePath());
            mv.addObject("list",list);
        }catch (ServiceException e){
            logger.error("获取所有视频信息失败",e);
        }
        return mv;
    }

    @RequestMapping(value = "toEdit")
    public ModelAndView toEdit(Long id){
        ModelAndView mv = new ModelAndView("/media/video/edit");
        try{
            Video video = videoService.getObject(id);
            mv.addObject("video",video);
        }catch (ServiceException e){
            logger.error(" video Controller getObject error",e);
        }
        return mv;
    }

    @RequestMapping(value = "edit")
    public RedirectView toEdit(Video video){
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try{
            videoService.update(video);
            result = RESULT_EDIT_SUCCESS;
        }catch (ServiceException e){
            logger.error(" video Controller update Object error",e);
        }
        rv.addStaticAttribute(OPE_RESULT,result);
        return rv;
    }

    private void saveFile(File file) {
        Video video = new Video();
        video.setName(file.getName());
        video.setFilePath(file.getAbsolutePath());
        video.setCreatedTime(DateTimeUtils.getTime());
        try {
            videoService.create(video);
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
                    }else if(Constants.VIDEO_NAME_EXT.contains(ext)){
                        saveFile(file);
                    }
                }
            }
        }
    }

    @RequestMapping(value = "toInitDir")
    public ModelAndView toInitDir(){
        return new ModelAndView("media/video/select_dir");
    }

    @RequestMapping(value = "initDir")
    public RedirectView initDir(String dir){
        RedirectView rv = new RedirectView("page");
        if(StringUtils.isEmpty(dir)){
            dir = "J:\\movie";
        }
        listDir(new File(dir));
        return rv;
    }

    @RequestMapping(value = "play")
    public ModelAndView play(Long id){
        ModelAndView mv = new ModelAndView("/media/video/play");
        try{
            Video video = videoService.getObject(id);
            mv.addObject("video",video);
        }catch (ServiceException e){
            logger.error("获取文件信息失败",e);
        }
        return mv;
    }

    @RequestMapping(value = "sewise")
    public ModelAndView sewise(Long id){
        ModelAndView mv = new ModelAndView("/media/video/sewise_player");
        try{
            Video video = videoService.getObject(id);
            mv.addObject("video",video);
        }catch (ServiceException e){
            logger.error("获取文件信息失败",e);
        }
        return mv;
    }

}
