package com.example.crud.series;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.api.CrudApi;
import com.example.crud.api.CrudService;
import com.example.crud.base.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseAddEditSeriesActivity extends BaseActivity {

    protected CrudService service;
    protected Series series;
    protected EditText seriesId;
    protected EditText seriesName;
    protected EditText seriesImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_series);
        log("onCreate");
        setupApiService();
        findIds();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_series_menu, menu);
        return true;
    }
}