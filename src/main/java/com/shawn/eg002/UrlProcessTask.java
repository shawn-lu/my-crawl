/**
 * Project Name:crawl-app001
 * File Name:UrlProcessTask.java
 * Package Name:com.shawn.eg002
 * Date:2016-8-13下午3:49:22
 *
 */


package com.shawn.eg002;

import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.htmlparser.util.ParserException;

import com.shawn.eg001.DownLoadFile;
import com.shawn.eg001.HtmlParserTool;
import static com.shawn.eg002.MyCrawler.*;;

/**
 * ClassName: UrlProcessTask <br/>
 * Date: 2016-8-13 下午3:49:22 <br/>
 * Description: 负责待处理，已处理url的入队，解析url 
 *
 * @author luxf
 * @version 
 * @see
 */
public class UrlProcessTask implements Runnable{
    private AtomicInteger order = new AtomicInteger(0);
    public void run() {
        while(visitedSet.size()<=500){
            order.incrementAndGet();
            System.out.println(Thread.currentThread().getId() + "start ,order:"+order);
 /*           if(todoQueue.isEmpty()){
                try {
                    wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }*/
            //待访问的url出队列
            String toVisitUrl = null;
            try {
                toVisitUrl = todoQueue.take();
            } catch (InterruptedException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
            DownLoadFile downloadFile = new DownLoadFile();
            LinkFilterImpl filter = new LinkFilterImpl();
            //下载页面
/*          System.out.println("download url start " + visitUrl);
            downloadFile.downloadFile(visitUrl);
            System.out.println("download url end " + visitUrl);*/
            //下载图片
            Set<String> images;
            try {
                images = HtmlParserTool.extractImage(toVisitUrl, filter);
                System.out.println("-------------------------------- image from :" + toVisitUrl);
                for(String imgUrl:images){
//                    System.out.println("download imgUrl start " + imgUrl);
                    downloadFile.downloadFile(imgUrl);
//                    System.out.println("download imgUrl end " + imgUrl);
                }
            } catch (ParserException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            visitedSet.add(toVisitUrl);
            Set<String> links;
            try {
                links = HtmlParserTool.extractLinks(toVisitUrl, filter);
                for(String link:links){
                    todoQueue.put(link);
                }
            } catch (ParserException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InterruptedException e) {
                // TODO: handle exception
                e.printStackTrace();
            }finally{
                System.out.println(Thread.currentThread().getId() + "end ,order:"+order);
            }
        }
    }

  

}

