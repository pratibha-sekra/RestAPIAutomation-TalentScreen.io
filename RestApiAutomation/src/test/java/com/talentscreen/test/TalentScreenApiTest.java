package com.talentscreen.test;

import static org.testng.Assert.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.talentscreen.base.BaseApi;
import com.talentscreen.base.BaseApiTest;
import com.talentscreen.helper.RestResponse;

public class TalentScreenApiTest extends BaseApiTest {
	
	BaseApi api;
	RestResponse restResponse;
	@BeforeClass
	public void beforeClass(){
		api= new BaseApi(endPoint);
	}
	@Test
	public void getTest(){
	restResponse= api.get("/subjects");
	System.out.println("Get request status code :"+restResponse.getStatusCode());
	System.out.println("Get request status message :"+restResponse.getStatusMessage());
	assertEquals(restResponse.getStatusCode(),200);
	assertTrue(restResponse.getStatusMessage().contains("OK"));
	JSONArray jsonArray= new JSONArray(restResponse.getPayLoad());
	System.out.println(jsonArray.toString());
	System.out.println("id :"+ ((JSONObject) jsonArray.get(0)).get("id"));
	}
	@Test
	public void postTest(){
	restResponse= api.post("/subjects");
	System.out.println("post request status code :"+restResponse.getStatusCode());
	System.out.println("post request status message :"+restResponse.getStatusMessage());
	assertEquals(restResponse.getStatusCode(), 201);
	assertTrue(restResponse.getStatusMessage().contains("Created"));
	JSONObject jsonObject= new JSONObject(restResponse.getPayLoad());
	System.out.println(jsonObject.toString());
	System.out.println("id"+ jsonObject.getInt("id"));
	}
	@Test
	public void updateTest(){
	restResponse= api.update("/subjects","3");
	System.out.println("put request status code :"+restResponse.getStatusCode());
	System.out.println("put request status message :"+restResponse.getStatusMessage());
	assertEquals(restResponse.getStatusCode(), 200);
	assertTrue(restResponse.getStatusMessage().contains("OK"));
	JSONObject jsonObject= new JSONObject(restResponse.getPayLoad());
	System.out.println(jsonObject.toString());
	System.out.println("name"+ jsonObject.get("name"));
	}
	@Test
	public void deleteTest(){
	restResponse= api.delete("/subjects","3");
	System.out.println("delete request status code :"+restResponse.getStatusCode());
	System.out.println("delete request status message :"+restResponse.getStatusMessage());
	assertEquals(restResponse.getStatusCode(), 204);
	assertTrue(restResponse.getStatusMessage().contains("No Content"));
	}


}
