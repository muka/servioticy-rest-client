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

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Álvaro Villalba Navarro <alvaro.villalba@bsc.es>
 *
 */
public class FutureRestResponse {
    private Future<HttpResponse> httpResponse;
    final static protected Logger logger = LoggerFactory.getLogger(FutureRestResponse.class);

    public FutureRestResponse(Future<HttpResponse> httpResponse){
        this.setHttpResponse(httpResponse);
    }
    private void setHttpResponse(Future<HttpResponse> httpResponse){
        this.httpResponse = httpResponse;
    }
    public boolean cancel(boolean mayInterruptIfRunning){
        return this.httpResponse.isCancelled();
    }
    public boolean isCancelled(){
        return this.httpResponse.isCancelled();
    }
    public boolean isDone(){
        return this.httpResponse.isDone();
    }
    private RestResponse getRestResponse(HttpResponse response) throws RestClientException, RestClientErrorCodeException {
        int statusCode = response.getStatusLine().getStatusCode();
        RestResponse rr;
        try {
            if(response.getEntity() == null){
                rr = new RestResponse(null, statusCode);
            }else{
                StringWriter writer = new StringWriter();
                IOUtils.copy(response.getEntity().getContent(), writer, "utf-8");
                rr = new RestResponse(writer.toString(), statusCode);
                response.getEntity().getContent().close();
            }
        }catch (Exception e) {
            logger.error(e.getMessage());
            throw new RestClientException(e.getMessage());
        }
        if(statusCode < 200 || statusCode >= 300){
            logger.warn("'" + rr.getResponse() + "'");
            throw new RestClientErrorCodeException(rr.getResponse(), rr);
        }
        return rr;
    }
    public RestResponse get() throws InterruptedException, ExecutionException, RestClientException, RestClientErrorCodeException {
        HttpResponse response = this.httpResponse.get();
        return getRestResponse(response);
    }
    public RestResponse get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException, RestClientException, RestClientErrorCodeException {
        HttpResponse response = this.httpResponse.get(timeout, unit);
        return getRestResponse(response);
    }
}
