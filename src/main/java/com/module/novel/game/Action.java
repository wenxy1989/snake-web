package com.module.novel.game;

public interface Action {
	
	public Position getPosition();
	
	public void setPosition(Position position);
	
	/**
	 * 发出方
	 * @return
	 */
	public Physical getPhysical();
	
	/**
	 * 目标
	 * @return
	 */
	public Physical getTarget();
	
	/**
	 * 执行效果
	 * @return
	 */
	public String getCommond();


}
