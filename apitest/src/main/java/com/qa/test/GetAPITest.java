package com.qa.test;

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

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase  {

	CloseableHttpResponse response;
	RestClient restclient;
	
	public GetAPITest() throws IOException {
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
	public void get() throws ClientProtocolException, IOException {		
		 restclient=new RestClient();
		response=restclient.get(URL);
		
		int statuscode=response.getStatusLine().getStatusCode();
		System.out.println("Status Code" +statuscode);
		
		Assert.assertEquals(statuscode, Response_status_Code_200,"success");
		
		String ResponseString=EntityUtils.toString(response.getEntity(),"UTF-8");
		
		JSONObject jasonobject=new JSONObject(ResponseString);
		System.out.println("Jason Response:"+jasonobject);
		
		String valueofperpage=TestUtil.getValueByJPath(jasonobject,"/per_page");
		System.out.println("Value of per page: "+valueofperpage);
		
		Assert.assertEquals(Integer.parseInt(valueofperpage),6);
		
		String totalvalue =TestUtil.getValueByJPath(jasonobject,"/total");
		System.out.println("Toal Value : "+totalvalue);
		
		Assert.assertEquals(Integer.parseInt(totalvalue), 12);		
		
		//get the value from jason array
		
		String data =TestUtil.getValueByJPath(jasonobject,"/data[1]/first_name");
		System.out.println("Data from jason array  : "+data);
		
		Assert.assertEquals(data, "Lindsay");		
		
		Header[] headerdata=response.getAllHeaders();
		
		HashMap<String,String> allheaders=new HashMap<String,String>();
		
		for(Header header:headerdata) {
			
			allheaders.put(header.getName(),header.getValue());
			
		}
		
		System.out.println("All Header Data: "+allheaders);
		
	}
	@Test(priority=2)
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException{
		restclient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
//		headerMap.put("username", "test@amazon.com");
//		headerMap.put("password", "test213");
//		headerMap.put("Auth Token", "12345");
		
		response = restclient.get(URL,headerMap);
		
		//a. Status Code:
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println("Status Code--->"+ statusCode);
		
		Assert.assertEquals(statusCode,Response_status_Code_200, "Status code is not 200");

		//b. Json String:
		String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
		
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API---> "+ responseJson);
		
		//single value assertion:
		//per_page:
		String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("value of per page is-->"+ perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);
		
		//total:
		String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("value of total is-->"+ totalValue);		
		Assert.assertEquals(Integer.parseInt(totalValue), 12);

		//get the value from JSON ARRAY:
		String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");

		System.out.println(lastName);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(firstName);
		
		
		//c. All Headers
		Header[] headersArray =  response.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();	
		for(Header header : headersArray){
			allHeaders.put(header.getName(), header.getValue());
		}	
		System.out.println("Headers Array-->"+allHeaders);
		
	}
	

}
