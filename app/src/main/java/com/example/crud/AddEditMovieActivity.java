package com.example.crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.crud.series.Series;
import com.example.crud.series.SeriesListApi;
import com.example.crud.series.SeriesListService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditMovieActivity extends AppCompatActivity {

    public CustomSeriesAdapter customSeriesAdapter;
    private ArrayList<Series> seriesList = new ArrayList<>();
    private Spinner seriesSp;
    private Movie movie;
    private EditText movieNameTxt;
    private EditText movieIdTxt;
    private EditText imageUrlTxt;
    private EditText movieDescriptionTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_movie);
        setupSeriesListSp();
        findIds();
        fetchSeriesList();
        if (getIntent().hasExtra(Constants.KEY_MOVIE)) {
            getSupportActionBar().setTitle("Edit Title");
            movie = (Movie) getIntent().getSerializableExtra(Constants.KEY_MOVIE);
            showData();
        }else {
            getSupportActionBar().setTitle("Add Title");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_movie_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.check_movie){
            String name = movieNameTxt.getText().toString();
            String movieId = movieIdTxt.getText().toString();
            Series series = (Series) seriesSp.getSelectedItem();
            String seriesId = series.seriesId;
            String imagesUrl = imageUrlTxt.getText().toString();
            String description = movieDescriptionTxt.getText().toString();
            if(movie == null){
                addMovie(name, movieId, seriesId, imagesUrl, description);
            }else {
                editMovie(movie.id, name, movieId, seriesId, imagesUrl, description);
            }
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showData() {
        movieNameTxt.setText(movie.movieName);
        movieIdTxt.setText(movie.movieId);
        imageUrlTxt.setText(movie.movieImageUrl);
        movieDescriptionTxt.setText(movie.description);
        for (int i= 0; i < customSeriesAdapter.getCount(); i++) {
            Series series = customSeriesAdapter.getItem(i);
            if(movie.seriesId.equals(series.seriesId)){
                seriesSp.setSelection(i);
            }
        }
    }

    public void setupSeriesListSp() {
        seriesSp = findViewById(R.id.movie_series_sp);
        customSeriesAdapter = new CustomSeriesAdapter(this, android.R.layout.simple_list_item_1, seriesList);
        seriesSp.setAdapter(customSeriesAdapter);
    }

    private void findIds() {
        seriesSp = findViewById(R.id.movie_series_sp);
        movieNameTxt = findViewById(R.id.movie_name_txt);
        movieIdTxt = findViewById(R.id.movie_id_txt);
        imageUrlTxt = findViewById(R.id.image_url_txt);
        movieDescriptionTxt = findViewById(R.id.movie_description_txt);
    }

    private void fetchSeriesList() {
        SeriesListApi seriesApi = new SeriesListApi();
        SeriesListService seriesListService = seriesApi.createSeriesListService();
        Call<List<Series>> call = seriesListService.fetchSeries();
        call.enqueue(new Callback<List<Series>>() {
            @Override
            public void onResponse(Call<List<Series>> call, Response<List<Series>> response) {
                Toast.makeText(AddEditMovieActivity.this, "success", Toast.LENGTH_SHORT).show();
                List<Series> series = response.body();
                customSeriesAdapter.addAll(series);
                if (movie != null) {
                    showData();
                }

            }

            @Override
            public void onFailure(Call<List<Series>> call, Throwable t) {

            }
        });
    }

    private void addMovie(String name, String movieId, String seriesId, String imagesUrl, String description) {
        movie = new Movie();

        movie.movieName = name;
        movie.movieImageUrl = imagesUrl;
        movie.movieId = movieId;
        movie.description = description;
        movie.seriesId = seriesId;

        MoviesApi moviesApi = new MoviesApi();
        MoviesService moviesService = moviesApi.createMoviesService();
        Call<Movie> call = moviesService.createMovie(movie);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Toast.makeText(AddEditMovieActivity.this, "success", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
    }
    private void editMovie(String id, String movieId, String seriesId, String name, String imagesUrl, String description) {
        movie = new Movie();

        movie.movieName = name;
        movie.movieId = movieId;
        movie.movieImageUrl = imagesUrl;
        movie.description = description;
        movie.seriesId = seriesId;

        MoviesApi moviesApi = new MoviesApi();
        MoviesService movieService = moviesApi.createMoviesService();
        Call<Void> call = movieService.editMovie(id, movie);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(AddEditMovieActivity.this, "Success", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}