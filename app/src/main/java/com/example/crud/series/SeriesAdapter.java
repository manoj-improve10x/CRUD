package com.example.crud.series;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesViewHolder> {

    public List<Series> serieses;
    public OnItemActionListener onItemActionListener;

    public void setOnItemActionListener(OnItemActionListener actionListener) {
        onItemActionListener = actionListener;
    }
    public void setData(List<Series> seriesList) {
        serieses = seriesList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.series_item, parent, false);
        SeriesViewHolder seriesViewHolder = new SeriesViewHolder(view);
        return seriesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesViewHolder holder, int position) {
        Series series = serieses.get(position);
        holder.seriesNameTxt.setText(series.name);
        Picasso.get().load(series.imageUrl).into(holder.seriesImg);
        holder.seriesDeleteBtn.setOnClickListener(view -> {
            onItemActionListener.onDelete(series.id);
        });
        holder.itemView.setOnClickListener(view -> {
            onItemActionListener.onEdit(series);
        });
    }

    @Override
    public int getItemCount() {
        return serieses.size();
    }
}
