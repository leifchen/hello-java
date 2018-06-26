package com.chen.concurrent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * DaemonThread
 * @Author LeifChen
 * @Date 2018-06-25
 */
public class DaemonThread implements Runnable {

    private final static int TIMES = 99;

    @Override
    public void run() {
        System.out.println("进入守护线程" + Thread.currentThread().getName());
        try {
            writeToFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("退出守护线程" + Thread.currentThread().getName());
    }

    private void writeToFile() throws Exception {
        File filename = new File("e://test.txt");
        OutputStream os = new FileOutputStream(filename, true);
        int count = 0;
        while (count < TIMES) {
            os.write(("\r\nword" + count).getBytes());
            System.out.println("守护进程" + Thread.currentThread().getName() + "向文件中写入" + count++);
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) {
        DaemonThread daemonThread = new DaemonThread();
        Thread thread = new Thread(daemonThread);
        thread.setDaemon(true);
        thread.start();

        Scanner sc = new Scanner(System.in);
        sc.next();
    }
}
