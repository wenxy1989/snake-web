package com.module.novel.model;

import java.io.Serializable;
import java.util.List;

/**
 * 世界/位面
 * @author xue
 *
 */
public class World extends NovelElement {
	
	private List<Character> characters;
	private List<Family> familys;
	private List<Race> races;

	public List<Character> getCharacters() {
		return characters;
	}

	public void setCharacters(List<Character> characters) {
		this.characters = characters;
	}

	public List<Family> getFamilys() {
		return familys;
	}

	public void setFamilys(List<Family> familys) {
		this.familys = familys;
	}

	public List<Race> getRaces() {
		return races;
	}

	public void setRaces(List<Race> races) {
		this.races = races;
	}

	@Override
	public Serializable getObjectId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUniqueIdName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getNovelId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNovelId(Long novelId) {
		// TODO Auto-generated method stub
		
	}

}
