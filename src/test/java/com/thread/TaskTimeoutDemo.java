package com.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 启动一个任务，然后等待任务的计算结果，如果等待时间超出预设定的超时时间，则中止任务。
 *
 * @author Chen Feng
 */
public class TaskTimeoutDemo {


    ExecutorService exec = Executors.newCachedThreadPool();

    public void testTask(Callable callable, int timeout) {
        Future<Boolean> future = exec.submit(callable);
        Boolean taskResult = null;
        String failReason = null;
        try {
            // 等待计算结果，最长等待timeout秒，timeout秒后中止任务
            taskResult = future.get(timeout, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            failReason = e.getLocalizedMessage();
        } catch (ExecutionException e) {
            failReason = e.getLocalizedMessage();
        } catch (TimeoutException e) {
            failReason = e.getLocalizedMessage();
            exec.shutdownNow();
        }

        System.out.println("\ntaskResult : " + taskResult);
        System.out.println("failReason : " + failReason);
    }
    public static void main(String[] args) {
        System.out.println("Start ...");
        TaskTimeoutDemo task = new TaskTimeoutDemo();
        task.testTask(new MyTask("-"), 10); // 任务成功结束后等待计算结果，不需要等到15秒
        task.testTask(new MyTask("*"), 6); // 只等待5秒，任务还没结束，所以将任务中止

        System.out.println("End!");
    }
}

class MyTask implements Callable<Boolean> {

    private String value;

    public MyTask(String value) {
        this.value = value;
    }

    @Override
    public Boolean call() throws Exception {
        // 总计耗时约10秒
        for (int i = 0; i < 50L; i++) {
            Thread.sleep(100); // 睡眠0.1秒
            System.out.print(value);
        }
        return Boolean.TRUE;
    }
}