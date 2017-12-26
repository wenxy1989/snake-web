package com.module.translate.topic.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.common.dao.Dao;
import com.base.common.service.AbstractService;
import com.module.translate.topic.Topic;

@Service("topicService")
public class TopicService extends AbstractService<Topic> implements ITopicService {
	
	@Resource(name="dao")
	private Dao dao;

	@Override
	protected Dao getDao() {
		return dao;
	}
	
}
