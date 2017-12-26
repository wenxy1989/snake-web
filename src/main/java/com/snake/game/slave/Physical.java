package com.snake.game.slave;

import com.snake.novel.model.FlatPosition;

/**
 * 实物、物体
 * @author xue
 *
 */
public interface Physical {

	public View getView();
	public void setView(View view);
	
	public FlatPosition getPosition();
	
	public void setPosition(FlatPosition position);
	
	/**
	 * 长
	 * @return
	 */
	public Long getLength();
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
