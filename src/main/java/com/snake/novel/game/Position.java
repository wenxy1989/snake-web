package com.snake.novel.game;

public class Position {

    private int x=0;
    private int y=0;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Position(){}
    public Position(int x,int y){
        this.x = x;
        this.y = y;
    }

    public Position effect(Position position) {
        int resultX = this.x + position.x;
        int resultY = this.y + position.y;
        return new Position(resultX,resultY);
    }
}