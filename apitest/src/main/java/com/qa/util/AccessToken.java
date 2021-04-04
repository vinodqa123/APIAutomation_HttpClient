package com.qa.util;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest.AuthenticationRequestBuilder;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest.TokenRequestBuilder;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthMessage;
import org.apache.oltu.oauth2.common.message.types.GrantType;

public class AccessToken {

	
	public static String post() throws OAuthSystemException, OAuthProblemException {
		RestAssured.baseURI="https://ccmqa.liquid-payments.com";
        
	    String ouathresponse=given().header("Content-Type","application/x-www-form-urlencoded")
	               .body("grant_type=password&client_id=31fadfb9fe574cdaf18c8cdd1c489140749e4c555cd5cff6c54b6947c0fabe69&client_secret=9a3b14be9088e4f2673c6e3a164818df&username=lpfusebillach3_support@liquid-payments.com&password=12LpTest@12")
	               .when().post("oauth")
	               .asString();
	    
//	    System.out.println("Here is the Response: "+ ouathresponse);
	    
	    JsonPath js=new JsonPath(ouathresponse);
	    String accesstoken=js.get("access_token");
	    System.out.println("Access Token: "+accesstoken);
		
	}

	
	
	
}
