package com.module.novel.game;

/**
 * 实物、物体
 * @author xue
 *
 */
public interface Physical {
	
	public Position getPosition();
	
	public void setPosition(Position position);
	
	/**
	 * 长
	 * @return
	 */
	public Long getLongth();
	/**
	 * 宽
	 * @return
	 */
	public Long getWidth();
	/**
	 * 高
	 * @return
	 */
	public Long getHeight();
	
	/**
	 * 重量
	 * @return
	 */
	public Long getWeight();
	
	/**
	 * 能量
	 * @return
	 */
	public Long getEnergy();
	
	/**
	 * 硬度
	 * @return
	 */
	public Long getHardness();
	
	/**
	 * 精神力
	 * @return
	 */
	public Long getSpirit();

}
