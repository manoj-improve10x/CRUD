package com.example.crud.messages;

import com.example.crud.messages.Message;

public interface OnItemActionListener {

    void OnDelete( String id);

    void onEdit(Message message);
}
