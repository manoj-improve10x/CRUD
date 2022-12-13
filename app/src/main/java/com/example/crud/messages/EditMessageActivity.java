package com.example.crud.messages;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.Constants;
import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditMessageActivity extends BaseAddEditMessageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().hasExtra(Constants.KEY_MESSAGE)) {
            messages = (Message) getIntent().getSerializableExtra(Constants.KEY_MESSAGE);
            getSupportActionBar().setTitle("Edit Message");
            showData();
        }
    }

    private void showData() {
        addNameTxt.setText(messages.name);
        addNumberTxt.setText(messages.number);
        addMessageTxt.setText(messages.messageText);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_message) {
            String name = addNameTxt.getText().toString();
            String number = addNumberTxt.getText().toString();
            String message =addMessageTxt.getText().toString();
                setEditMessage(this.messages.id,name,number,message);
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
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
                showToast("successfully updated the message");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("failed to update message ");
            }
        });
    }
}