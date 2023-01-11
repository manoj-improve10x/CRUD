package com.example.crud.message;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.Constants;
import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditMessageActivity extends BaseAddEditMessageActivity {

    private Message message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Edit Message");
        if (getIntent().hasExtra(Constants.KEY_MESSAGE)) {
            message = (Message) getIntent().getSerializableExtra(Constants.KEY_MESSAGE);
            showData();
        }
    }

    private void showData() {
       binding.setMessage(message);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            String name = binding.nameTxt.getText().toString();
            String number = binding.phoneNumberTxt.getText().toString();
            String message = binding.messageTextTxt.getText().toString();
            EditMessage(this.message.id, name, number, message);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void EditMessage(String id, String name, String number, String message) {
        this.message = new Message();
        this.message.name = name;
        this.message.number = number;
        this.message.messageText = message;

        Call<Void> call = crudService.editMessage(id, this.message);
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