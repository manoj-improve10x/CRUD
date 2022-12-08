package com.example.crud.messages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud.Constants;
import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditMessageActivity extends AppCompatActivity {

    private Message messages;
    private EditText addNameTxt;
    private EditText addNumberTxt;
    private EditText addMessageTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_message);
        findIds();
        if (getIntent().hasExtra(Constants.KEY_MESSAGE)) {
            messages = (Message) getIntent().getSerializableExtra(Constants.KEY_MESSAGE);
            getSupportActionBar().setTitle("Edit Message");
            showData();
        }else {
            getSupportActionBar().setTitle("Add Message");
        }
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
        getMenuInflater().inflate(R.menu.add_message_menu, menu);
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
        MessageApi messageApi = new MessageApi();
        MessagesService messagesService = messageApi.createMessagesService();
        Call<Message> call = messagesService.createMessage(messages);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                Toast.makeText(AddEditMessageActivity.this, "success", Toast.LENGTH_SHORT).show();
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

        MessageApi messageApi = new MessageApi();
        MessagesService messagesService = messageApi.createMessagesService();
        Call<Void> call = messagesService.editMessage(id,messages);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }
}