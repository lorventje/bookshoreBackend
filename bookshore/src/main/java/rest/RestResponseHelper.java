package rest;

import com.google.gson.Gson;

public class RestResponseHelper {
    private static final Gson gson = new Gson();

    private RestResponseHelper() {}

    public static String getSuccessResponse(boolean success){
        RestResponse restResponse = new RestResponse();
        String output = gson.toJson(restResponse);
        System.out.println("[Server Response " + output);
        return output;
    }

    public static String getErrorResponseString()
    {
        RestResponse response = new RestResponse();
        String output = gson.toJson(response);
        System.out.println("[Server response] " + output);
        return output;
    }
}
