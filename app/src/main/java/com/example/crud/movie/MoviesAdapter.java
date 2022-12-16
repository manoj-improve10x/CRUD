package com.example.crud.movie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<Movie> movies;
    private OnItemActionListener onItemActionListener;

    void setData(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    void setOnItemActionListener(OnItemActionListener actionListener) {
        onItemActionListener = actionListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_item, parent, false);
        MovieViewHolder movieViewHolder = new MovieViewHolder(view);
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.movieNameTxt.setText(movie.movieName);
        Picasso.get().load(movie.movieImageUrl).into(holder.movieImg);
        holder.DeleteBtn.setOnClickListener(view -> {
            onItemActionListener.onDelete(movie.id);
        });
        holder.itemView.setOnClickListener(view -> {
            onItemActionListener.onEdit(movie);
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
