package com.snake.game.slave;


public interface Action {

	public View getView();
	public void setView(View view);

	public Position getPosition();
	
	public void setPosition(Position position);

	public void effect(Physical physical);

	/**
	 * 发出方
	 * @return
	 */
	public void setPhysical(Physical physical);
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
	public String getCommand();

	public void run();

	public void setTarget(Physical physical);
}
