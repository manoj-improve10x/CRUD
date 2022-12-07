package com.example.crud.messages;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Message implements Serializable {

    @SerializedName("_id")
    public String id;
    public String name;
    @SerializedName("phoneNumber")
    public String number;
    public String messageText;
}
