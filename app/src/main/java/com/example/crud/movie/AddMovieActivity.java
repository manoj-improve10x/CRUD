package com.example.crud.movie;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.R;
import com.example.crud.series.Series;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMovieActivity extends BaseAddEditMovieActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Movie");
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
            addMovie(movieName, movieId, seriesId, imagesUrl, description);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void addMovie(String name, String movieId, String seriesId, String imagesUrl, String description) {
        movie = new Movie();
        movie.movieName = name;
        movie.movieImageUrl = imagesUrl;
        movie.movieId = movieId;
        movie.description = description;
        movie.seriesId = seriesId;

        Call<Movie> call = crudService.createMovie(movie);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                showToast("Successfully added the movie");
                finish();
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                showToast("Failed to add movie");
            }
        });
    }
}
