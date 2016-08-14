/**
 * Project Name:crawl-app001
 * File Name:MyCrawler.java
 * Package Name:com.shawn.eg001
 * Date:2016年4月11日下午10:07:27
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.shawn.eg001;

import java.util.Set;

import org.htmlparser.util.ParserException;

/**
 * ClassName: MyCrawler <br/>
 * Date: 2016年4月11日 下午10:07:27 <br/>
 * Description: TODO
 *
 * @author shawn
 * @version
 * @see
 */
public class MyCrawler {
    //初始化根节点（可以有多个）
	private void initCrawlerWithSeeds(String[] seeds) {
		for(String seed:seeds){
			LinkQueue.addUnVisitedUrl(seed);
		}
	}
	
	public void crawling(String[] seeds){
		initCrawlerWithSeeds(seeds);
		
		while(!LinkQueue.unVisitedUrlIsEmpty() && LinkQueue.getVisitedUrlNum()<=500){
			LinkFilter filter= new LinkFilter(){
				public boolean accept(String url) {
					if (url.contains("jsedu.sh.cn")) {
						return true;
					} else {
						return false;
					}
				}
			};
			//待访问的url出队列
			String visitUrl = (String)LinkQueue.getUnVisitedUrl().deQueue();
			DownLoadFile downloadFile = new DownLoadFile();
			//下载页面
/*			System.out.println("download url start " + visitUrl);
			downloadFile.downloadFile(visitUrl);
			System.out.println("download url end " + visitUrl);*/
			//下载图片
			Set<String> images;
			try {
				images = HtmlParserTool.extractImage(visitUrl, filter);
				System.out.println("-------------------------------- image from :" + visitUrl);
				for(String imgUrl:images){
					System.out.println("download imgUrl start " + imgUrl);
					downloadFile.downloadFile(imgUrl);
					System.out.println("download imgUrl end " + imgUrl);
				}
			} catch (ParserException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			LinkQueue.addVisitedUrl(visitUrl);
			Set<String> links;
			try {
				links = HtmlParserTool.extractLinks(visitUrl, filter);
				for(String link:links){
					LinkQueue.addUnVisitedUrl(link);
				}
			} catch (ParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		MyCrawler crawler = new MyCrawler();
		crawler.crawling(new String[]{"http://zjex.jsedu.sh.cn/index/"});
		System.out.println("end cost =" + ((System.currentTimeMillis()-startTime)/1000));
		//cost 20 minutes
	}
}
