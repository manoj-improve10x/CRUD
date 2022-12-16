package com.example.crud.movie;

import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.crud.R;
import com.example.crud.base.BaseActivity;
import com.example.crud.series.Series;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseAddEditMovieActivity extends BaseActivity {

    protected CustomSeriesItemsAdapter customSeriesItemsAdapter;
    protected ArrayList<Series> seriesItems = new ArrayList<>();
    protected Spinner seriesSp;
    protected Movie movie;
    protected EditText movieNameTxt;
    protected EditText movieIdTxt;
    protected EditText imageUrlTxt;
    protected EditText movieDescriptionTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_movie);
        log("on create");
        initViews();
        setupSeriesItemsSp();
        fetchSeriesList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_movie_menu, menu);
        return true;
    }

    protected void showData() {
        movieNameTxt.setText(movie.movieName);
        movieIdTxt.setText(movie.movieId);
        imageUrlTxt.setText(movie.movieImageUrl);
        movieDescriptionTxt.setText(movie.description);
        for (int i = 0; i < customSeriesItemsAdapter.getCount(); i++) {
            Series series = customSeriesItemsAdapter.getItem(i);
            if (movie.seriesId.equals(series.seriesId)) {
                seriesSp.setSelection(i);
            }
        }
    }

    private void setupSeriesItemsSp() {
        customSeriesItemsAdapter = new CustomSeriesItemsAdapter(this, android.R.layout.simple_list_item_1, seriesItems);
        seriesSp.setAdapter(customSeriesItemsAdapter);
    }

    private void initViews() {
        seriesSp = findViewById(R.id.movie_series_sp);
        movieNameTxt = findViewById(R.id.movie_name_txt);
        movieIdTxt = findViewById(R.id.movie_id_txt);
        imageUrlTxt = findViewById(R.id.image_url_txt);
        movieDescriptionTxt = findViewById(R.id.movie_description_txt);
    }

    //ToDo: change method name fetchSeriesItems
    private void fetchSeriesList() {
        Call<List<Series>> call = crudService.fetchSeriesItems();
        call.enqueue(new Callback<List<Series>>() {
            @Override
            public void onResponse(Call<List<Series>> call, Response<List<Series>> response) {
                List<Series> series = response.body();
                customSeriesItemsAdapter.addAll(series);
                if (movie != null) {
                    showData();
                }
            }

            @Override
            public void onFailure(Call<List<Series>> call, Throwable t) {
            }
        });
    }
}