/*******************************************************************************
 * Copyright 2014 Barcelona Supercomputing Center (BSC)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.servioticy.restclient;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.Future;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Álvaro Villalba Navarro <alvaro.villalba@bsc.es>
 * 
 */
public class RestClient implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private CloseableHttpAsyncClient httpClient;

	final static protected Logger logger = LoggerFactory.getLogger(RestClient.class);
	
	public final static int POST = 0;
	public final static int PUT = 1;
	public final static int GET = 2;

    public RestClient(){
        this.httpClient = HttpAsyncClients.createDefault();
        this.httpClient.start();
    }

    public void close() throws IOException {
        this.httpClient.close();
    }
	
	public FutureRestResponse restRequest(String url, String body, int method, Map<String, String> headers) throws RestClientException, RestClientErrorCodeException{
		HttpRequestBase httpMethod;
		StringEntity input;
		
		switch(method){
			case POST:
				try {
					input = new StringEntity(body);
				} catch (UnsupportedEncodingException e) {
					logger.error(e.getMessage());
					throw new RestClientException(e.getMessage());
				}
				input.setContentType("application/json");
				HttpPost httpPost = new HttpPost(url);
				httpPost.setEntity(input);
				httpMethod = httpPost;
				break;
			case PUT:
				try {
					input = new StringEntity(body);
				} catch (UnsupportedEncodingException e) {
					logger.error(e.getMessage());
					throw new RestClientException(e.getMessage());
				}
				input.setContentType("application/json");
				HttpPut httpPut = new HttpPut(url);
				httpPut.setEntity(input);
				httpMethod = httpPut;
				break;
			case GET:
				httpMethod = new HttpGet(url);
				break;
			default:
				return null;
		}
		
		if(headers != null){
			for(Map.Entry<String, String> header : headers.entrySet()){
				httpMethod.addHeader(header.getKey(), header.getValue());
			}
		}
		
		Future<HttpResponse> response;
		try {
			response = httpClient.execute(httpMethod, null);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RestClientException(e.getMessage());
		}
		
		// TODO Check the errors nicely
		FutureRestResponse rr = new FutureRestResponse(response);

		return rr;
		
	}

}
