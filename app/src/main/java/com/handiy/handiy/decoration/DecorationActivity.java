package com.handiy.handiy.decoration;

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

public class DecorationActivity extends AppCompatActivity implements DecorationContract.View, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, DecorationAdapter.DecorationItemListener{

    private DecorationContract.Presenter presenter;
    private DecorationAdapter decorationAdapter;
    private SwipeRefreshLayout srDecoration;
    private RecyclerView rvDecoration;
    List<Object> decorationDataSet = new ArrayList<>();
    private LinearLayout linearLayout;
    private TextView txtRetry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decoration);

        setupSwipeRefresh();
        setupToolbar();
        setupRecyclerView();

        presenter = new DecorationPresenter(this);
        presenter.start();
    }

    private void initView() {

    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.decoration_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Decoration");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupSwipeRefresh() {
        srDecoration = (SwipeRefreshLayout) findViewById(R.id.decoration_swiperefresh_tutorial);
        srDecoration.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        srDecoration.setOnRefreshListener(this);
    }

    private void setupRecyclerView() {
        decorationAdapter = new DecorationAdapter(getContext(), new ArrayList<>(), this);
        rvDecoration = (RecyclerView) findViewById(R.id.tutorial_list);
        rvDecoration.setLayoutManager(new LinearLayoutManager(getContext()) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        });
        rvDecoration.setAdapter(decorationAdapter);
    }

    @Override
    public Context getContext() {
        return DecorationActivity.this;
    }

    @Override
    public void showDecorationData(List<Object> decorations, String categoryId) {
        this.decorationDataSet.clear();
        this.decorationDataSet.addAll(decorations);
        decorationAdapter.replaceData(this.decorationDataSet);
        //decorationAdapter.replaceData(decorations);
    }

    @Override
    public void showError(String message) {
        if (getContext() != null && message != null)
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

        this.decorationDataSet.clear();
        decorationAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        srDecoration.post(new Runnable() {
            @Override
            public void run() {
                srDecoration.setRefreshing(true);
            }
        });
    }

    @Override
    public void hideProgress() {
        srDecoration.post(new Runnable() {
            @Override
            public void run() {
                srDecoration.setRefreshing(false);
            }
        });
    }

    @Override
    public void showDecorationDetailsView(String extras) {
        Intent intent = new Intent(DecorationActivity.this, DetailActivity.class);
        intent.putExtra("tutorial-detail", extras);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onRefresh() {
        presenter.loadDecorationData("1");
    }

    @Override
    public void onDecorationClick(String extras) {
        presenter.openDecorationDetails(extras);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
