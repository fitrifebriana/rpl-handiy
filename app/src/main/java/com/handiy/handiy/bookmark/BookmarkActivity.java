package com.handiy.handiy.bookmark;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.handiy.handiy.R;

import java.util.ArrayList;
import java.util.List;

public class BookmarkActivity extends AppCompatActivity implements BookmarkContract.View, SwipeRefreshLayout.OnRefreshListener, BookmarkAdapter.BookmarkItemClickListener{

    RecyclerView rvBookmark;
    BookmarkAdapter bookmarkAdapter;
    SwipeRefreshLayout srBookmark;
    List<Object> bookmarkDataSet = new ArrayList<>();
    Context context = BookmarkActivity.this;
    BookmarkContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        setupToolbar();
        setupSwipeRefresh();
        setupRecyclerView();

        presenter = new BookmarkPresenter(this);
        presenter.start();
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.bookmark_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Bookmarks");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupRecyclerView() {
        bookmarkAdapter = new BookmarkAdapter(context, new ArrayList<>(), this);
        rvBookmark = (RecyclerView)findViewById(R.id.bookmark_recyclerview);
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        rvBookmark.setLayoutManager(layoutManager);
        rvBookmark.setAdapter(bookmarkAdapter);
    }

    private void setupSwipeRefresh() {
        srBookmark = (SwipeRefreshLayout)findViewById(R.id.bookmark_swipe_refresh);
        srBookmark.setColorSchemeColors(ContextCompat.getColor(BookmarkActivity.this, R.color.colorPrimaryDark));
        srBookmark.setOnRefreshListener(this);
    }

    @Override
    public Context getContext() {
        return BookmarkActivity.this;
    }

    @Override
    public void showProgress() {
        srBookmark.post(new Runnable() {
            @Override
            public void run() {
                srBookmark.setRefreshing(true);
            }
        });
    }

    @Override
    public void hideProgress() {
        srBookmark.post(new Runnable() {
            @Override
            public void run() {
                srBookmark.setRefreshing(false);
            }
        });
    }

    @Override
    public void showErrorMessage(String message) {
        if (getContext() != null && message != null)
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

        this.bookmarkDataSet.clear();
        bookmarkAdapter.notifyDataSetChanged();
    }

    @Override
    public void showBookmarkData(List<Object> bookmarks) {
        this.bookmarkDataSet.clear();
        this.bookmarkDataSet.addAll(bookmarks);
        bookmarkAdapter.replaceData(this.bookmarkDataSet);
    }

    @Override
    public void onRefresh() {
        presenter.loadBookmark("idzarunianti");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

