package com.example.huarongdao;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.LinkedList;

public class SelectActivity extends AppCompatActivity {

    private final LinkedList<String> mModeList = new LinkedList<>();

    private RecyclerView mRecyclerView;
    private ModeAdapter mAdapter;

    public static SelectActivity mactivity;
    public static Context mContext;
    public static int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        mactivity = this;
        mContext = this.getBaseContext();

        initMode();

        mRecyclerView = findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new ModeAdapter(this, mModeList);
        // Connect the adapter with the recycler view.
        mRecyclerView.setAdapter(mAdapter);
        // Give the recycler view a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        size = dm.widthPixels / 5 + 4;
    }

    private void initMode() {
        mModeList.addLast("赛前练习");
        mModeList.addLast("全面封锁");
        mModeList.addLast("带球突破");
        mModeList.addLast("持球进攻");
        mModeList.addLast("冲闯禁区");
        mModeList.addLast("四人防守");
        mModeList.addLast("近在咫尺");
        mModeList.addLast("人球分过");
        mModeList.addLast("夹缝生存");
        mModeList.addLast("交叉过人");
    }
}
