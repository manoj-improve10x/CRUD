package com.example.crud.template;

import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

import com.example.crud.R;
import com.example.crud.base.BaseActivity;

public class BaseAddEditTemplateActivity extends BaseActivity {
    //Todo: change object name templateTextTxt
    protected EditText TemplateTextTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_template);
        log("onCreate");
        initViews();
    }

    private void initViews() {
        TemplateTextTxt = findViewById(R.id.template_text_txt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_template_menu, menu);
        return true;
    }
}