package com.example.android.alcchallenge;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by DrNice on 9/14/2017.
 */

public class ApiGetSetter extends Request<ApiDevGitPayLoadResolver> {


    private static int method;
    private static String url;
    private static Map<String, String> headers;
    private static Response.Listener listener;
    private static Response.ErrorListener errorListener;
    private static Class<ApiDevGitPayLoadResolver> devs;
    private static Gson gson = new Gson();


    //initialize constructor
    public ApiGetSetter(int _method,
                        String _url,
                        Class<ApiDevGitPayLoadResolver> _devs,
                        Map<String, String> _headers,
                        Response.Listener _listener,
                        Response.ErrorListener _errorListener) {

        super(_method, _url, _errorListener);
        method = _method;
        devs = _devs;
        url = _url;
        headers = _headers;
        listener = _listener;
        errorListener = _errorListener;
    }


    @Override
    protected Response<ApiDevGitPayLoadResolver> parseNetworkResponse(NetworkResponse networkResponse) {
     /*
      *  Run the network in the a try catch statement
      *  so that our doesn't break in case there is an issue
      * */

        try {
            /*
             * run network request
             */
            String json = new String(
                    networkResponse.data,
                    HttpHeaderParser.parseCharset(networkResponse.headers));
            /*
             * Convert the data from json to java using Gson library
             * the return model on sucess
             */
            return Response.success(
                    gson.fromJson(json, devs),
                    HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }


    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    /*
     * Here volley calls you back on the main thread (The thread on which your app is running on)
     * with the object you returned in parseNetworkResponse()
     * */

    @Override
    protected void deliverResponse(ApiDevGitPayLoadResolver _devs) {
        listener.onResponse(_devs);
    }


}
