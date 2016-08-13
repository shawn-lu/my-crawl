/**
 * Project Name:crawl-app001
 * File Name:LinkQueue.java
 * Package Name:com.shawn.eg001
 * Date:2016年4月10日上午12:11:02
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */


package com.shawn.eg001;

import java.util.HashSet;
import java.util.Set;

/**
 * ClassName: LinkQueue <br/>
 * Date: 2016年4月10日 上午12:11:02 <br/>
 * Description: TODO 
 *
 * @author shawn
 * @version 
 * @see
 */
public class LinkQueue {
	//已访问的url集合
	private static Set visitedUrl = new HashSet();
	//待访问的url集合
	private static Queue unVisitedUrl = new Queue();
	
	//获得待访问队列
	public static Queue getUnVisitedUrl() {
		return unVisitedUrl;
	}
	
	//添加到访问过的url队列中
	public static void addVisitedUrl(String url){
		visitedUrl.add(url);
	}
	//移除访问过的url队列
	public void removeVisitedUrl(Object url){
		visitedUrl.remove(url);
	}
	//未访问的url出队列
	public static Object unVisitedUrlDeQueue(){
		return unVisitedUrl.deQueue();
	}
	//保证每个url只被访问一次
	public static void addUnVisitedUrl(String url){
		if(null!=url && !url.trim().equals("") && !visitedUrl.contains(url) && !unVisitedUrl.contains(url)){
			unVisitedUrl.enQueue(url);
		}
	}
	//获得已经访问的URL数目
	public static int getVisitedUrlNum() {
		return visitedUrl.size();
	}
	//判断未访问的URL队列中是否为空
	public static boolean unVisitedUrlIsEmpty(){
		return unVisitedUrl.isQueueEmpty();
	}
}

