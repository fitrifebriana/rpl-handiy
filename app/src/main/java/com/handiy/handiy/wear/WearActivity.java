package com.handiy.handiy.wear;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.handiy.handiy.R;
import com.handiy.handiy.detail.DetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FitriFebriana on 4/14/2017.
 */

public class WearActivity extends AppCompatActivity implements WearContract.View, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, WearAdapter.WearItemListener{

    private WearAdapter wearAdapter;
    private WearContract.Presenter presenter;
    private SwipeRefreshLayout srWear;
    List<Object> wearDataSet = new ArrayList<>();
    Context context;
    TextView txtTutorialTitle;
    ImageView imgThumbnail;
    private LinearLayout linearLayout;
    private TextView txtRetry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wear);

        initView();
        setupToolbar();
        setupSwipeRefresh();
        setupRecyclerView();

        presenter = new WearPresenter(this);
        presenter.start();
    }

    private void initView() {
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.wear_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Wear");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupSwipeRefresh() {
        srWear = (SwipeRefreshLayout) findViewById(R.id.wear_swiperefresh_tutorial);
        srWear.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        srWear.setOnRefreshListener(this);
    }

    private void setupRecyclerView() {
        wearAdapter = new WearAdapter(context, new ArrayList<>(), this);
        RecyclerView rvWear = (RecyclerView) findViewById(R.id.tutorial_list);
        rvWear.setLayoutManager(new LinearLayoutManager(getContext()) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        });
        rvWear.setAdapter(wearAdapter);
    }

    @Override
    public Context getContext() {
        return WearActivity.this;
    }

    @Override
    public void showWearData(List<Object> wears, String categoryId) {
        this.wearDataSet.clear();
        this.wearDataSet.addAll(wears);
        wearAdapter.replaceData(this.wearDataSet);
    }

    @Override
    public void showError(String message) {
        if (getContext() != null && message != null)
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

        this.wearDataSet.clear();
        wearAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        srWear.post(new Runnable() {
            @Override
            public void run() {
                srWear.setRefreshing(true);
            }
        });
    }

    @Override
    public void hideProgress() {
        srWear.post(new Runnable() {
            @Override
            public void run() {
                srWear.setRefreshing(false);
            }
        });
    }


    @Override
    public void showWearDetailsView(String extras) {
        Intent intent = new Intent(WearActivity.this, DetailActivity.class);
        intent.putExtra("tutorial-detail", extras);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onRefresh() {
        presenter.loadWearData("3");
    }

    @Override
    public void onWearClick(String extras) {
        presenter.openWearDetails(extras);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
