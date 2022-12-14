package com.example.crud.series;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSeriesItemActivity extends BaseAddEditSeriesItemActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Series");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.save) {
            String id = binding.seriesIdTxt.getText().toString();
            String name = binding.seriesNameTxt.getText().toString();
            String imageUrl = binding.seriesImage.getText().toString();
            AddSeriesItem(name, id, imageUrl);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void AddSeriesItem(String name, String id, String imageUrl) {
        Series series = new Series();
        series.seriesId = id;
        series.name = name;
        series.imageUrl = imageUrl;
        Call<Series> call = crudService.createSeriesItem(series);
        call.enqueue(new Callback<Series>() {
            @Override
            public void onResponse(Call<Series> call, Response<Series> response) {
                showToast("successfully added the series");
                finish();
            }

            @Override
            public void onFailure(Call<Series> call, Throwable t) {
                showToast("failed to add series");
            }
        });
    }
}
