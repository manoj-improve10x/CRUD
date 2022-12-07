package com.example.crud.messages;

import com.example.crud.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MessagesService {

    @GET(Constants.MESSAGE_END_POINT)
    Call<List<Message>> fetchData();

    @POST(Constants.MESSAGE_END_POINT)
    Call<Message>  createMessage(@Body Message message);

    @DELETE(Constants.MESSAGE_END_POINT + "/{id}")
    Call<Void> deleteMessage(@Path("id") String id);

    @PUT(Constants.MESSAGE_END_POINT + "/{id}")
    Call<Void> editMessage(@Path("id") String id, @Body Message message);
}
