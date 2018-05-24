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

import java.util.concurrent.ThreadLocalRandom;

public class RestUtility {

	ThreadLocalRandom random;
	
	String url = "http://localhost:8080/events";
	
	HttpClient httpClient = HttpClientBuilder.create().build();
	
	HttpPost httpPost = new HttpPost(url);
	
	HttpResponse httpResponse;
	
	Gson gson = new Gson();
	
	StringEntity request;
	
	public void sendEvents(EventsBO events) throws IOException {
		
		httpPost.setHeader("Content-Type","application/json");
		
		events.setComponent("treatment");
		events.setEventId(random.nextLong(11111111, 99999999));
		request = new StringEntity(gson.toJson(events));
		httpPost.setEntity(request);
		httpResponse = httpClient.execute(httpPost);
		
		events.setComponent("Movement");
		events.setEventId(random.nextLong(11111111, 99999999));
		request = new StringEntity(gson.toJson(events));
		httpPost.setEntity(request);
		httpResponse = httpClient.execute(httpPost);
		
		events.setComponent("Aggregate");
		events.setEventId(random.nextLong(11111111, 99999999));
		request = new StringEntity(gson.toJson(events));
		httpPost.setEntity(request);
		httpResponse = httpClient.execute(httpPost);
		
		events.setComponent("Settlement");
		events.setEventId(random.nextLong(11111111, 99999999));
		request = new StringEntity(gson.toJson(events));
		httpPost.setEntity(request);
		httpResponse = httpClient.execute(httpPost);
		
	}
	
}
