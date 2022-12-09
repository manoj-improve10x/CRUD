package com.example.crud.messages;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditMessageActivity extends AppCompatActivity {

    private CrudService service;
    private Message messages;
    private EditText addNameTxt;
    private EditText addNumberTxt;
    private EditText addMessageTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_message);
        Log.i("AddEditMessageActivity", "AddEditMessageActivity onCrate called");
        setupApiService();
        findIds();
        if (getIntent().hasExtra(Constants.KEY_MESSAGE)) {
            messages = (Message) getIntent().getSerializableExtra(Constants.KEY_MESSAGE);
            getSupportActionBar().setTitle("Edit Message");
            showData();
        }else {
            getSupportActionBar().setTitle("Add Message");
        }
    }

    public void setupToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void setupApiService() {
        CrudApi api = new CrudApi();
        service = api.createCrudService();
    }

    private void showData() {
        addNameTxt.setText(messages.name);
        addNumberTxt.setText(messages.number);
        addMessageTxt.setText(messages.messageText);
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_message) {
            String name = addNameTxt.getText().toString();
            String number = addNumberTxt.getText().toString();
            String message =addMessageTxt.getText().toString();
            if (this.messages == null){
                setAddMessage(name,number,message);
            }else {
                setEditMessage(this.messages.id,name,number,message);
            }
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void setAddMessage(String name, String number, String message) {
        messages = new Message();
        messages.name = name;
        messages.messageText = message;
        messages.number = number;

        Call<Message> call = service.createMessage(messages);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                setupToast("Successfully added");
                finish();
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });
    }
    private void setEditMessage(String id, String name, String number, String message) {
        messages = new Message();
        messages.name = name;
        messages.number = number;
        messages.messageText = message;

        Call<Void> call = service.editMessage(id,messages);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                setupToast("successfully edited");
                finish();
                Log.i("AddEditMessageActivity", "AddEditMessageActivity onEdit called");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }
}