package com.snake.novel.game;

import com.snake.novel.model.Character;
import com.snake.novel.model.NovelElement;

import java.io.Serializable;

/**
 * 技能/技术
 * @author xue
 *
 */
public class Skill extends NovelElement {

    public Skill(){
    }
    public Skill(String name){
        this.name = name;
    }
    public Skill(String name,String command){
        this.name = name;
        this.command = command;
    }
    public Skill(String name,String command,String logicScript){
        this.name = name;
        this.command = command;
        this.logicScript = logicScript;
    }

    private String logicScript;

    @Override
    public boolean equals(Object obj) {
        boolean result = null != obj;
        result = result && ((Skill)obj).getName().equals(this.name);
        result = result && ((Skill)obj).getCommand().equals(this.command);
        return result || super.equals(obj);
    }

    @Override
	public Serializable getObjectId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUniqueIdName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getNovelId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNovelId(Long novelId) {
		// TODO Auto-generated method stub
		
	}

    private String command;

    public void setCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    private TextView view;

    public TextView getView() {
        return view;
    }

    public void setView(TextView view) {
        this.view = view;
    }

    private Position position = new Position();

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void effect(com.snake.novel.model.Character physical) {
        Position actionResult = getPosition().effect(physical.getPosition());
        Position targetResult = physical.getPosition().effect(getPosition());
        physical.setPosition(targetResult);
        setPosition(actionResult);
    }

    public void run(){
        this.view.rendering(this.physical);
        this.view.renderingTarget(this, this.target);
        effect(this.target);
        this.view.rendering(this.physical);
        this.view.renderingTarget(this, this.target);
    }

    public Character getCharacter() {
        return physical;
    }

    public void setCharacter(Character physical) {
        this.physical = physical;
    }

    private Character physical;
    private Character target;

    public Character getTarget() {
        return target;
    }

    public void setTarget(Character target){
        this.target = target;
    }

}
