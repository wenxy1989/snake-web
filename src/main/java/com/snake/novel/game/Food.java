package com.snake.novel.game;

/**
 * food
 * Created by HP on 2017/1/13.
 */
public class Food {

    private Lock lock;

    private Position position;

    private Long energy;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Long getEnergy() {
        return energy;
    }

    public void setEnergy(Long energy) {
        this.energy = energy;
    }

    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }

    public void free() {
        status("free");
        this.lock = null;
    }

    public void hold(Lock lock) {
        this.lock = lock;
        status("hold");
    }

    private void status(String acton){
        System.out.println(this.lock.getToken() + " , action " + acton);
    }

}
