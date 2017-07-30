package com.handiy.handiy.bookmark;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.handiy.handiy.R;
import com.handiy.handiy.data.BookmarkModel;
import com.handiy.handiy.data.source.remote.APIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by FitriFebriana on 7/28/2017.
 */

public class BookmarkPresenter implements BookmarkContract.Presenter {
    private final BookmarkContract.View bookmarkView;
    private List<Object> bookmarks = new ArrayList<>();

    public BookmarkPresenter(BookmarkContract.View bookmarkView) {
        this.bookmarkView = bookmarkView;
    }

    @Override
    public void start() {
        loadBookmark("idzarunianti");
    }

    @Override
    public void loadBookmark(final String username) {
        bookmarkView.showProgress();

        Context context = bookmarkView.getContext();
        final String txtError = context.getResources().getString(R.string.error);

        APIService apiService = APIService.factory.create();
        Call<BookmarkModel.BookmarkListModel> call = apiService.getAllBookmarks(username);
        call.enqueue(new Callback<BookmarkModel.BookmarkListModel>() {
            @Override
            public void onResponse(Call<BookmarkModel.BookmarkListModel> call, Response<BookmarkModel.BookmarkListModel> response) {
                Log.d("testing", "response: "+response.message()+" *** "+response.code()+" *** "+response.isSuccessful() +
                        " *** " + response.raw().toString());
                bookmarkView.hideProgress();
                if (response.isSuccessful()) {
                    bookmarks.clear();
                    bookmarks.addAll(response.body().getResult());
                    bookmarkView.showBookmarkData(bookmarks);

                }
            }

            @Override
            public void onFailure(Call<BookmarkModel.BookmarkListModel> call, Throwable t) {
                bookmarks.clear();
                bookmarkView.hideProgress();
                bookmarkView.showErrorMessage(txtError);
                Log.d("test", t.getMessage());
            }
        });
    }


}
