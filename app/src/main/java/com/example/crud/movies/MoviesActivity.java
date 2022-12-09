package com.example.crud.movies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.crud.Constants;
import com.example.crud.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesActivity extends AppCompatActivity {

    private ArrayList<Movie> movies = new ArrayList<>();
    private RecyclerView moviesRv;
    private ProgressBar moviesProgressbar;
    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        getSupportActionBar().setTitle("Movies");
        Log.i("MoviesActivity", "onCreate called");
        setupMoviesRv();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchMovies();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movies_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.movie_add) {
            Intent intent = new Intent(this, AddEditMovieActivity.class);
            startActivity(intent);
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showProgressbar() {
        moviesProgressbar.setVisibility(View.VISIBLE);
    }

    private void hideProgressbar() {
        moviesProgressbar.setVisibility(View.GONE);
    }

    private void setupMoviesRv() {
        moviesProgressbar = findViewById(R.id.movies_progressbar);
        moviesRv = findViewById(R.id.movies_rv);
        moviesRv.setLayoutManager(new GridLayoutManager(this, 2));
        moviesAdapter = new MoviesAdapter();
        moviesAdapter.setData(movies);
        moviesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onDelete(String id) {
                deleteMovies(id);
            }

            @Override
            public void onEdit(Movie movie) {
                editMovie(movie);
            }
        });
        moviesRv.setAdapter(moviesAdapter);
    }

    private void fetchMovies() {
        Log.i("MoviesActivity", "successfully called movies from api ");
        showProgressbar();
        MoviesApi moviesApi = new MoviesApi();
        MoviesService moviesService = moviesApi.createMoviesService();
        Call<List<Movie>> call = moviesService.fetchMovies();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                List<Movie> movies = response.body();
                moviesAdapter.setData(movies);
                hideProgressbar();
                Toast.makeText(MoviesActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {

            }
        });
    }
    private void deleteMovies(String id) {
        MoviesApi moviesApi = new MoviesApi();
        MoviesService moviesService = moviesApi.createMoviesService();
        Call<Void> call = moviesService.deleteMovie(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                fetchMovies();
                Toast.makeText(MoviesActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
    private void editMovie(Movie movie) {
        Intent intent = new Intent(this, AddEditMovieActivity.class);
        intent.putExtra(Constants.KEY_MOVIE, movie);
        startActivity(intent);
    }
}