package com.example.crud.movie;

import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.crud.R;
import com.example.crud.base.BaseActivity;
import com.example.crud.databinding.ActivityAddEditMovieBinding;
import com.example.crud.series.Series;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseAddEditMovieActivity extends BaseActivity {

    protected ActivityAddEditMovieBinding binding;
    protected CustomSeriesItemsAdapter customSeriesItemsAdapter;
    protected ArrayList<Series> seriesItems = new ArrayList<>();
    protected Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditMovieBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        log("on create");
        setupSeriesItemsSp();
        fetchSeriesItems();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_movie_menu, menu);
        return true;
    }

    protected void showData() {
        binding.movieNameTxt.setText(movie.movieName);
        binding.movieIdTxt.setText(movie.movieId);
        binding.imageUrlTxt.setText(movie.movieImageUrl);
        binding.movieDescriptionTxt.setText(movie.description);
        for (int i = 0; i < customSeriesItemsAdapter.getCount(); i++) {
            Series series = customSeriesItemsAdapter.getItem(i);
            if (movie.seriesId.equals(series.seriesId)) {
                binding.movieSeriesSp.setSelection(i);
            }
        }
    }

    private void setupSeriesItemsSp() {
        customSeriesItemsAdapter = new CustomSeriesItemsAdapter(this, android.R.layout.simple_list_item_1, seriesItems);
        binding.movieSeriesSp.setAdapter(customSeriesItemsAdapter);
    }

    private void fetchSeriesItems() {
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