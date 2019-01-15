package rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class RestClient {
    private static final Logger log = Logger.getLogger(RestClient.class.getName());
    private static final String url = "http://localhost:8090/bookShore";
    private final Gson gson = new Gson();

    public RestClient(){}

    public RestResponse executeQueryPost(Object object, String queryPost){
        final String query = url + queryPost;
        log.info("[Query post] " + query);

        //Execute the HTTP POST request
        HttpPost httpPost = new HttpPost(query);
        httpPost.addHeader("content-type", "application/json");
        StringEntity params;

        try{
            params = new StringEntity(gson.toJson(object));
            httpPost.setEntity(params);
            log.info(params.toString());
        } catch (UnsupportedEncodingException ex){
            Logger.getLogger(RestClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        try(CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(httpPost)){
            log.info("[Status Line] : " + response.getStatusLine());
            HttpEntity entity = response.getEntity();
            final String entityString = EntityUtils.toString(entity);
            log.info("[Entity]");
            try{RestResponse resje = gson.fromJson(entityString, RestResponse.class);} catch (IllegalStateException | JsonSyntaxException exception){System.out.println(entityString);}
            return gson.fromJson(entityString, RestResponse.class);

        } catch (IOException e){
            log.warning(e.toString());
        }
        return executeHttpUriRequest(httpPost);
    }

    private RestResponse executeHttpUriRequest(HttpUriRequest httpUriRequest) {

        // Execute the HttpUriRequest
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpUriRequest)) {
            System.out.println("[Status Line] : " + response.getStatusLine());
            HttpEntity entity = response.getEntity();
            final String entityString = EntityUtils.toString(entity);
            System.out.println("[Entity] : " + entityString);
            RestResponse restResponse = gson.fromJson(entityString, RestResponse.class);
            return restResponse;
        } catch (IOException e) {
            System.out.println("IOException : " + e.toString());
            RestResponse restResponse = new RestResponse();
            //restResponse.setSuccess(false);
            return restResponse;
        } catch (JsonSyntaxException e) {
            System.out.println("JsonSyntaxException : " + e.toString());
            RestResponse restResponse = new RestResponse();
            //restResponse.setSuccess(false);
            return restResponse;
        }
    }
    public RestResponse executeQueryGet(String queryGet) {

        // Build the query for the REST service
        final String query = url + queryGet;
        System.out.println("[Query Get] : " + query);

        // Execute the HTTP GET request
        HttpGet httpGet = new HttpGet(query);
        return executeHttpUriRequest(httpGet);
    }
}
