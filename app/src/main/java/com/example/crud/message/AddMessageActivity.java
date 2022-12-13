package com.example.crud.message;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMessageActivity extends BaseAddEditMessageActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Message");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_message) {
            String name = addNameTxt.getText().toString();
            String number = addNumberTxt.getText().toString();
            String message =addMessageTxt.getText().toString();
                setAddMessage(name,number,message);
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }
//change to method name add message
    private void setAddMessage(String name, String number, String message) {
        this.message = new Message();
        this.message.name = name;
        this.message.messageText = message;
        this.message.number = number;

        Call<Message> call = service.createMessage(this.message);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                showToast("Successfully added the message");
                finish();
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                showToast("failed to add message");
            }
        });
    }
}
