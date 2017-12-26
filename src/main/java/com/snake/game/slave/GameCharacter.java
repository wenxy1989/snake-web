package com.snake.game.slave;

public interface GameCharacter extends Live {

	/**
	 * 指向性动作
	 * @param action
	 * @param physical
	 */
	public void doAction(Action action, Physical physical);
	
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

    void setCurrentCharacter(Character character);

    Character getCurrentCharacter();

    Character getEnemy();

    void setEnemy(Character enemy);

    void learn(Action action);
}
