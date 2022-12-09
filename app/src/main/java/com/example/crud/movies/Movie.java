package com.example.crud.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {

    @SerializedName("_id")
    public String id;
    public String movieName;
    public String movieImageUrl;
    public String seriesId;
    public String description;
    public String movieId;

}
