package com.example.crud.movie;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.series.Series;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditMovieActivity extends BaseAddEditMovieActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Edit Movie");
        if (getIntent().hasExtra(Constants.KEY_MOVIE)) {
            movie = (Movie) getIntent().getSerializableExtra(Constants.KEY_MOVIE);
            showData();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.save) {
            String movieName = binding.movieNameTxt.getText().toString();
            String movieId = binding.movieIdTxt.getText().toString();
            Series series = (Series) binding.movieSeriesSp.getSelectedItem();
            String seriesId = series.seriesId;
            String imagesUrl = binding.imageUrlTxt.getText().toString();
            String description = binding.movieDescriptionTxt.getText().toString();
            updateMovie(movie.id, movieName, movieId, seriesId, imagesUrl, description);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void updateMovie(String id, String name, String movieId, String seriesId, String imagesUrl, String description) {
        movie = new Movie();
        movie.movieName = name;
        movie.movieId = movieId;
        movie.movieImageUrl = imagesUrl;
        movie.description = description;
        movie.seriesId = seriesId;

        Call<Void> call = crudService.editMovie(id, movie);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("successfully updated the movie");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to update movie");
            }
        });
    }
}