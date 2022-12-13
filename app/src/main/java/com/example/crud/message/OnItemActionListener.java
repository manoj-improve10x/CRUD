package com.example.crud.message;

public interface OnItemActionListener {

    void OnDelete(String id);

    void onEdit(Message message);
}
