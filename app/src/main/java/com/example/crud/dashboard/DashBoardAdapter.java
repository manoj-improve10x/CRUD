package com.example.crud.dashboard;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.AddEditMovieActivity;
import com.example.crud.MoviesActivity;
import com.example.crud.series.SeriesListActivity;
import com.example.crud.messages.MessagesActivity;
import com.example.crud.R;
import com.example.crud.templates.TemplatesActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DashBoardAdapter extends RecyclerView.Adapter<DashBoardViewHolder> {

    private ArrayList<DashBoard> dashBoards;

    public void setData(ArrayList<DashBoard> dashBoardList) {
        dashBoards = dashBoardList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DashBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dash_board_item,parent, false);
        DashBoardViewHolder dashBoardViewHolder = new DashBoardViewHolder(view);
        return dashBoardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DashBoardViewHolder holder, int position) {
        DashBoard dashBoard = dashBoards.get(position);
        holder.dashBoardTitle.setText(dashBoard.title);
        Picasso.get().load(dashBoard.imageUrl).into(holder.dashBoardImage);
        holder.dashboardLayout.setOnClickListener(view -> {
            if (holder.dashBoardTitle.getText().toString().equalsIgnoreCase("Messages")) {
                Intent intent = new Intent(holder.dashBoardTitle.getContext(), MessagesActivity.class);
                holder.itemView.getContext().startActivity(intent);
            }else if (holder.dashBoardTitle.getText().toString().equalsIgnoreCase("SeriesList")) {
                Intent intent = new Intent(holder.dashBoardTitle.getContext(), SeriesListActivity.class);
                holder.itemView.getContext().startActivity(intent);
            }else if(holder.dashBoardTitle.getText().toString().equalsIgnoreCase("Templates")) {
                Intent intent = new Intent(holder.dashBoardTitle.getContext(), TemplatesActivity.class);
                holder.itemView.getContext().startActivity(intent);
            }else if (holder.dashBoardTitle.getText().toString().equalsIgnoreCase("Movies")) {
                Intent intent = new Intent(holder.dashBoardTitle.getContext(), MoviesActivity.class);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dashBoards.size();
    }
}
