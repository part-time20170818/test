package com.wg.common.http;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonNode;

import com.wg.common.JsonUtil;


public class HttpUtil {
	private CloseableHttpClient httpclient = HttpClients.createDefault();
	
	
	

	/**
	 * 执行通用get请求时可用
	 * 
	 * @param url
	 *            访问的请求信息
	 * @return 基本的请求信息
	 * @throws PassportRemoteException
	 */
	protected JsonNode execute(String url)  {
		HttpGet httpget = new HttpGet(url);
		return callHttpService(httpget);
	}

	/**
	 * 使用url post 方式提交
	 * 
	 * @param url
	 *            提交目标url地址
	 * @param uefEntity
	 *            待提交entity数据
	 * @return 相应结果
	 * @throws PassportRemoteException
	 */
	protected JsonNode execute(String url, HttpEntity uefEntity)  {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(uefEntity);
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		return callHttpService(httpPost);
	}
	// http发送报文
	public JsonNode callHttpService(HttpUriRequest request) {
		JsonNode json = null;
		
		
		 long startTime=System.currentTimeMillis();
		 System.out.println("请求时间：------------------"+startTime);
		 try {
			 CloseableHttpResponse response = httpclient.execute(request);
			 System.out.println("响应耗时-------------------------"+(float)(System.currentTimeMillis()-startTime)/1000);
			System.out.println("响应耗时-------------------------"+(float)(System.currentTimeMillis()-startTime)/1000);
			HttpEntity respEntity = response.getEntity();
			System.out.println("response.getEntity()--------------------------------"+response.getEntity());
			if (respEntity != null) {
				String respText = EntityUtils.toString(respEntity, "UTF-8");
				
				System.out.println("respText:-----------------------------------------"+respText);
				System.out.println("返回的json数据：" + respText);
				json = JsonUtil.string2Json(respText);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;

	}
}
