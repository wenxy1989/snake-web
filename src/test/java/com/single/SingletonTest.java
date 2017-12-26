package com.single;

import org.junit.Test;

import java.util.Iterator;
import java.util.Stack;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by HP on 2017/3/24.
 */
class LazySingleton {

    private static LazySingleton instance = null;

    private LazySingleton() {
    }

    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    private int count = 100;
    private Stack<String> stack = new Stack<String>();

    public void println() {
        stack.add("Singleton." + count++);
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}

class HungrySingleton {
    private static final HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {
    }

    public static HungrySingleton getInstance() {
        return instance;
    }

    private int count = 100;
    private Stack<String> stack = new Stack<String>();

    public void println() {
        stack.add("Singleton." + count++);
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}

class LockSingleton {
    private static LockSingleton instance = null;

    public static LockSingleton getInstance() {
        if (null == instance) {
            synchronized (LockSingleton.class) {
                instance = new LockSingleton();
            }
        }
        return instance;
    }

    private LockSingleton() {
    }

    private int count = 100;
    private Stack<String> stack = new Stack<String>();

    public void println() {
        stack.add("Singleton." + count++);
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

}

class ThreadService {
    public void execute(Runnable runnable) {
        new Thread(runnable).start();
    }
}

public class SingletonTest {

    private static int count = 10000;

    @Test
    public void lazySingletonSpeedTest() throws InterruptedException {
        final long startMils = System.currentTimeMillis();
        final CountDownLatch lazyCountDownLatch = new CountDownLatch(count);
//        ThreadService service = new ThreadService();
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    LazySingleton.getInstance().println();
                    lazyCountDownLatch.countDown();
                }
            });
        }
        lazyCountDownLatch.await();
        System.out.println(LazySingleton.getInstance().toString());
        System.out.println("------");
        long endMils = System.currentTimeMillis();
        System.out.println("lazy singleton use time " + (endMils - startMils));
    }

    @Test
    public void hungrySingletonSpeedTest() throws InterruptedException {
        final long startMils = System.currentTimeMillis();
        final CountDownLatch hungryCountDownLatch = new CountDownLatch(count);
//        ThreadService service = new ThreadService();
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {

            service.execute(new Runnable() {
                @Override
                public void run() {
                    HungrySingleton.getInstance().println();
                    hungryCountDownLatch.countDown();
                }
            });
        }
        hungryCountDownLatch.await();
        System.out.println(HungrySingleton.getInstance().toString());
        System.out.println("------");
        long endMils = System.currentTimeMillis();
        System.out.println("hungry singleton use time " + (endMils - startMils));
    }

    @Test
    public void lockSingletonSpeedTest() throws InterruptedException {
        final long startMils = System.currentTimeMillis();
        final CountDownLatch lockCountDownLatch = new CountDownLatch(count);
//        ThreadService service = new ThreadService();
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    LockSingleton.getInstance().println();
                    lockCountDownLatch.countDown();
                }
            });
        }
        lockCountDownLatch.await();
        System.out.println(LockSingleton.getInstance().toString());
        System.out.println("------");
        long endMils = System.currentTimeMillis();
        System.out.println("lock singleton use time " + (endMils - startMils));
    }

}
