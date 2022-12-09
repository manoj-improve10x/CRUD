package com.example.crud.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.crud.R;

import java.util.ArrayList;

public class DashBoardActivity extends AppCompatActivity {

    private ArrayList<DashBoard> dashBoards;
    private RecyclerView dashBoardRv;
    private DashBoardAdapter dashBoardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        getSupportActionBar().setTitle("Dash Board");
        Log.i("DashBoardActivity", "DashBoard onCreate called");
        setupData();
        setupDashBoardRv();
    }

    private void setupData() {
        dashBoards = new ArrayList<>();

        DashBoard dashBoard1 = new DashBoard();
        dashBoard1.imageUrl = "https://e7.pngegg.com/pngimages/169/794/png-clipart-ios-message-icon-iphone-message-computer-icons-text-messaging-messenger-electronics-grass.png";
        dashBoard1.title = "Messages";
        dashBoards.add(dashBoard1);
        DashBoard dashBoard2 = new DashBoard();
        dashBoard2.imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTF24qPNXvYKYx3rZvmPm0bGHRjHqnZqo4xjTMYCdycRaJaEwQWaFixVqNIfjrBC_goSxo&usqp=CAU";
        dashBoard2.title = "Templates";
        dashBoards.add(dashBoard2);
        DashBoard dashBoard3 = new DashBoard();
        dashBoard3.imageUrl = "https://img2.sfilm.hu/original/7VRkmVB23rloRbZHsCRPscXlgQp.jpg";
        dashBoard3.title = "Movies";
        dashBoards.add(dashBoard3);
        DashBoard dashBoard4 = new DashBoard();
        dashBoard4.title = "SeriesList";
        dashBoard4.imageUrl = "https://img2.sfilm.hu/original/7VRkmVB23rloRbZHsCRPscXlgQp.jpg";
        dashBoards.add(dashBoard4);

    }

    private void setupDashBoardRv() {
        dashBoardRv = findViewById(R.id.dash_board_rv);
        dashBoardRv.setLayoutManager(new LinearLayoutManager(this));
        dashBoardAdapter = new DashBoardAdapter();
        dashBoardAdapter.setData(dashBoards);
        dashBoardRv.setAdapter(dashBoardAdapter);
    }
}