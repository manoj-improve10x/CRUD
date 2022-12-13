package com.example.crud.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {

    @SerializedName("_id")
    public String id;
    @SerializedName("name")
    public String movieName;
    @SerializedName("imageUrl")
    public String movieImageUrl;
    public String seriesId;
    public String description;
    public String movieId;

}
