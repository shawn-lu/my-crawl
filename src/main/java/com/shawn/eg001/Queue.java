/**
 * Project Name:crawl-app001
 * File Name:Queue.java
 * Package Name:com.shawn.eg001
 * Date:2016年4月10日上午12:09:31
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */


package com.shawn.eg001;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * ClassName: Queue <br/>
 * Date: 2016年4月10日 上午12:09:31 <br/>
 * Description: 队列，保存将要访问的url
 *
 * @author shawn
 * @version 
 * @see
 */
public class Queue {
	//使用链表实现队列
	private LinkedList queue = new LinkedList();
	//入队列
	public void enQueue(Object t){
		queue.add(t);
	}
	//出队列
	public Object deQueue(){
		return queue.removeFirst();
	}
	//判断队列是否为空
	public boolean isQueueEmpty(){
		return queue.isEmpty();
	}
	//判断队列是否包含t
	public boolean contains(Object t){
		return queue.contains(t);
	}
	
}

