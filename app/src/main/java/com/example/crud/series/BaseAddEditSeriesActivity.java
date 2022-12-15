package com.example.crud.series;

import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

import com.example.crud.R;
import com.example.crud.api.CrudApi;
import com.example.crud.api.CrudService;
import com.example.crud.base.BaseActivity;

//ToDo: add item word
public class BaseAddEditSeriesActivity extends BaseActivity {

    protected CrudService crudService;
    protected EditText seriesIdTxt;
    protected EditText seriesNameTxt;
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
        CrudApi api = new CrudApi();
        crudService = api.createCrudService();
    }

    private void findIds() {
        seriesIdTxt = findViewById(R.id.series_id);
        seriesNameTxt = findViewById(R.id.series_name);
        seriesImage = findViewById(R.id.series_image);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_series_menu, menu);
        return true;
    }
}