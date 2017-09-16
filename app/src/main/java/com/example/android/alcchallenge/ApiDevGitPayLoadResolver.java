package com.example.android.alcchallenge;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DrNice on 9/14/2017.
 */

public class ApiDevGitPayLoadResolver {
//
//
//    @SerializedName("total_count")
//    @Expose
//    private Integer totalCount;
//    @SerializedName("incomplete_results")
//    @Expose
//    private Boolean incompleteResults;
//    @SerializedName("items")
//    @Expose
//    private ArrayList<Dev_Git> items = null;
//
//    public Integer getTotalCount() {
//        return totalCount;
//    }
//
//    public void setTotalCount(Integer totalCount) {
//        this.totalCount = totalCount;
//    }
//
//    public Boolean getIncompleteResults() {
//        return incompleteResults;
//    }
//
//    public void setIncompleteResults(Boolean incompleteResults) {
//        this.incompleteResults = incompleteResults;
//    }
//
//    public ArrayList<Dev_Git> getItems() {
//        return items;
//    }
//
//    public void setItems(ArrayList<Dev_Git> items) {
//        this.items = items;
//    }
//
        /*
        * Holds all the properties from the body of the response */

    String total_counts;
    String incomplete_results;

    /* Holds the all the lists of the developer here in the */
    @SerializedName("items")
    @Expose
    ArrayList<Dev_Git> items;


    public String getTotal_counts() {
        return total_counts;
    }

    public void setTotal_counts(String total_counts) {
        this.total_counts = total_counts;
    }

    public String getIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(String incomplete_results) {
        this.incomplete_results = incomplete_results;
    }


    public List<Dev_Git> getItems() {
        return items;
    }

    public void setItems(ArrayList<Dev_Git> items) {
        this.items = items;
    }


}
