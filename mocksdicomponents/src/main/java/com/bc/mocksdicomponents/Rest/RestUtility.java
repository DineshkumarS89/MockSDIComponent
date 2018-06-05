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

	
	
	String url = "http://localhost:8080/events";
	
	HttpClient httpClient = HttpClientBuilder.create().build();
	
	HttpPost httpPost = new HttpPost(url);
	
	HttpResponse httpResponse;
	
	Gson gson = new Gson();
	
	StringEntity request;
	
	public void sendEvents(EventsBO events) throws IOException {
		
		
		
		httpPost.setHeader("Content-Type","application/json");
		
		long eventID = ThreadLocalRandom.current().nextLong(11111111, 99999999);
		
		Double amount = Double.parseDouble(events.getTxnAmount());
		
		Double pricedAmount = 0.0;
		
		if(amount < 100)
			pricedAmount = amount - (amount * 0.02);
		else
			pricedAmount = amount - (amount * 0.05);
		
		events.setComponent("SDI");
		events.setEventId(eventID);
		
//		System.out.println(gson.toJson(events));
		
		request = new StringEntity(gson.toJson(events));
		httpPost.setEntity(request);
		httpResponse = httpClient.execute(httpPost);
		
		
		events.setComponent("treatment");
		events.setEventId(eventID);
		events.setTxnAmount(pricedAmount.toString());
		request = new StringEntity(gson.toJson(events));
		httpPost.setEntity(request);
		httpResponse = httpClient.execute(httpPost);
		
		events.setComponent("Movement");
		events.setEventId(eventID);
		request = new StringEntity(gson.toJson(events));
		httpPost.setEntity(request);
		httpResponse = httpClient.execute(httpPost);
		
		events.setComponent("Aggregate");
		events.setEventId(eventID);
		request = new StringEntity(gson.toJson(events));
		httpPost.setEntity(request);
		httpResponse = httpClient.execute(httpPost);
		
		events.setComponent("Settlement");
		events.setEventId(eventID);
		request = new StringEntity(gson.toJson(events));
		httpPost.setEntity(request);
		httpResponse = httpClient.execute(httpPost);
		
	}
	
}
