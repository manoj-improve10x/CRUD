package com.example.crud.message;

import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

import com.example.crud.R;
import com.example.crud.base.BaseActivity;

public class BaseAddEditMessageActivity extends BaseActivity {

    protected EditText nameTxt;
    protected EditText NumberTxt;
    protected EditText messageTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_message);
        log("onCreate");
        initViews();
    }

    private void initViews() {
        //ToDo: change object name phoneNumberTxt
        nameTxt = findViewById(R.id.name_txt);
        NumberTxt = findViewById(R.id.number_txt);
        //ToDo: change name messageTextTxt
        messageTxt = findViewById(R.id.message_txt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_message_menu, menu);
        return true;
    }
}