/**
 * Project Name:crawl-app001
 * File Name:MyCrawler.java
 * Package Name:com.shawn.eg002
 * Date:2016-8-13下午3:30:01
 *
 */


package com.shawn.eg002;

import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * ClassName: MyCrawler <br/>
 * Date: 2016-8-13 下午3:30:01 <br/>
 * Description: 基于多线程的爬虫示例 
 *
 * @author luxf
 * @version 
 * @see
 */
public class MyCrawler {
    protected static LinkedBlockingQueue<String> todoQueue  = new LinkedBlockingQueue();  
    protected static CopyOnWriteArraySet<String> visitedSet = new  CopyOnWriteArraySet();
    
    public static void main(String[] args) {
        int threadCount = 1;
        ExecutorService pool = Executors.newFixedThreadPool(threadCount); 
        long startTime = System.currentTimeMillis();
        todoQueue.add("http://zjex.jsedu.sh.cn/index/");
        for(int i=0;i<=threadCount-1;i++){
//            pool.submit(new UrlProcessTask());
            pool.execute(new TestThread());
        }
        pool.shutdown();
        while(true){
            if(pool.isShutdown()){
                System.out.println("shutdown");
            }
            if(pool.isTerminated()){
                System.out.println("terminated");
            }
        }
//        System.out.println("end cost =" + ((System.currentTimeMillis()-startTime)/1000));
    }
}

