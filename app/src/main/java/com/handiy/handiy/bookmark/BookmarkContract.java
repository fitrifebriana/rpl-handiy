package com.handiy.handiy.bookmark;

import com.handiy.handiy.BasePresenter;
import com.handiy.handiy.BaseView;

import java.util.List;

/**
 * Created by FitriFebriana on 7/28/2017.
 */

public interface BookmarkContract {
    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void showErrorMessage(String message);

        void showBookmarkData(List<Object> bookmarkDataSet);
    }
    interface Presenter extends BasePresenter{
        void loadBookmark(String username);
    }
}
