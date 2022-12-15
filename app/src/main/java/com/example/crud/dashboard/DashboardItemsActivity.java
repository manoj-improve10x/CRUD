package com.example.crud.dashboard;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.crud.R;
import com.example.crud.base.BaseActivity;

import java.util.ArrayList;

public class DashboardItemsActivity extends BaseActivity {

    private ArrayList<DashboardItem> dashboardItems;
    private RecyclerView dashboardItemsRv;
    private DashboardItemsAdapter dashboardItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        getSupportActionBar().setTitle("Dashboard");
        log("onCreate");
        initViews();
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
        movies.imageUrl = "https://www.google.com/search?rlz=1C1JJTC_enIN1022IN1022&sxsrf=ALiCzsZuPMiIe7SloM6SUgGMo5LJTYASog:1670913223759&q=movie+folder+png&tbm=isch&sa=X&ved=2ahUKEwiD-pvp_PX7AhVwH7cAHUFeAEcQ0pQJegQIDBAB&biw=1536&bih=746&dpr=1.25#imgrc=oXmHMYeeUnfyoM";
        movies.title = "Movies";
        dashboardItems.add(movies);
        DashboardItem seriesItems = new DashboardItem();
        seriesItems.title = "SeriesList";
        seriesItems.imageUrl = "https://www.google.com/search?rlz=1C1JJTC_enIN1022IN1022&sxsrf=ALiCzsZuPMiIe7SloM6SUgGMo5LJTYASog:1670913223759&q=movie+folder+png&tbm=isch&sa=X&ved=2ahUKEwiD-pvp_PX7AhVwH7cAHUFeAEcQ0pQJegQIDBAB&biw=1536&bih=746&dpr=1.25#imgrc=oXmHMYeeUnfyoM";
        dashboardItems.add(seriesItems);
    }
//Todo: Create new Method for ids in all classes give proper name
    private void initViews() {
        dashboardItemsRv = findViewById(R.id.dashboard_items_rv);
    }

    private void setupDashboardItemsRv() {
        dashboardItemsRv.setLayoutManager(new LinearLayoutManager(this));
        dashboardItemsAdapter = new DashboardItemsAdapter();
        dashboardItemsAdapter.setData(dashboardItems);
        dashboardItemsRv.setAdapter(dashboardItemsAdapter);
    }
}