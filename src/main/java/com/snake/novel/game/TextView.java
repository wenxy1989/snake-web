package com.snake.novel.game;


import com.snake.novel.model.Character;

/**
 * Created by HP on 2017/1/12.
 */
public class TextView {

    public void rendering(com.snake.novel.model.Character physical) {
        System.out.println("x:"+physical.getPosition().getX() + ", y:" + physical.getPosition().getY());
    }

    public void renderingTarget(Skill action, Character target) {
        System.out.println("action command : "+action.getCommand());
        System.out.println("action x:"+action.getPosition().getX() + ", y:" + action.getPosition().getY());
        System.out.println("target x:"+target.getPosition().getX() + ", y:" + target.getPosition().getY());
    }
}
