/**
 * Project Name:crawl-app001
 * File Name:TestThread.java
 * Package Name:com.shawn.eg002
 * Date:2016-8-14下午4:58:01
 *
 */


package com.shawn.eg002;

/**
 * ClassName: TestThread <br/>
 * Date: 2016-8-14 下午4:58:01 <br/>
 * Description: TODO 
 *
 * @author luxf
 * @version 
 * @see
 */
public class TestThread implements Runnable{

    public void run() {
        int i = 0;
        while(i<=2){
            try {
                Thread.currentThread().sleep(1000);
                System.out.println(Thread.currentThread().getId()+",i="+i);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally{
                i++;
            }
        }
        System.out.println(Thread.currentThread().getId()+" exit");
    }

}

