package com.snake.novel.game;

/**
 * Created by HP on 2017/1/13.
 */
public class Lock {

    private String token;

    public Lock(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object obj) {
        return null != obj && ((Lock)obj).getToken().equals(this.token) || super.equals(obj) ;
    }
}
