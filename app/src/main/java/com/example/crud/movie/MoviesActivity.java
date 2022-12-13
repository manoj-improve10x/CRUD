package com.example.crud.movie;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.api.CrudApi;
import com.example.crud.api.CrudService;
import com.example.crud.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesActivity extends BaseActivity {

    private ArrayList<Movie> movies = new ArrayList<>();
    private RecyclerView moviesRv;
    private ProgressBar moviesProgressbar;
    private MoviesAdapter moviesAdapter;
    private CrudService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        getSupportActionBar().setTitle("Movies");
        log("onCreate");
        setupApiService();
        setupMoviesRv();
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("onResume");
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
            Intent intent = new Intent(this, AddMovieActivity.class);
            startActivity(intent);
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void setupApiService() {
        CrudApi api = new CrudApi();
        service = api.createCrudService();
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
                deleteMovie(id);
            }

            @Override
            public void onEdit(Movie movie) {
                editMovie(movie);
            }
        });
        moviesRv.setAdapter(moviesAdapter);
    }

    private void fetchMovies() {
        showProgressbar();
        Call<List<Movie>> call = service.fetchMovies();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                List<Movie> movies = response.body();
                moviesAdapter.setData(movies);
                hideProgressbar();
                showToast("Successfully loaded the movies");
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                showToast("Failed to load movies");
            }
        });
    }
    private void deleteMovie(String id) {
        Call<Void> call = service.deleteMovie(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                fetchMovies();
                showToast("Successfully deleted the movie");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to delete movie");
            }
        });
    }
    private void editMovie(Movie movie) {
        Intent intent = new Intent(this, EditMovieActivity.class);
        intent.putExtra(Constants.KEY_MOVIE, movie);
        startActivity(intent);
    }
}