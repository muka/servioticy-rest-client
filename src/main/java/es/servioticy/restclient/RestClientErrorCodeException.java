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
 ******************************************************************************/ package es.servioticy.restclient;

/**
 * @author Álvaro Villalba Navarro <alvaro.villalba@bsc.es>
 * 
 */
public class RestClientErrorCodeException extends Exception {
	private RestResponse restResponse;
	
	public RestClientErrorCodeException() {
		super();
	}
	
	public RestClientErrorCodeException(RestResponse restResponse) {
		super();
		this.setRestResponse(restResponse);
	}
		
	public RestClientErrorCodeException(String message) {
		super(message);
	}
	public RestClientErrorCodeException(String message, RestResponse restResponse) {
		super(message);
		this.setRestResponse(restResponse);
	}

	public RestClientErrorCodeException(Throwable cause) {
		super(cause);
	}

	public RestClientErrorCodeException(String message, Throwable cause) {
		super(message, cause);
	}

	public RestClientErrorCodeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RestResponse getRestResponse() {
		return restResponse;
	}

	private void setRestResponse(RestResponse restResponse) {
		this.restResponse = restResponse;
	}
	
	
}

