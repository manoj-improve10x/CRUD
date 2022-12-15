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
    //Todo: change DashboardAdapter name
    private DashboardItemsAdapter dashboardItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        getSupportActionBar().setTitle("Dashboard");
        log("onCreate");
        setupData();
        setupDashboardItemsRv();
    }

    //ToDo: Implement progressBar in all activities
    private void setupData() {
        dashboardItems = new ArrayList<>();

        //Todo: DashboardItemsActivity-change objectNames
        DashboardItem dashboardItem1 = new DashboardItem();
        dashboardItem1.imageUrl = "https://e7.pngegg.com/pngimages/169/794/png-clipart-ios-message-icon-iphone-message-computer-icons-text-messaging-messenger-electronics-grass.png";
        dashboardItem1.title = "Messages";
        dashboardItems.add(dashboardItem1);
        DashboardItem dashboardItem2 = new DashboardItem();
        dashboardItem2.imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTF24qPNXvYKYx3rZvmPm0bGHRjHqnZqo4xjTMYCdycRaJaEwQWaFixVqNIfjrBC_goSxo&usqp=CAU";
        dashboardItem2.title = "Templates";
        dashboardItems.add(dashboardItem2);
        DashboardItem dashboardItem3 = new DashboardItem();
        dashboardItem3.imageUrl = "https://www.google.com/search?rlz=1C1JJTC_enIN1022IN1022&sxsrf=ALiCzsZuPMiIe7SloM6SUgGMo5LJTYASog:1670913223759&q=movie+folder+png&tbm=isch&sa=X&ved=2ahUKEwiD-pvp_PX7AhVwH7cAHUFeAEcQ0pQJegQIDBAB&biw=1536&bih=746&dpr=1.25#imgrc=oXmHMYeeUnfyoM";
        dashboardItem3.title = "Movies";
        dashboardItems.add(dashboardItem3);
        DashboardItem dashboardItem4 = new DashboardItem();
        dashboardItem4.title = "SeriesList";
        dashboardItem4.imageUrl = "https://www.google.com/search?rlz=1C1JJTC_enIN1022IN1022&sxsrf=ALiCzsZuPMiIe7SloM6SUgGMo5LJTYASog:1670913223759&q=movie+folder+png&tbm=isch&sa=X&ved=2ahUKEwiD-pvp_PX7AhVwH7cAHUFeAEcQ0pQJegQIDBAB&biw=1536&bih=746&dpr=1.25#imgrc=oXmHMYeeUnfyoM";
        dashboardItems.add(dashboardItem4);
    }
//Todo: Create new Method for ids in all classes give proper name

    private void setupDashboardItemsRv() {
        dashboardItemsRv = findViewById(R.id.dash_board_rv);
        dashboardItemsRv.setLayoutManager(new LinearLayoutManager(this));
        dashboardItemsAdapter = new DashboardItemsAdapter();
        dashboardItemsAdapter.setData(dashboardItems);
        dashboardItemsRv.setAdapter(dashboardItemsAdapter);
    }
}