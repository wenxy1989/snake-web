package com.thread;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class BlueToothManagerTests {

    class CallbackTest implements SingleThreadManager.Callback {
        @Override
        public void success() {

        }

        @Override
        public void failed(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void executeTest() throws InterruptedException, TimeoutException, ExecutionException {
        SingleThreadManager.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("----------- need 3 seconds ------------------");
                    Thread.sleep(1000 * 5);
                    System.out.println("----------- use 3 seconds ------------------");
                } catch (InterruptedException e) {
                    System.out.println("----------- use 3 seconds sleep interrupted ------------------");
                }
            }
        }, new CallbackTest());
        SingleThreadManager.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("----------- need 2 seconds ------------------");
                    Thread.sleep(1000 * 2);
                    System.out.println("----------- use 2 seconds ------------------");
                } catch (InterruptedException e) {
                    System.out.println("----------- use 2 seconds sleep interrupted ------------------");
                }
            }
        }, new CallbackTest());
        SingleThreadManager.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("----------- need 1 seconds ------------------");
                    Thread.sleep(1000);
                    System.out.println("----------- use 1 seconds ------------------");
                } catch (InterruptedException e) {
                    System.out.println("----------- use 2 seconds sleep interrupted ------------------");
                }
            }
        }, new CallbackTest());
        System.out.println("----------- execute thread over ------------------");
    }
}