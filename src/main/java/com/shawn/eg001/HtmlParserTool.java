/**
 * Project Name:crawl-app001
 * File Name:HtmlParserTool.java
 * Package Name:com.shawn.eg001
 * Date:2016年4月10日下午2:55:20
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */


package com.shawn.eg001;

import java.util.HashSet;
import java.util.Set;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * ClassName: HtmlParserTool <br/>
 * Date: 2016年4月10日 下午2:55:20 <br/>
 * Description: TODO 
 *
 * @author shawn
 * @version 
 * @see
 */
public class HtmlParserTool {
	public static Set<String> extractLinks(String url,LinkFilter filter) throws ParserException{
		Set<String> links = new HashSet<String>();
		Parser parser = new Parser(url);
		parser.setEncoding("UTF-8");
		NodeFilter frameFilter = new NodeFilter(){
			public boolean accept(Node node) {
				if(node.getText().startsWith("frame src=")){
					return true;
				}else{
					return false;
				}
			};
		};
		OrFilter linkFilter = new OrFilter(new NodeClassFilter(LinkTag.class),frameFilter);
		NodeList list = parser.extractAllNodesThatMatch(linkFilter);
		for(int i=0;i<=list.size()-1;i++){
			Node tag = list.elementAt(i);
			if(tag instanceof LinkTag){
				LinkTag link = (LinkTag)tag;
				String linkUrl = link.getLink();
				if(filter.accept(linkUrl)){
					links.add(linkUrl);
				}else{
					String frame = tag.getText();
					int start = frame.indexOf("src=");
					if(start != -1){
						frame = frame.substring(start);
						int end = frame.indexOf(" ");
						if(end == -1){
							end = frame.indexOf(">");
						}
						String frameUrl = frame.substring(5, end - 1);
						if(filter.accept(frameUrl)){
							links.add(frameUrl);
						}
					}
				}
			}
		}
		return links;
	}
	

	public static Set<String> extractImage(String url,LinkFilter filter) throws ParserException{
		Set<String> images = new HashSet<String>();
		Parser parser = new Parser(url);
		parser.setEncoding("UTF-8");
		NodeFilter nodeFilter = new NodeClassFilter(ImageTag.class);
		NodeList list = parser.extractAllNodesThatMatch(nodeFilter);
		for(int i=0;i<=list.size()-1;i++){
			Node tag = list.elementAt(i);
			if(tag instanceof ImageTag){
				ImageTag image = (ImageTag)tag;
				String imageUrl = image.getImageURL();
				if(filter.accept(imageUrl) && !imageUrl.contains(".gif")){
					images.add(imageUrl);
				}
			}
		}
		return images;
	}
	
	//for test
	public static void main(String[] args) {
		String url = "http://zjex.jsedu.sh.cn/index/";
		LinkFilter filter= new LinkFilter(){
			@Override
			public boolean accept(String url) {
				if (url.startsWith("http://zjex.jsedu.sh.cn")) {
					return true;
				} else {
					return false;
				}
			}
		};
		try {
			Set<String> images = extractImage(url, filter);
			for(String image:images){
				System.out.println(image);
			}
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

