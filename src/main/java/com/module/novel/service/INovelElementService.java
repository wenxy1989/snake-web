package com.module.novel.service;

import com.base.common.service.BaseService;
import com.module.novel.model.NovelElement;

public interface INovelElementService extends BaseService<NovelElement> {

	public void setNovelElements(NovelElement element);

}
