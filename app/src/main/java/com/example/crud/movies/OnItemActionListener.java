package com.example.crud.movies;

import com.example.crud.movies.Movie;

public interface OnItemActionListener {
    void onDelete(String id);
    void onEdit(Movie movie);
}
