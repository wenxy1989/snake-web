package com.snake.novel.service;

import com.base.common.service.BaseService;
import com.snake.novel.model.NovelElement;

public interface INovelElementService extends BaseService<NovelElement> {

	public void setNovelElements(NovelElement element);

}
