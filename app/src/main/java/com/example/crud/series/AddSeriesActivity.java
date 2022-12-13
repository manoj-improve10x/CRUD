package com.example.crud.series;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSeriesActivity extends BaseAddEditSeriesActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Series");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.check) {
            String name = seriesName.getText().toString();
            String imageUrl = seriesImage.getText().toString();
                setAddSeries(name, imageUrl);
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void setAddSeries(String name, String imageUrl) {
        series = new Series();
        series.name = name;
        series.imageUrl = imageUrl;
        Call<Series> call = service.createSeries(series);
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
