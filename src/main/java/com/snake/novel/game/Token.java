package com.snake.novel.game;

import com.base.utils.ArrayTools;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by HP on 2017/1/13.
 */
public class Token {

    private Set<Lock> openLocks = new HashSet<Lock>();

    public Set<Lock> getOpenLocks() {
        return openLocks;
    }

    public void setOpenLocks(Set<Lock> openLocks) {
        this.openLocks = openLocks;
    }

    public boolean open(Lock lock){
        return ArrayTools.contains(openLocks,lock);
    }

}
