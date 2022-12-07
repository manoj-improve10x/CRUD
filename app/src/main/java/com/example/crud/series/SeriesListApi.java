package com.example.crud.series;

import com.example.crud.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SeriesListApi {

    public SeriesListService createSeriesListService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SeriesListService seriesListService = retrofit.create(SeriesListService.class);
        return seriesListService;
    }
}
