package com.example.crud.series;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud.Constants;
import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditSeriesActivity extends AppCompatActivity {

    public Series series;
    public EditText seriesId;
    public EditText seriesName;
    public EditText seriesImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_series);
        findIds();
        if (getIntent().hasExtra(Constants.KEY_SERIES)) {
            series = (Series)getIntent().getSerializableExtra(Constants.KEY_SERIES);
            getSupportActionBar().setTitle("Edit Series");
            showData();
        }else {
            getSupportActionBar().setTitle("Add Series");
        }
    }

    public void findIds() {
        seriesId = findViewById(R.id.series_id);
        seriesName = findViewById(R.id.series_name);
        seriesImage = findViewById(R.id.series_image);
    }

    public void showData() {
        seriesId.setText(series.id);
        seriesName.setText(series.name);
        seriesImage.setText(series.imageUrl);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_series_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.check) {
            String name = seriesName.getText().toString();
            String imageUrl = seriesImage.getText().toString();
            if (this.series == null) {
                setAddSeries(name, imageUrl);
            } else {
                setEditSeries(this.series.id, name, imageUrl);
            }
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }

    public  void setAddSeries(String name, String imageUrl) {
        series = new Series();
        series.name = name;
        series.imageUrl = imageUrl;
        SeriesListApi seriesListApi = new SeriesListApi();
        SeriesListService seriesListService = seriesListApi.createSeriesListService();
        Call<Series> call = seriesListService.createSeries(series);
        call.enqueue(new Callback<Series>() {
            @Override
            public void onResponse(Call<Series> call, Response<Series> response) {
                Toast.makeText(AddEditSeriesActivity.this, "success", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Series> call, Throwable t) {

            }
        });
    }
    public void setEditSeries(String id, String name, String imageUrl) {
        series = new Series();
       // series.id = id;
        series.name = name;
        series.imageUrl = imageUrl;

        SeriesListApi seriesListApi = new SeriesListApi();
        SeriesListService seriesListService = seriesListApi.createSeriesListService();
        Call<Void> call = seriesListService.editSeries(id,series);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(AddEditSeriesActivity.this, "successfully", Toast.LENGTH_SHORT).show();
                finish();

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

}