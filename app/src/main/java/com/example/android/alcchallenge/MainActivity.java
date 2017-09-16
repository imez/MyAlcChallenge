package com.example.android.alcchallenge;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private static String URL = "https://api.github.com/search/users?q=location:lagos+language:java";
    private static Gson gson = new Gson();
    private static ApiDevGitPayLoadResolver apiDevGitPayLoadResolver;
    private static ArrayList<Dev_Git> devsTemp = new ArrayList<>();
    private static ProgressDialog dialog;
    ListView listView;
    JSONArray array;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        * This is just a simple progress bar that loads when Volley is pulling
        * data from the url which is: https://api.github.com/search/users?q=location:lagos+language:java
        * from github servers.
        * */

        listView = (ListView) findViewById(R.id.developersListView);
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Loading");// this is the message you see when the progress bar is visible
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.show();


        /*
         *create new requestQueue on Volley
         *Allows you to queue request on volley
         *The RequestQueue manages worker threads for running
         *the network operations, reading from and writing to
         *the cache, and parsing responses.
         * */
        RequestQueue requestQueue = Volley.newRequestQueue(this);


        //This is where the request is initialized to get the data from the network.
        ApiGetSetter apiRequest = new ApiGetSetter(
                Request.Method.GET,
                URL,
                ApiDevGitPayLoadResolver.class,
                null,
                /*
                * This is a listener that listens and retrieves the data when its ready.
                *
                * */
                new Response.Listener<ApiDevGitPayLoadResolver>() {
                    @Override
                    public void onResponse( ApiDevGitPayLoadResolver _payLoad) {
                       //clear the temporary array to avoid leaks
                        devsTemp.clear();
                        //get data from payload
                        apiDevGitPayLoadResolver = _payLoad;

                        //add new developers from the payload to a temporary arrayList
                        for ( Dev_Git d : apiDevGitPayLoadResolver.getItems()) {
                            devsTemp.add(d);
                        }

                        // create instance of the adapter
                        final  ListViewAdapter listViewAdapter = new  ListViewAdapter(MainActivity.this, R.layout.developer_card, devsTemp);

                        //bind adapter to listview
                        listView.setAdapter(listViewAdapter);

                        //notify adapter about new data
                        listViewAdapter.notifyDataSetChanged();

                        //hide progress bar when data has finished loading
                        dialog.dismiss();


                    }
                },


                //Listen for error
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }


        );

        //add request to the volley queue
        requestQueue.add(apiRequest);

    }



}
