package com.module.novel.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.common.dao.Dao;
import com.base.common.service.AbstractService;
import com.module.novel.model.Character;
import com.module.system.model.User;

@Service("characterService")
public class CharacterService extends AbstractService<Character> implements ICharacterService {
	
	@Resource(name="dao")
	private Dao dao;

	@Override
	protected Dao getDao() {
		return dao;
	}

	@SuppressWarnings("unchecked")
	public List<Character> getCharactersByUser(User user) {
		return dao.find("from com.module.novel.character.Character c where c.userId=?",user.getId());
	}

	@SuppressWarnings("unchecked")
	public List<Character> getEnemysByUser(User user) {
		return dao.find("from com.module.novel.character.Character c where c.userId<>?",user.getId());
	}

}
