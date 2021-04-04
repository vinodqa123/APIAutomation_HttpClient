package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;

public class RestClient {
	
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		
		// Get Method 
		CloseableHttpClient httpclient= HttpClients.createDefault();
		HttpGet httpget=new HttpGet(url);
		CloseableHttpResponse response= httpclient.execute(httpget);		
		 return response;
				 		
	}
	public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); //http get request
		
		for(Entry<String, String> entry : headerMap.entrySet()){
			httpget.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closebaleHttpResponse =  httpClient.execute(httpget); //hit the GET URL
		return closebaleHttpResponse;
			
		}
	
	//Post call	
	public CloseableHttpResponse post(String url,String entityString,HashMap<String,String> headermap) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpclient=HttpClients.createDefault();
		HttpPost httppost=new HttpPost(url);  // URL
		
		for(Entry<String, String> entry : headermap.entrySet()){  // Headers
			httppost.addHeader(entry.getKey(), entry.getValue());
		}
		
		httppost.setEntity(new StringEntity(entityString));  // Payload		
		CloseableHttpResponse closebaleHttpResponse	=httpclient.execute(httppost);				
		return closebaleHttpResponse;
		
	}
	
}
