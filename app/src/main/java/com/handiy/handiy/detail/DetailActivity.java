package com.handiy.handiy.detail;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.handiy.handiy.R;
import com.handiy.handiy.data.TutorialModel;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements DetailContract.View, DetailAdapter.DetailItemListener, SwipeRefreshLayout.OnRefreshListener {

    private DetailAdapter detailAdapter;
    private RecyclerView rvSteps;
    private ImageView imgHeader;
    private TextView txtHeader;
    List<Object> detailDataSet = new ArrayList<>();
    private CheckBox cbBookmark;

    Context context;
    SwipeRefreshLayout srDetail;
    DetailContract.Presenter presenter;
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
        @Override
        public RecyclerView.LayoutParams generateDefaultLayoutParams() {
            return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initView();
        setupToolbar();
        setupRecyclerView();

        presenter = new DetailPresenter(this);
        presenter.start();
        if (getIntent() != null && getIntent().getStringExtra("tutorial-detail") != null) {
            presenter.loadDetails(new Gson().fromJson(getIntent().getStringExtra("tutorial-detail"), TutorialModel.class).getId());
        }


        cbBookmark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(getContext(), "Bookmarked", Toast.LENGTH_SHORT).show();
                } else {
                    presenter.postBookmark("idzarunianti", new Gson().fromJson(getIntent().getStringExtra("tutorial-detail"), TutorialModel.class));
                    Toast.makeText(getContext(), "Bookmark Unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void initView() {
        cbBookmark = (CheckBox)findViewById(R.id.checkbox_bookmark);
        imgHeader = (ImageView)findViewById(R.id.tutorialdetail_imageview_header);
        txtHeader = (TextView)findViewById(R.id.tutorialdetail_textview_header);
    }

    private void setupRecyclerView() {
        rvSteps = (RecyclerView)findViewById(R.id.detail_recyclerview);
        detailAdapter = new DetailAdapter(getContext(), new ArrayList<>(), this);
        rvSteps.setLayoutManager(linearLayoutManager);
        rvSteps.setAdapter(detailAdapter);
    }

    @Override
    public Context getContext() {
        return DetailActivity.this;
    }

    @Override
    public void showDetailsData(List<Object> details) {
        Glide.with(getContext()).load(new Gson().fromJson(getIntent().getStringExtra("tutorial-detail"), TutorialModel.class).getThumbnail()).into(imgHeader);
        txtHeader.setText(new Gson().fromJson(getIntent().getStringExtra("tutorial-detail"), TutorialModel.class).getTitle());
        this.detailDataSet.clear();
        this.detailDataSet.addAll(details);
        detailAdapter.replaceData(this.detailDataSet);
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tutorial Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onCreationClick(String title) {

    }

    @Override
    public void onRefresh() {
        presenter.loadDetails(new Gson().fromJson(getIntent().getStringExtra("tutorial-detail"), TutorialModel.class).getId());
    }
}
