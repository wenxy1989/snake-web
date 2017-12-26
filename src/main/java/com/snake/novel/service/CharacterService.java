package com.snake.novel.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.common.dao.Dao;
import com.base.common.service.AbstractService;
import com.snake.novel.model.Character;
import com.snake.system.model.User;

@Service("characterService")
public class CharacterService extends AbstractService<Character> implements ICharacterService {
	
	@Resource(name="dao")
	private Dao dao;

	@Override
	protected Dao getDao() {
		return dao;
	}

	public List<Character> getCharactersByUserId(Long characterId) {
		return dao.find("from com.snake.novel.model.Character c where c.userId=?",characterId);
	}

	public List<Character> getEnemiesByUserId(Long characterId) {
		return dao.find("from com.snake.novel.model.Character c where c.userId<>?",characterId);
	}

}
