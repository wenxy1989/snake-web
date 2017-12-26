package com.snake.web.slave;

import com.snake.novel.game.*;
import com.snake.novel.model.Character;
import com.snake.novel.model.Organization;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wenxy on 2017/1/11.
 */
public class LogicTest {

    @Test
    public void equalsTest(){
        boolean equals = new Skill("放哨").equals(new Skill("放哨"));
        System.out.println(equals);
    }

    /*class CatchSlave implements Action {

        protected String command;
    }*/

    @Test
    public void mainTest(){
        TextView view = new TextView();
        Character liuxuerui = new Character("瑞瑞","liuxuerui");
        liuxuerui.setPosition(new Position(5,5));
        Character youwenle = new Character("文乐","youwenle");
        Character caozhifeng = new Character("小曹","caozhifeng");
        Character gaokefan = new Character("小高","gaokefan");
        Character haoruipeng = new Character("郝比","haoruipeng");
        Character biguozeng = new Character("老毕","biguozeng");
        Character wanglike = new Character("小科","wanglike");
        Character qianyu = new Character("钱玉","qianyu");
        Character wenxueyong = new Character("管理员","wenxueyong");
        Skill skill = new Skill("抓起来","catch","");
        skill.setPosition(new Position(3, 5));
        skill.setView(view);
        wenxueyong.doSkill(skill, liuxuerui);

        Food food = new Food();
        food.setEnergy(200l);
        youwenle.collect(food);
        youwenle.abandon(food);
        qianyu.collect(food);
    }

    @Test
    public void characterApplyJobTest(){
        com.snake.novel.model.Character user = new Character();
        user.setName("wenxueyong");
        user.learn(new Skill("打铁"));
//        user.learnSkill(new Skill("织布"));
        user.learn(new Skill("舞剑"));
        user.learn(new Skill("放哨"));
        Set<Skill> requiredSkills = new HashSet<Skill>();
        requiredSkills.add(new Skill("打铁"));
        requiredSkills.add(new Skill("舞剑"));
        requiredSkills.add(new Skill("放哨"));
        Job job = new Job("护院",requiredSkills);
        System.out.println(user.applyJob(job));
    }

    @Test
    public void characterFormationOrganizationTest(){
        List<Job> jobs = new ArrayList<Job>();
        jobs.add(new Job("文员"));
        jobs.add(new Job("老爷"));
        jobs.add(new Job("大厨"));
        jobs.add(new Job("护院"));
        jobs.add(new Job("奶娘"));
        List<Job> requiredJobs = new ArrayList<Job>();
        requiredJobs.add(new Job("护院"));
        requiredJobs.add(new Job("大厨"));
        requiredJobs.add(new Job("奶娘"));
        requiredJobs.add(new Job("老爷"));
        requiredJobs.add(new Job("师爷"));
        Organization organization = new Organization("护院",requiredJobs);
        System.out.println(organization.formation(jobs));
    }

}
