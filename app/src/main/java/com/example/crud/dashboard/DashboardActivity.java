package com.example.crud.dashboard;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.crud.R;
import com.example.crud.base.BaseActivity;

import java.util.ArrayList;

public class DashboardActivity extends BaseActivity {

    private ArrayList<Dashboard> dashboards;
    private RecyclerView dashBoardRv;
    private DashboardAdapter dashBoardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        getSupportActionBar().setTitle("Dashboard");
        log("onCreate");
        setupData();
        setupDashBoardRv();
    }

    private void setupData() {
        dashboards = new ArrayList<>();

        Dashboard dashboard1 = new Dashboard();
        dashboard1.imageUrl = "https://e7.pngegg.com/pngimages/169/794/png-clipart-ios-message-icon-iphone-message-computer-icons-text-messaging-messenger-electronics-grass.png";
        dashboard1.title = "Messages";
        dashboards.add(dashboard1);
        Dashboard dashboard2 = new Dashboard();
        dashboard2.imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTF24qPNXvYKYx3rZvmPm0bGHRjHqnZqo4xjTMYCdycRaJaEwQWaFixVqNIfjrBC_goSxo&usqp=CAU";
        dashboard2.title = "Templates";
        dashboards.add(dashboard2);
        Dashboard dashboard3 = new Dashboard();
        dashboard3.imageUrl = "https://www.google.com/search?rlz=1C1JJTC_enIN1022IN1022&sxsrf=ALiCzsZuPMiIe7SloM6SUgGMo5LJTYASog:1670913223759&q=movie+folder+png&tbm=isch&sa=X&ved=2ahUKEwiD-pvp_PX7AhVwH7cAHUFeAEcQ0pQJegQIDBAB&biw=1536&bih=746&dpr=1.25#imgrc=oXmHMYeeUnfyoM";
        dashboard3.title = "Movies";
        dashboards.add(dashboard3);
        Dashboard dashboard4 = new Dashboard();
        dashboard4.title = "SeriesList";
        dashboard4.imageUrl = "https://www.google.com/search?rlz=1C1JJTC_enIN1022IN1022&sxsrf=ALiCzsZuPMiIe7SloM6SUgGMo5LJTYASog:1670913223759&q=movie+folder+png&tbm=isch&sa=X&ved=2ahUKEwiD-pvp_PX7AhVwH7cAHUFeAEcQ0pQJegQIDBAB&biw=1536&bih=746&dpr=1.25#imgrc=oXmHMYeeUnfyoM";
        dashboards.add(dashboard4);
    }
//doubt
    private void setupDashBoardRv() {
        dashBoardRv = findViewById(R.id.dash_board_rv);
        dashBoardRv.setLayoutManager(new LinearLayoutManager(this));
        dashBoardAdapter = new DashboardAdapter();
        dashBoardAdapter.setData(dashboards);
        dashBoardRv.setAdapter(dashBoardAdapter);
    }
}