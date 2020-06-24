package com.example.swm;

public class aa {
    public static void main(String[] args) {
        final Object object = new Object();
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ////在局部内部类中使用方法的局部变量
                System.out.println("::::::"+object);
            }
        }.start();
    }
}
