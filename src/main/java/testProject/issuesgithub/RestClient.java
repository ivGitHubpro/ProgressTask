package testProject.issuesgithub;


/**
 * Rest client for backend calls
 *
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class RestClient 
{
	private static final String CONTENT_TYPE_HEADER = "content-type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER = "Bearer ";
    private static final String ACCEPT = "Accept";
    private static final String ACCEPT_VALUE = "application/vnd.github.VERSION.full+json";
    private static final int STATUS_SUBSTRING_INDEX = 9;

   private List<String> executeHttpRequest(HttpRequestBase request) throws IOException {
	   
	        List<String> responseDetails = new ArrayList<String>();
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpResponse response = httpClient.execute(request);	   		
            String responseStatus = response.getStatusLine().toString().substring(STATUS_SUBSTRING_INDEX);
            String jsonResponse = EntityUtils.toString(response.getEntity(), "UTF-8");
            responseDetails.add(responseStatus);
            responseDetails.add(jsonResponse);
            return responseDetails;
        
    }

    private void setHeaders(String token, HttpRequestBase request) {
        if (token != null) {
            request.addHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE_JSON);
            request.addHeader(AUTHORIZATION_HEADER, BEARER + token);
            request.addHeader(ACCEPT, ACCEPT_VALUE);
        }
    }

    public List<String> sendPostRequest(String url, String body, String token, String type) throws IOException {
        HttpPost request = new HttpPost(url);


            StringEntity params = new StringEntity(body);

            setHeaders(token, request);

            if (type != null) {
                params.setContentType(type);
            }

            request.setEntity(params);


        return executeHttpRequest(request);
    }

    public List<String> sendPostRequest(String url, String body, String token) throws IOException {
        return sendPostRequest(url, body, token, null);
    }

    public List<String> sendGetRequest(String url, String token) throws IOException {
        HttpGet request = new HttpGet(url);

        setHeaders(token, request);

        return executeHttpRequest(request);
    }

    public boolean sendDeleteRequest(String url, String token)  throws IOException {

           CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpDelete request = new HttpDelete(url);

            setHeaders(token, request);

            HttpResponse response = httpClient.execute(request);

            int result = response.getStatusLine().getStatusCode();
            if (result == 200 || result == 204) {
                return true;
            }

   
        return false;
    }
    
    public boolean sendPutRequest(String url, String token)  throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
         HttpPut request = new HttpPut(url);

         setHeaders(token, request);

         HttpResponse response = httpClient.execute(request);

         int result = response.getStatusLine().getStatusCode();
         if (result == 200 || result == 204) {
             return true;
         }


     return false;
 }
    
    public List<String> sendPatchRequest(String url, String body, String token) throws IOException {
            HttpPatch request = new HttpPatch(url);
            StringEntity params = new StringEntity(body);
            setHeaders(token, request);
            request.setEntity(params);


        return executeHttpRequest(request);
    }

}

