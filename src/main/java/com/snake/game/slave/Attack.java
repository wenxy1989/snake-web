package com.snake.game.slave;

/**
 * 动作、技能
 * @author xue
 *
 */
public interface Attack extends Action{
	/**
	 * 指向属性：生命、灵魂、意志力（意识）
	 * @return
	 */
	public Long getType();
	/**
	 * 攻击力
	 * @return
	 */
	public Long getATK();
	/**
	 * 实物目标
	 * @return
	 */
	public Physical getTarget();
}