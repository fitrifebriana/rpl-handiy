package com.handiy.handiy.detail;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.handiy.handiy.R;
import com.handiy.handiy.data.BookmarkModel;
import com.handiy.handiy.data.CreationModel;
import com.handiy.handiy.data.TutorialModel;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements DetailContract.View, DetailAdapter.DetailItemListener, SwipeRefreshLayout.OnRefreshListener, CreationAdapter.DetailItemListener {

    private DetailAdapter detailAdapter;
    private RecyclerView rvSteps;
    private RecyclerView rvCreations;
    private ImageView imgHeader;
    private TextView txtHeader;
    private CreationAdapter creationAdapter;
    List<Object> detailDataSet = new ArrayList<>();
    List<Object> creationDataSet = new ArrayList<>();
    private CheckBox cbBookmark;
    private Button btnAddCreation;

    Context context = DetailActivity.this;
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
        setupCreationsRecyclerView();

        presenter = new DetailPresenter(this);
        presenter.start();
        if (getIntent() != null && getIntent().getStringExtra("tutorial-detail") != null) {
            presenter.loadDetails(new Gson().fromJson(getIntent().getStringExtra("tutorial-detail"), TutorialModel.class).getId());
        }

        cbBookmark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    presenter.deleteBookmark("idzarunianti", new Gson().fromJson(getIntent().getStringExtra("tutorial-detail"), BookmarkModel.class).getTutorial_id());
                } else {
                    presenter.postBookmark("idzarunianti", new Gson().fromJson(getIntent().getStringExtra("tutorial-detail"), BookmarkModel.class).getTutorial_id());
                }
            }
        });

        btnAddCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //nampil dialog tambah kreasu
            }
        });
    }

    private void setupCreationsRecyclerView() {
        rvCreations = (RecyclerView)findViewById(R.id.creation_recyclerview);
        creationAdapter = new CreationAdapter(context, new ArrayList<>(), this);
        rvCreations.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvCreations.setAdapter(creationAdapter);
    }

    private void initView() {
        cbBookmark = (CheckBox)findViewById(R.id.checkbox_bookmark);
        imgHeader = (ImageView)findViewById(R.id.tutorialdetail_imageview_header);
        txtHeader = (TextView)findViewById(R.id.tutorialdetail_textview_header);
        btnAddCreation = (Button)findViewById(R.id.detail_button_add_creation);
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
    public void showCreationsData(List<Object> creations) {
        this.creationDataSet.clear();
        this.creationDataSet.addAll(creations);
        creationAdapter.replaceData(this.creationDataSet);
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
        presenter.loadCreations(new Gson().fromJson(getIntent().getStringExtra("tutorial-detail"), CreationModel.class).getTutorial_id());
    }
}
