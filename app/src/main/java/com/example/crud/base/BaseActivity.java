package com.example.crud.base;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.crud.api.CrudApi;
import com.example.crud.api.CrudService;

public class BaseActivity extends AppCompatActivity {

    protected CrudService crudService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupApiService();
    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void log(String message) {
        Log.i(this.getLocalClassName(), message);
    }

    private void setupApiService() {
        CrudApi api = new CrudApi();
        crudService = api.createCrudService();
    }

}
