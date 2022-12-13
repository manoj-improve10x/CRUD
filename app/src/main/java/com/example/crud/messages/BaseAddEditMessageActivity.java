package com.example.crud.messages;

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

public class BaseAddEditMessageActivity extends BaseActivity {

    protected CrudService service;
    protected Message messages;
    protected EditText addNameTxt;
    protected EditText addNumberTxt;
    protected EditText addMessageTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_message);
        log("onCreate");
        setupApiService();
        findIds();
    }

    private void setupApiService() {
        CrudApi api = new CrudApi();
        service = api.createCrudService();
    }

    private void findIds() {
        addNameTxt = findViewById(R.id.add_name_txt);
        addNumberTxt = findViewById(R.id.add_number_txt);
        addMessageTxt = findViewById(R.id.add_message_txt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_message_menu, menu);
        return true;
    }
}