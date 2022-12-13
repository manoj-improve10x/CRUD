package com.example.crud.series;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.Constants;
import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditSeriesActivity extends BaseAddEditSeriesActivity{

    private  Series series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().hasExtra(Constants.KEY_SERIES)) {
            series = (Series)getIntent().getSerializableExtra(Constants.KEY_SERIES);
            getSupportActionBar().setTitle("Edit Series");
            showData();
        }
    }

    private void showData() {
        seriesId.setText(series.id);
        seriesName.setText(series.name);
        seriesImage.setText(series.imageUrl);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //change name id
        if (item.getItemId() == R.id.check) {
            String id = seriesId.getText().toString();
            String name = seriesName.getText().toString();
            String imageUrl = seriesImage.getText().toString();
            setEditSeries(id, name, imageUrl);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
//change method name
    private void setEditSeries(String id, String name, String imageUrl) {
        series = new Series();
        series.seriesId = id;
        series.name = name;
        series.imageUrl = imageUrl;

        Call<Void> call = service.editSeries(id,series);
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
