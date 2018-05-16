package com.bc.mocksdicomponents.Rest;


import com.bc.mocksdicomponents.BO.EventsBO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;

import java.io.IOException;


public class RestUtility {

	String url = "http://localhost:8080/events";
	
	HttpClient httpClient = HttpClientBuilder.create().build();
	
	HttpPost httpPost = new HttpPost(url);
	
	HttpResponse httpResponse;
	
	Gson gson = new Gson();
	
	StringEntity request;
	
	public void sendEvents(EventsBO events) throws IOException {
		
		httpPost.setHeader("Content-Type","application/json");
		
		events.setComponent("treatment");
		request = new StringEntity(gson.toJson(events));
		httpPost.setEntity(request);
		httpResponse = httpClient.execute(httpPost);
		
		events.setComponent("Movement");
		request = new StringEntity(gson.toJson(events));
		httpPost.setEntity(request);
		httpResponse = httpClient.execute(httpPost);
		
		events.setComponent("Aggregate");
		request = new StringEntity(gson.toJson(events));
		httpPost.setEntity(request);
		httpResponse = httpClient.execute(httpPost);
		
		events.setComponent("Settlement");
		request = new StringEntity(gson.toJson(events));
		httpPost.setEntity(request);
		httpResponse = httpClient.execute(httpPost);
		
	}
	
}
