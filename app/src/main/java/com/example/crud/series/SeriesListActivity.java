package com.example.crud.series;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.example.crud.api.CrudApi;
import com.example.crud.api.CrudService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesListActivity extends AppCompatActivity {

    private CrudService service;
    private ArrayList<Series> series = new ArrayList<>();
    private RecyclerView seriesRv;
    private SeriesAdapter seriesAdapter;
    private ProgressBar seriesProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);
        getSupportActionBar().setTitle("Series");
        log("onCreate");
        setupApiService();
        setupSeriesRv();
    }

    private void setupApiService() {
        CrudApi api = new CrudApi();
        service = api.createCrudService();
    }

    @Override
    protected void onResume() {
        super.onResume();
       log("onResume");
        fetchSeries();
    }

    private void log(String message) {
        Log.i("SeriesListActivity", message);
    }

    private void hideProgressbar() {
        seriesProgressbar.setVisibility(View.GONE);
    }

    private void showProgressbar() {
        seriesProgressbar.setVisibility(View.VISIBLE);
    }

    private void fetchSeries() {
        showProgressbar();

        Call<List<Series>> call = service.fetchSeries();
        call.enqueue(new Callback<List<Series>>() {
            @Override
            public void onResponse(Call<List<Series>> call, Response<List<Series>> response) {
                List<Series> series = response.body();
                seriesAdapter.setData(series);
                hideProgressbar();
                Toast.makeText(SeriesListActivity.this, "Success", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<Series>> call, Throwable t) {
                Toast.makeText(SeriesListActivity.this, "failure", Toast.LENGTH_SHORT).show();
                hideProgressbar();
            }
        });
    }


    private void setupSeriesRv() {
        seriesProgressbar = findViewById(R.id.series_progressbar);
        seriesRv = findViewById(R.id.series_rv);
        seriesRv.setLayoutManager(new LinearLayoutManager(this));
        seriesAdapter = new SeriesAdapter();
        seriesAdapter.setData(series);
        seriesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onEdit(Series series) {
                editSeries(series);

            }

            @Override
            public void onDelete(String id) {
                deleteSeries(id);
                fetchSeries();
            }
        });
        seriesRv.setAdapter(seriesAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.series_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.series_add) {
            Intent intent = new Intent(this, AddEditSeriesActivity.class);
            startActivity(intent);
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }
    private void deleteSeries(String id) {
        Call<Void> call = service.deleteSeries(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
    private void editSeries(Series series) {
        Intent intent = new Intent(this, AddEditSeriesActivity.class);
        intent.putExtra(Constants.KEY_SERIES, series);
        startActivity(intent);
    }
}