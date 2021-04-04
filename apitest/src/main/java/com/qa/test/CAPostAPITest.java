package com.qa.test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;
import com.qa.data.Users2;
import com.qa.util.PaymentForm;
import com.qa.util.TestUtil;

public class CAPostAPITest extends TestBase  {
		CloseableHttpResponse response;
		RestClient restclient;
		String DateofVisit="12/09/2020";
		
		public String Validation_message_Empty_Amount="Value is required and can't be empty";
		public String Validation_message_Invalid_Amount="Please enter valid amount";
		public String Validation_message_PhoneNumber="Please enter a valid phone number";
		public String Validation_message_ProviderIdentification="Provide valid  lp_provider_identification value.";
		public String Validation_message_FirstName="Value is required and can't be empty";
		public String Validation_message_LastName="Value is required and can't be empty";
				
		public CAPostAPITest() throws IOException {
			super();
			// TODO Auto-generated constructor stub
		}

		TestBase testbase;
		String URL;
		
		
		@BeforeMethod	
		public void setUp() throws IOException {
			
			testbase=new TestBase();
			String apiURL=prop.getProperty("PatrespURL");
			String sericeURL=prop.getProperty("ServiceURL");	
			URL=apiURL;
			
			
		}
		
		@DataProvider
		public Object[][] patRespData() throws IOException {
			Object[][] data=TestUtil.getTestPRData();
			return data ;
									
		}
		
		@Test (dataProvider="patRespData")
		public void post(String TestCase,String Amount,String PaymentType,String FirstName,String LastName,String PhoneNumber,String ProviderIdentification,String ReferenceNumber,String ReturnUrl,String Token ) throws Exception {
			String AuthToken="Bearer "+Token;
			System.out.println(AuthToken);
			
			restclient=new RestClient();		
			HashMap<String, String> headerMap = new HashMap<String, String>();
			headerMap.put("Content-Type", "application/json");
			headerMap.put("Host", "apisandbox.liquid-payments.com");
			headerMap.put("Accept", "application/json");
			headerMap.put("Authorization",AuthToken);
			
			//Jackson API			
			ObjectMapper mapper=new ObjectMapper();		
									
			Users2 users=new Users2(Amount,PaymentType,FirstName,LastName, PhoneNumber,DateofVisit,ProviderIdentification,ReferenceNumber,ReturnUrl,Token);
			//Java Object to Json file
			mapper.writeValue(new File("\\Users\\sys\\eclipse-workspace\\apitest\\src\\main\\java\\com\\qa\\data\\users2.json"),users);
			
			// Object to Jason String 
			String userjasonString=mapper.writeValueAsString(users);
			
			System.out.println("Input Data: "+userjasonString);
			
			response=restclient.post(URL, userjasonString, headerMap);
			
			int statuscode=response.getStatusLine().getStatusCode();
			System.out.println("Status Code: " +statuscode);
	
			
		
			if(statuscode==Response_status_Code_200) {
			
				String responsestring=EntityUtils.toString(response.getEntity(),"UTF-8");
				JSONObject jobject=new JSONObject(responsestring);
		
				//Json to java object
				Users2 usersresobj=mapper.readValue(responsestring,Users2.class);	
				System.out.println("Response String: "+usersresobj);	
				System.out.println(usersresobj);
		
				System.out.println("Transaction ID:"+usersresobj.getLp_lookupid());	
				System.out.println("Reference Number :"+usersresobj.getLp_referenceid());	
				System.out.println("Response Text :"+usersresobj.getLp_respreason_text());
				System.out.println("Payment URL :"+usersresobj.getUrl());
				String PaymentUrl=usersresobj.getUrl();
				
				if(PaymentUrl!=null){
					
					Thread.sleep(5000);
					PaymentForm.InitiatePayment(PaymentUrl,Amount);
					
				}
				
			}
			if(statuscode==Response_status_Code_422) {
				
				String responsestring=EntityUtils.toString(response.getEntity(),"UTF-8");
				JSONObject jobject=new JSONObject(responsestring);
		
				//Json to java object
				Users2 usersresobj=mapper.readValue(responsestring,Users2.class);	
				System.out.println("Response String: "+usersresobj);									
				String Validation_Message_Amount=usersresobj.getLp_amount();
				String Validation_Message_FirstName=usersresobj.getLp_first_name();
				String Validation_Message_LastName=usersresobj.getLp_last_name();
				String Validation_Message_ProviderIdentification=usersresobj.getLp_provider_identification();
				String Validation_Message_PhoneNumber=usersresobj.getLp_phone_number();
								
		}

			if(statuscode==Response_status_Code_401) {
				Assert.assertFalse(statuscode!=200);
				
			}
			if(statuscode==Response_status_Code_404) {
				
				Assert.assertFalse(statuscode!=200);
			}
						
			
			
			
	}

}

