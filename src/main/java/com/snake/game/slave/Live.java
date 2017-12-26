package com.snake.game.slave;

import java.util.Set;

/**
 * 生命体
 * @author xue
 *
 */
public interface Live extends Physical {
	
	/**
	 * 获取可使用的动作表，如：档、刺、拍、砍、削
	 * @return
	 */
	public Set<Action> getActions();
	
	/**
	 * 对所有实物执行指定的动作
	 * @param action
	 */
	public void doAction(String action);
	
	/**
	 * 对生命体执行指定的动作
	 * @param action
	 */
	public void doActionOnLive(Live live, String action);
	
	/**
	 * 生命值
	 */
	public Long getLive();
	
	/**
	 * 攻击力
	 * @return
	 */
	public Long getATK();
	
	/**
	 * 护甲
	 * @return
	 */
	public Long getArmor();
	
	/**
	 * 生命体承受攻击
	 * @param attack
	 */
	public void sufferAction(Attack attack);

}
