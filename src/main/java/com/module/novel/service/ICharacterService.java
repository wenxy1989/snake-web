package com.module.novel.service;

import java.util.List;

import com.base.common.service.BaseService;
import com.module.novel.model.Character;
import com.module.system.model.User;

public interface ICharacterService extends BaseService<Character>{

	public List<Character> getCharactersByUser(User user);

	public List<Character> getEnemysByUser(User user);

}
