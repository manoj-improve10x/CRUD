package com.example.crud.message;

import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

import com.example.crud.R;
import com.example.crud.base.BaseActivity;
import com.example.crud.databinding.ActivityAddEditMessageBinding;

public class BaseAddEditMessageActivity extends BaseActivity {

    protected ActivityAddEditMessageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        log("onCreate");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_message_menu, menu);
        return true;
    }
}