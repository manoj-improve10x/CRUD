package com.example.crud.template;

import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

import com.example.crud.R;
import com.example.crud.base.BaseActivity;
import com.example.crud.databinding.ActivityAddEditTemplateBinding;

public class BaseAddEditTemplateActivity extends BaseActivity {

    protected ActivityAddEditTemplateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditTemplateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        log("onCreate");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_template_menu, menu);
        return true;
    }
}