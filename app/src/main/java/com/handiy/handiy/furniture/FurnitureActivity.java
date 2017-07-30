package com.handiy.handiy.furniture;

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

public class FurnitureActivity extends AppCompatActivity implements FurnitureContract.View, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, FurnitureAdapter.FurnitureItemListener{

    private FurnitureAdapter furnitureAdapter;
    private FurnitureContract.Presenter presenter;
    private SwipeRefreshLayout srFurniture;
    List<Object> furnitureDataSet = new ArrayList<>();
    TextView txtTutorialTitle;
    ImageView imgThumbnail;
    Context context;
    private LinearLayout linearLayout;
    private TextView txtRetry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_furniture);

        initView();
        setupToolbar();
        setupSwipeRefresh();
        setupRecyclerView();

        presenter = new FurniturePresenter(this);
        presenter.start();
    }

    private void initView() {
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.furniture_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Furniture");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupSwipeRefresh() {
        srFurniture = (SwipeRefreshLayout) findViewById(R.id.furniture_swiperefresh_tutorial);
        srFurniture.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        srFurniture.setOnRefreshListener(this);
    }

    private void setupRecyclerView() {
        furnitureAdapter = new FurnitureAdapter(context, new ArrayList<>(), this);
        RecyclerView rvFurniture = (RecyclerView) findViewById(R.id.tutorial_list);
        rvFurniture.setLayoutManager(new LinearLayoutManager(getContext()) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        });
        rvFurniture.setAdapter(furnitureAdapter);
    }

    @Override
    public Context getContext() {
        return FurnitureActivity.this;
    }

    @Override
    public void showFurnitureData(List<Object> furnitures, String categoryId) {
        this.furnitureDataSet.clear();
        this.furnitureDataSet.addAll(furnitures);
        furnitureAdapter.replaceData(this.furnitureDataSet);
    }

    @Override
    public void showError(String message) {
        if (getContext() != null && message != null)
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

        this.furnitureDataSet.clear();
        furnitureAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        srFurniture.post(new Runnable() {
            @Override
            public void run() {
                srFurniture.setRefreshing(true);
            }
        });
    }

    @Override
    public void hideProgress() {
        srFurniture.post(new Runnable() {
            @Override
            public void run() {
                srFurniture.setRefreshing(false);
            }
        });
    }


    @Override
    public void showDecorationDetailsView(String extras) {
        Intent intent = new Intent(FurnitureActivity.this, DetailActivity.class);
        intent.putExtra("tutorial-detail", extras);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onRefresh() {
        presenter.loadFurnitureData("2");
    }

    @Override
    public void onFurnitureClick(String extras) {
        presenter.openFurnitureDetails(extras);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
