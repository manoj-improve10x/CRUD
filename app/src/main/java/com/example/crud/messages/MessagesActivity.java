package com.example.crud.messages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.crud.Constants;
import com.example.crud.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesActivity extends AppCompatActivity {

    public ArrayList<Message> messages = new ArrayList<>();
    public RecyclerView messagesRv;
    public MessageAdapter messageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        getSupportActionBar().setTitle("Messages");
        fetchData();
        setupMessageRv();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }

    private void fetchData() {
        MessageApi messageApi = new MessageApi();
        MessagesService messagesService = messageApi.createMessagesService();
        Call<List<Message>> call = messagesService.fetchData();
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                List<Message> messages = response.body();
                messageAdapter.setData(messages);
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {

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
            Intent intent = new Intent(this, AddEditMessageActivity.class);
            startActivity(intent);
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }


    public void setupMessageRv() {
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

    public void deleteMessage(String id) {
        MessageApi messageApi = new MessageApi();
        MessagesService messagesService = messageApi.createMessagesService();
        Call<Void> call = messagesService.deleteMessage(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                fetchData();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void editMessage(Message message) {
        Intent intent = new Intent(this, AddEditMessageActivity.class);
        intent.putExtra(Constants.KEY_MESSAGE, message);
        startActivity(intent);
    }
}