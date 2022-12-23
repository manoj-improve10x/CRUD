package com.example.crud.series;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.Constants;
import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditSeriesItemActivity extends BaseAddEditSeriesItemActivity {

    private Series series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Edit Series");
        if (getIntent().hasExtra(Constants.KEY_SERIES)) {
            series = (Series) getIntent().getSerializableExtra(Constants.KEY_SERIES);
            showData();
        }
    }

    private void showData() {
        binding.seriesIdTxt.setText(series.seriesId);
        binding.seriesNameTxt.setText(series.name);
        binding.seriesImage.setText(series.imageUrl);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.save) {
            String name = binding.seriesNameTxt.getText().toString();
            String seriesId = binding.seriesIdTxt.getText().toString();
            String imageUrl = binding.seriesImage.getText().toString();
            updateSeriesItem(series.id, seriesId, name, imageUrl);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void updateSeriesItem(String id,  String seriesId, String name, String imageUrl) {
        series = new Series();
        series.seriesId = seriesId;
        series.name = name;
        series.imageUrl = imageUrl;

        Call<Void> call = crudService.editSeriesItem(id, series);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully updated the series");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("failed to update series");
            }
        });
    }
}
