package com.snake.novel.game;

import com.snake.novel.game.Skill;
import com.snake.novel.model.NovelElement;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wenxy on 2017/1/11.
 */
public class Job extends NovelElement {

    public Job(){}
    public Job(String name){
        this.name = name;
    }
    public Job(String name,Collection<Skill> skills){
        this.name = name;
        if(null != skills && skills.size() > 0) {
            for (Skill skill : skills) {
                requiredSkills.add(skill);
            }
        }
    }

    private Set<Skill> requiredSkills = new HashSet<Skill>();

    public Set<Skill> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(Set<Skill> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }
}
