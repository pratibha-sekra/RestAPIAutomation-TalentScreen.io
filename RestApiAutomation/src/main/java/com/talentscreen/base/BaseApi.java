package com.talentscreen.base;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.talentscreen.helper.RestResponse;

public class BaseApi {

	private String url;
	RestResponse restResponse;
	HttpClient httpClient;

public BaseApi(String url){
	this.url=url;
	httpClient= HttpClientBuilder.create().build();
	setAuthentication();
}

private void setAuthentication() {
}
public RestResponse get(String resource){
	HttpGet get= new HttpGet(url+resource+"?authentication=false");
	restResponse= new RestResponse();
	try {
		HttpResponse response= httpClient.execute(get);
		restResponse.setStatusCode(response.getStatusLine().getStatusCode());
		restResponse.setStatusMessage(response.getStatusLine().toString());
		restResponse.setPayLoad(IOUtils.toString(response.getEntity().getContent()));
		
	} catch (ClientProtocolException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	return restResponse;
}
public RestResponse post(String resource){
	HttpPost post= new HttpPost(url+resource+"?authentication=false");
	restResponse= new RestResponse();
	try {
		post.setHeader("content-type","application/json");
		HttpEntity entity= new StringEntity(createRequestPayloadForPost());
		post.setEntity(entity);
		HttpResponse response= httpClient.execute(post);
		restResponse.setStatusCode(response.getStatusLine().getStatusCode());
		restResponse.setStatusMessage(response.getStatusLine().toString());
		restResponse.setPayLoad(IOUtils.toString(response.getEntity().getContent()));
		
	} catch (ClientProtocolException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	return restResponse;
}
public String createRequestPayloadForPost() {
	String reqPayLoad=  "{\"categoryid\": 1,\"name\": \"C1 neew\",\"icon_class\": \"ts-csharp\"}";
	return reqPayLoad;
}	
public RestResponse update(String resource,String requestParam){
	HttpPut put= new HttpPut(url+resource+"/"+requestParam+"?authentication=false");
	restResponse= new RestResponse();
	try {
		put.setHeader("content-type","application/json");
		HttpEntity entity= new StringEntity(createRequestPayloadForput());
		put.setEntity(entity);
		HttpResponse response= httpClient.execute(put);
		restResponse.setStatusCode(response.getStatusLine().getStatusCode());
		restResponse.setStatusMessage(response.getStatusLine().toString());
		restResponse.setPayLoad(IOUtils.toString(response.getEntity().getContent()));
		
	} catch (ClientProtocolException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	return restResponse;
}

private String createRequestPayloadForput() {
	String reqPayLoad= "{\"categoryid\": 1,\"name\": \"Python1 new\",\"icon_class\": \"ts-pythonprogramming\",\"template\": \"kk\",\"codemirror_theme\": null}";
	return reqPayLoad;
}
public RestResponse delete (String resource,String requestParam){
	HttpDelete delete= new HttpDelete(url+resource+"/"+requestParam+"?authentication=false");
	restResponse= new RestResponse();
	try {
		HttpResponse response= httpClient.execute(delete);
		restResponse.setStatusCode(response.getStatusLine().getStatusCode());
		restResponse.setStatusMessage(response.getStatusLine().toString());		
	} catch (ClientProtocolException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	return restResponse;
}	
	
	
}
