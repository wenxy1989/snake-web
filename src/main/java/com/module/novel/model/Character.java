package com.module.novel.model;

import java.io.Serializable;

/**
 * 角色/人物
 * @author xue
 *
 */
public class Character extends NovelElement {
	
	private Long userId;//游戏人物所属用户id
	private Long createId;//游戏人物创建人id
	private Long raceId;//种族id
	private Long worldId;//世界（位面）id
	private Long familyId;//家族id
	private Integer sex;//性别
	
	private Long live;//生命值
	private Long atk;//攻击力
	private Long armor;//护甲
	
	private Race race;//种族对象
	private Family family;//家族对象

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCreateId() {
		return createId;
	}

	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	public Long getRaceId() {
		return raceId;
	}

	public void setRaceId(Long raceId) {
		this.raceId = raceId;
	}

	public Long getWorldId() {
		return worldId;
	}

	public void setWorldId(Long worldId) {
		this.worldId = worldId;
	}

	public Long getFamilyId() {
		return familyId;
	}

	public void setFamilyId(Long familyId) {
		this.familyId = familyId;
	}
	
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public Family getFamily() {
		return family;
	}

	public void setFamily(Family family) {
		this.family = family;
	}
	public Long getAtk() {
		return atk;
	}

	public void setAtk(Long atk) {
		this.atk = atk;
	}

	public void setLive(Long live) {
		this.live = live;
	}

	public void setArmor(Long armor) {
		this.armor = armor;
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

}
