package com.snake.game.slave;

/**
 * Created by HP on 2017/1/12.
 */
public interface View {

    public void rendering(Physical physical);
    public void renderingTarget(Action action, Physical target);

}
