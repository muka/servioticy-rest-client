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
package es.servioticy.restclient;

/**
 * @author Álvaro Villalba Navarro <alvaro.villalba@bsc.es>
 * 
 */
public class RestResponse {
	private String response;
	private int httpCode;
	
	public RestResponse(String response, int httpCode){
		this.setResponse(response);
		this.setHttpCode(httpCode);
	}
	
	public int getHttpCode() {
		return httpCode;
	}
	private void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}
	private void setResponse(String response) {
		this.response = response;
	}
	public String getResponse() {
		return this.response;
	}

}
