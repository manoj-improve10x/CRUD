package com.example.crud.series;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.api.CrudApi;
import com.example.crud.api.CrudService;
import com.example.crud.base.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditSeriesActivity extends BaseActivity {

    private CrudService service;
    private Series series;
    private EditText seriesId;
    private EditText seriesName;
    private EditText seriesImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_series);
        log("onCreate");
        setupApiService();
        findIds();
        if (getIntent().hasExtra(Constants.KEY_SERIES)) {
            series = (Series)getIntent().getSerializableExtra(Constants.KEY_SERIES);
            getSupportActionBar().setTitle("Edit Series");
            showData();
        }else {
            getSupportActionBar().setTitle("Add Series");
        }
    }

    private void setupApiService() {
        CrudApi api =new CrudApi();
        service = api.createCrudService();
    }

    private void findIds() {
        seriesId = findViewById(R.id.series_id);
        seriesName = findViewById(R.id.series_name);
        seriesImage = findViewById(R.id.series_image);
    }

    private void showData() {
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
    private void setEditSeries(String id, String name, String imageUrl) {
        series = new Series();
       // series.id = id;
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