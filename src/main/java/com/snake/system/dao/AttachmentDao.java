package com.snake.system.dao;

import org.springframework.stereotype.Repository;
import com.base.dao.MybatisBasicDao;
import com.snake.system.model.Attachment;

/**
* @ClassName:AttachmentDao
* @Description: 附件信息表
* @author: wenxy
* @date:2014-07-15 13:32:29
*/
@Repository("attachmentDao")
public class AttachmentDao extends MybatisBasicDao<Attachment> implements IAttachmentDao {

	public AttachmentDao(){
		super(Attachment.class);
	}
}
