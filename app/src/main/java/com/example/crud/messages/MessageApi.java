package com.example.crud.messages;

import com.example.crud.Constants;
import com.example.crud.messages.MessagesService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageApi {

    public MessagesService createMessagesService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MessagesService messagesService = retrofit.create(MessagesService.class);
        return messagesService;
    }

}
