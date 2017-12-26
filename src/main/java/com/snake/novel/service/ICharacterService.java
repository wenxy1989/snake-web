package com.snake.novel.service;

import com.base.common.service.BaseService;
import com.snake.novel.model.Character;

import java.util.List;

public interface ICharacterService extends BaseService<Character>{

	public List<Character> getCharactersByUserId(Long characterId);

	public List<Character> getEnemiesByUserId(Long characterId);

}
