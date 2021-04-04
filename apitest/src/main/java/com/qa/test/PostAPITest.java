package com.qa.test;

import java.io.File;
import java.io.IOException;
	import java.util.HashMap;

	import org.apache.http.Header;
	import org.apache.http.client.ClientProtocolException;
	import org.apache.http.client.methods.CloseableHttpResponse;
	import org.apache.http.util.EntityUtils;
	import org.json.JSONObject;
	import org.testng.Assert;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
	import com.qa.client.RestClient;
import com.qa.data.Users;
import com.qa.util.TestUtil;

	public class PostAPITest extends TestBase  {
		
		CloseableHttpResponse response;
		RestClient restclient;

		public PostAPITest() throws IOException {
			super();
			// TODO Auto-generated constructor stub
		}

		TestBase testbase;
		String URL;
		
		@BeforeMethod	
		public void setUp() throws IOException {
			
			testbase=new TestBase();
			String apiURL=prop.getProperty("URL");
			String sericeURL=prop.getProperty("ServiceURL");	
			URL=apiURL+sericeURL;
		}
		
		@Test
		public void post() throws JsonGenerationException, JsonMappingException, IOException {
			
			restclient=new RestClient();		
			HashMap<String, String> headerMap = new HashMap<String, String>();
			headerMap.put("Content-Type", "application/json");
			
			//Jackson API			
			ObjectMapper mapper=new ObjectMapper();			
			Users users=new Users("morpheus","leader");
			
			//Java Object to Jason file
			mapper.writeValue(new File("\\Users\\sys\\eclipse-workspace\\apitest\\src\\main\\java\\com\\qa\\data\\users.json"),users);
			
			// Object to Jason String 
			
			String userjasonString=mapper.writeValueAsString(users);
			
			System.out.println(userjasonString);
			
			response=restclient.post(URL, userjasonString, headerMap);
			
			int statuscode=response.getStatusLine().getStatusCode();
			System.out.println("Status Code" +statuscode);
			
			Assert.assertEquals(statuscode, Response_status_Code_201,"success");
			
			String responsestring=EntityUtils.toString(response.getEntity(),"UTF-8");
			JSONObject jobject=new JSONObject(responsestring);
			System.out.println(responsestring);
			
			//Jason to java object
			
			Users usersresobj=mapper.readValue(responsestring,Users.class);	
			System.out.println(usersresobj);
			
			Assert.assertTrue(users.getName().equals(usersresobj.getName()));
			Assert.assertTrue(users.getJob().equals(usersresobj.getJob()));
			
			
		}

}
