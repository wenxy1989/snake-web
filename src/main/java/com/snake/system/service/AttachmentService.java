package com.snake.system.service;

import com.base.dao.IBasicDao;
import com.base.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.snake.system.dao.IAttachmentDao;
import com.snake.system.model.Attachment;

/**
* @ClassName: AttachmentService
* @Description: 附件信息表
* @author: wenxy
* @date:2014-07-15 13:32:29
*/

@Service
public class AttachmentService extends BasicService<Attachment> implements IAttachmentService {
    @Autowired
    @Qualifier("attachmentDao")
    private IAttachmentDao dao;

    @Override
    public IBasicDao<Attachment> getDao() {
        return this.dao;
    }
}
