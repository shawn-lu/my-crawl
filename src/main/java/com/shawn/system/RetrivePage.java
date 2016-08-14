/**
 * Project Name:crawl-app001
 * File Name:RetrivePage.java
 * Package Name:com.shawn.system
 * Date:2016年4月6日下午11:59:01
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.shawn.system;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

/**
 * ClassName: RetrivePage <br/>
 * Date: 2016年4月6日 下午11:59:01 <br/>
 * Description: TODO
 *
 * @author shawn
 * @version
 * @see
 */
public class RetrivePage {
	private static HttpClient httpClient = HttpClients.createDefault();

	public static boolean downloadPage(String path) throws ClientProtocolException, IOException {
		InputStream input = null;
		OutputStream output = null;
		HttpPost postMethod = new HttpPost(path);
		NameValuePair[] postData = new NameValuePair[2];
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("name", "baidu"));
		formparams.add(new BasicNameValuePair("password", "abcde"));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
		postMethod.setEntity(new UrlEncodedFormEntity(formparams));

		HttpResponse httpResponse = httpClient.execute(postMethod);
		int statusCode = httpResponse.getStatusLine().getStatusCode();
//		System.out.println(statusCode);
		if (statusCode == HttpStatus.SC_OK) {
			input = httpResponse.getEntity().getContent();
			String filename = path.substring(path.lastIndexOf('/') + 1);
	        System.out.println("output path:"+filename);
			output = new FileOutputStream(filename);

			int tempByte = -1;
			while ((tempByte = input.read()) > 0) {
				output.write(tempByte);
			}
			if (input != null) {
				input.close();
			}
			if (output != null) {
				output.close();
			}
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		try {
			RetrivePage.downloadPage("http://www.lietu.com/index.html");
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
