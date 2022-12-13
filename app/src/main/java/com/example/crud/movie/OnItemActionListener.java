package com.example.crud.movie;

public interface OnItemActionListener {

    void onDelete(String id);

    void onEdit(Movie movie);
}
