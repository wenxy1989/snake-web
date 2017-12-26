package com.snake.novel.model;

import com.base.utils.ArrayTools;
import com.snake.novel.game.Job;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Organization extends NovelElement {

    private List<Job> requiredJobs = new ArrayList<Job>();

    public Organization(){}

    public Organization(String name,List<Job> jobs){
        this.name = name;
        definition(jobs);
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

    public void definition(List<Job> jobs) {
        if(null != jobs && jobs.size() > 0){
            for(Job job : jobs){
                requiredJobs.add(job);
            }
        }
    }

    public boolean formation(List<Job> characters){
        return ArrayTools.containsAll(characters,this.requiredJobs);
    }

}
