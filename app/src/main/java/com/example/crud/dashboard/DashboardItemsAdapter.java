package com.example.crud.dashboard;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.databinding.DashBoardItemBinding;
import com.example.crud.movie.MoviesActivity;
import com.example.crud.series.SeriesListActivity;
import com.example.crud.message.MessagesActivity;
import com.example.crud.R;
import com.example.crud.template.TemplatesActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DashboardItemsAdapter extends RecyclerView.Adapter<DashboardItemViewHolder> {

    private ArrayList<DashboardItem> dashboardItems;

    void setData(ArrayList<DashboardItem> dashboardItems) {
        this.dashboardItems = dashboardItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DashboardItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DashBoardItemBinding binding = DashBoardItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        DashboardItemViewHolder dashBoardItemViewHolder = new DashboardItemViewHolder(binding);
        return dashBoardItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardItemViewHolder holder, int position) {
        DashboardItem dashboardItem = dashboardItems.get(position);
        holder.binding.titleTxt.setText(dashboardItem.title);
        Picasso.get().load(dashboardItem.imageUrl).into(holder.binding.dashboardImg);
        holder.itemView.setOnClickListener(view -> {
            if (holder.binding.titleTxt.getText().toString().equalsIgnoreCase("Messages")) {
                Intent intent = new Intent(holder.binding.titleTxt.getContext(), MessagesActivity.class);
                holder.itemView.getContext().startActivity(intent);
            } else if (holder.binding.titleTxt.getText().toString().equalsIgnoreCase("SeriesList")) {
                Intent intent = new Intent(holder.binding.titleTxt.getContext(), SeriesListActivity.class);
                holder.itemView.getContext().startActivity(intent);
            } else if (holder.binding.titleTxt.getText().toString().equalsIgnoreCase("Templates")) {
                Intent intent = new Intent(holder.binding.titleTxt.getContext(), TemplatesActivity.class);
                holder.itemView.getContext().startActivity(intent);
            } else if (holder.binding.titleTxt.getText().toString().equalsIgnoreCase("Movies")) {
                Intent intent = new Intent(holder.binding.titleTxt.getContext(), MoviesActivity.class);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dashboardItems.size();
    }
}
