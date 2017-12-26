package com.module.novel.game;

public interface GameCharater {
	
	/**
	 * 指向性动作
	 * @param action
	 * @param physical
	 */
	public void doAction(Action action,Physical physical);
	
	/**
	 * 非指向性动作
	 * @param action
	 */
	public void doAction(Action action);
	
	/**
	 * 移动到某位置
	 * @param position
	 */
	public void moveTo(Position position);
	
	/**
	 * 动向移动
	 */
	public void move(Trend trend);
	
}
