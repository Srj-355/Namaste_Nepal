package com.example.namastenepal.movie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieContainer {
    @SerializedName("page")
    @Expose
    int page;
    @SerializedName("results")
    @Expose
    List<MovieResults> resultsList;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<MovieResults> getResultsList() {
        return resultsList;
    }

    public void setResultsList(List<MovieResults> resultsList) {
        this.resultsList = resultsList;
    }
}
