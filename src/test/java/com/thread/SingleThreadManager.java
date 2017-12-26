package com.thread;

import java.util.Queue;
import java.util.concurrent.*;

/**
 * Created by HP on 2017/4/1.
 */
public class SingleThreadManager implements Runnable {

    private SingleThreadManager() {
    }

    private static int timeout = 5;

    private static Queue<Task> taskQueue = new LinkedBlockingQueue<Task>();

    public static void setTimeout(int timeout) {
        SingleThreadManager.timeout = timeout;
    }

    public static void execute(final Runnable runnable, final Callback callback) {
        if (null != runnable) {
            synchronized (taskQueue) {
                Task task = new Task(runnable, callback);
//                if (null == running || running.isCancelled() || running.isDone()) {
                if (taskQueue.isEmpty()) {
                    taskQueue.offer(task);
                    new Thread(new SingleThreadManager()).start();
                } else {
                    taskQueue.offer(task);
                }
            }
        }
    }

    @Override
    public void run() {
        Task task = null;
        while ((task = taskQueue.peek()) != null) {
            startRunnable(task.getRunnable(), task.getCallback());
            synchronized (taskQueue) {
                taskQueue.poll();
            }
        }
    }


    protected void startRunnable(final Runnable runnable, final Callback callback) {
        ExecutorService service = Executors.newFixedThreadPool(1);
        Future running = null;
        try {
            running = service.submit(runnable);
            running.get(timeout, TimeUnit.SECONDS);
            if (null != callback) {
                callback.success();
            }
        } catch (Exception e) {
            running.cancel(true);
            if (null != callback) {
                callback.failed(e);
            }
        } finally {
            service.shutdownNow();
        }
    }

    public interface Callback {
        public void success();

        public void failed(Exception e);
    }

}

class Task {

    public Task(Runnable runnable, SingleThreadManager.Callback callback) {
        this.runnable = runnable;
        this.callback = callback;
    }

    private Runnable runnable;
    private SingleThreadManager.Callback callback;

    public Runnable getRunnable() {
        return runnable;
    }

    public SingleThreadManager.Callback getCallback() {
        return callback;
    }
}
