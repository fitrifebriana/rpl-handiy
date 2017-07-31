package com.handiy.handiy.detail;

import com.handiy.handiy.BasePresenter;
import com.handiy.handiy.BaseView;

import java.util.List;

/**
 * Created by FitriFebriana on 5/28/2017.
 */

public interface DetailContract {
    interface View extends BaseView {
        void showDetailsData(List<Object> details);

        void showCreationsData(List<Object> creations);

        void showError(String message);

        void showProgress();

        void hideProgress();
    }

    interface Presenter extends BasePresenter {
        void loadDetails(String tutorialId);

        void postBookmark(String username, String tutorialId);

        void deleteBookmark(String username, String bookmarks_id);

        void loadCreations(String tutorialId);

        void postCreation(String username,  String tutorialId);

        void deleteCreation(String tutorialId, String username);
    }
}
