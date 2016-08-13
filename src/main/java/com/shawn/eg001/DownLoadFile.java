/**
 * Project Name:crawl-app001
 * File Name:DownLoadFile.java
 * Package Name:com.shawn.eg001
 * Date:2016年4月10日上午2:08:28
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.shawn.eg001;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * ClassName: DownLoadFile <br/>
 * Date: 2016年4月10日 上午2:08:28 <br/>
 * Description: TODO
 *
 * @author shawn
 * @version
 * @see
 */
public class DownLoadFile {
	/**
	 * 根据url和网页类型生成需要保存的网页的文件名，去除URL中的非文件名字符
	 */
	public String getFilenameByUrl(String url, String contentType) {
		url = url.substring(7);
		if ("html".equals(contentType)) {
			url = url.replaceAll("[\\?/:*|<>\"]", "_") + ".html";
			return url;
		} else {
			// 如application/pdf类型
			return url.replaceAll("[\\?/:*|<>\"]", "_") + "." + contentType.substring(contentType.lastIndexOf("/") + 1);
		}
	}

	/**
	 * 保存网页字节数组到本地文件，filepath为要保存的文件的相对路径
	 */
	private void saveToLocal(byte[] data, String filepath) {
		try {
			DataOutputStream out = new DataOutputStream(
					new FileOutputStream(new File("D://huaxia//crawl-app001//path//" + filepath)));
			for (int i = 0; i <= data.length - 1; i++) {
				out.write(data[i]);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 下载网页
	public String downloadFile(String url) {
		String filepath = null;
		try {
			HttpClient httpClient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url);
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).build();
			httpGet.setConfig(requestConfig);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (HttpStatus.SC_OK == statusCode) {
				HttpEntity httpEntity = httpResponse.getEntity();
				filepath = getFilenameByUrl(url, httpEntity.getContentType().getValue());
				byte[] data = EntityUtils.toByteArray(httpEntity);
				saveToLocal(data, filepath);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filepath;
	}
}
