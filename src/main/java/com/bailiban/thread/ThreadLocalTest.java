package com.bailiban.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalTest {

    private ThreadLocal<Integer> countThreadLocal;
    private int count;
    private AtomicInteger countAtom;

    public ThreadLocalTest(int initCount) {
        countThreadLocal = new ThreadLocal<>();
        countThreadLocal.set(initCount);
        count = initCount;
        countAtom = new AtomicInteger(initCount);
    }

    public ThreadLocalTest() {
        this(0);
    }

    static class MyThread extends Thread {

        private ThreadLocalTest test;

        public MyThread(ThreadLocalTest test) {
            super();
            this.test = test;
        }

        @Override
        public void run() {
            int i = 10000;
            while (i-- > 0) {
                test.count++;
            }
            i = 10000;
            while (i-- > 0) {
                test.countAtom.incrementAndGet();
            }
            i = 10000;
            test.countThreadLocal.set(0);
            while (i-- > 0) {
                int tmp = test.countThreadLocal.get();
                test.countThreadLocal.set(tmp + 1);
            }
            System.out.println(getName() + ": " + test.count);
            System.out.println(getName() + ": " + test.countAtom.get());
            System.out.println(getName() + ": " + test.countThreadLocal.get());
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ThreadLocalTest test = new ThreadLocalTest();
        Thread t1 = new MyThread(test);
        Thread t2 = new MyThread(test);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(test.count);
        System.out.println(test.countAtom.get());
        System.out.println(test.countThreadLocal.get());
    }
}
