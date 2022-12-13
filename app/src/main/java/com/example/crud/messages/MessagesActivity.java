package com.example.crud.messages;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.api.CrudApi;
import com.example.crud.api.CrudService;
import com.example.crud.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesActivity extends BaseActivity {

    private CrudService service;
    private ArrayList<Message> messages = new ArrayList<>();
    private RecyclerView messagesRv;
    private MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        getSupportActionBar().setTitle("Messages");
        log("on Create Called");
        setupApiService();
        setupMessageRv();
    }

    private void setupApiService() {
        CrudApi api = new CrudApi();
        service = api.createCrudService();
    }

    @Override
    protected void onResume() {
        super.onResume();
       log("on Resume");
        fetchData();
    }

    private void fetchData() {
        Call<List<Message>> call = service.fetchData();
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                List<Message> messages = response.body();
                showToast("successfully loaded the data");
                messageAdapter.setData(messages);

            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                showToast("failed to load data");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.messages_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add_message_menu){
            Intent intent = new Intent(this, AddMessageActivity.class);
            startActivity(intent);
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }


    private void setupMessageRv() {
        messagesRv = findViewById(R.id.messages_rv);
        messagesRv.setLayoutManager(new LinearLayoutManager(this));
        messageAdapter = new MessageAdapter();
        messageAdapter.setData(messages);
        messageAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void OnDelete(String id) {
                deleteMessage(id);
                fetchData();
            }

            @Override
            public void onEdit(Message message) {
                editMessage(message);
            }
        });
        messagesRv.setAdapter(messageAdapter);
    }

    private void deleteMessage(String id) {
        Call<Void> call = service.deleteMessage(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("successfully deleted the message");
                fetchData();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("failed to delete message");

            }
        });
    }

    private void editMessage(Message message) {
        Intent intent = new Intent(this, EditMessageActivity.class);
        intent.putExtra(Constants.KEY_MESSAGE, message);
        startActivity(intent);
    }
}