package com.example.crud.series;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.example.crud.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesListActivity extends BaseActivity {

    private ArrayList<Series> seriesItems = new ArrayList<>();
    private RecyclerView seriesItemsRv;
    //Todo: change SeriesItemsAdapter
    private SeriesListAdapter seriesListAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);
        getSupportActionBar().setTitle("Series");
        log("onCreate");
        initViews();
        setupSeriesItemsRv();
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("onResume");
        fetchSeries();
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }


    private void fetchSeries() {
        showProgressBar();
        Call<List<Series>> call = crudService.fetchSeriesItems();
        call.enqueue(new Callback<List<Series>>() {
            @Override
            public void onResponse(Call<List<Series>> call, Response<List<Series>> response) {
                hideProgressBar();
                List<Series> series = response.body();
                seriesListAdapter.setData(series);
            }

            @Override
            public void onFailure(Call<List<Series>> call, Throwable t) {
                hideProgressBar();
                showToast("Failed to load series");
            }
        });
    }

    private void initViews() {
        progressBar = findViewById(R.id.progress_bar);
        seriesItemsRv = findViewById(R.id.series_items_rv);
    }

    private void setupSeriesItemsRv() {
        seriesItemsRv.setLayoutManager(new LinearLayoutManager(this));
        seriesListAdapter = new SeriesListAdapter();
        seriesListAdapter.setData(seriesItems);
        seriesListAdapter.setOnItemActionListener(new OnItemActionListener() {
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
        seriesItemsRv.setAdapter(seriesListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.series_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Intent intent = new Intent(this, AddSeriesActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void deleteSeries(String id) {
        showProgressBar();
        Call<Void> call = crudService.deleteSeriesItem(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                hideProgressBar();
                showToast("Successfully deleted series");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                hideProgressBar();
                showToast("Failed to delete series");
            }
        });
    }

    private void editSeries(Series series) {
        Intent intent = new Intent(this, EditSeriesActivity.class);
        intent.putExtra(Constants.KEY_SERIES, series);
        startActivity(intent);
    }
}