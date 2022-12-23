package com.example.crud.dashboard;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.crud.R;
import com.example.crud.base.BaseActivity;
import com.example.crud.databinding.ActivityDashBoardBinding;

import java.util.ArrayList;

public class DashboardItemsActivity extends BaseActivity {

    private ActivityDashBoardBinding binding;
    private ArrayList<DashboardItem> dashboardItems;
    private DashboardItemsAdapter dashboardItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Dashboard");
        log("onCreate");
        setupData();
        setupDashboardItemsRv();
    }

    private void setupData() {
        dashboardItems = new ArrayList<>();

        DashboardItem messages = new DashboardItem();
        messages.imageUrl = "https://e7.pngegg.com/pngimages/169/794/png-clipart-ios-message-icon-iphone-message-computer-icons-text-messaging-messenger-electronics-grass.png";
        messages.title = "Messages";
        dashboardItems.add(messages);
        DashboardItem templates = new DashboardItem();
        templates.imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTF24qPNXvYKYx3rZvmPm0bGHRjHqnZqo4xjTMYCdycRaJaEwQWaFixVqNIfjrBC_goSxo&usqp=CAU";
        templates.title = "Templates";
        dashboardItems.add(templates);
        DashboardItem movies = new DashboardItem();
        movies.imageUrl = "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/i/ed4f9bf6-0144-4b2e-bfff-60bd129cd587/d4q8dy8-4bc1e5ef-46f7-42c8-b8dd-0856f232098b.jpg";
        movies.title = "Movies";
        dashboardItems.add(movies);
        DashboardItem seriesItems = new DashboardItem();
        seriesItems.title = "SeriesList";
        seriesItems.imageUrl = "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/i/574a12ae-b953-4f51-8253-5a7f34d477fb/dce38iv-c126dcac-1629-4a7b-b410-4eb8546227a7.png/v1/fill/w_512,h_512,q_80,strp/series_general_folder_icon_by_mrartoholic_dce38iv-fullview.jpg";
        dashboardItems.add(seriesItems);
    }

    private void setupDashboardItemsRv() {
        binding.dashboardItemsRv.setLayoutManager(new LinearLayoutManager(this));
        dashboardItemsAdapter = new DashboardItemsAdapter();
        dashboardItemsAdapter.setData(dashboardItems);
        binding.dashboardItemsRv.setAdapter(dashboardItemsAdapter);
    }
}