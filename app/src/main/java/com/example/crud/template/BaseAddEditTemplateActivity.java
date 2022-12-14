package com.example.crud.template;

import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

import com.example.crud.R;
import com.example.crud.api.CrudApi;
import com.example.crud.api.CrudService;
import com.example.crud.base.BaseActivity;

public class BaseAddEditTemplateActivity extends BaseActivity {

    protected CrudService crudService;
    //change object name templateTxt
    protected EditText TemplateTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_template);
        setupApiService();
        log("onCreate");
        TemplateTxt = findViewById(R.id.add_template_txt);
    }

    protected void setupApiService() {
        CrudApi api = new CrudApi();
        crudService = api.createCrudService();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_template_menu, menu);
        return true;
    }
}