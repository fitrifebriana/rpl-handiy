package com.handiy.handiy.detail;

import com.handiy.handiy.BasePresenter;
import com.handiy.handiy.BaseView;
import com.handiy.handiy.data.TutorialModel;

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

        void postBookmark(String username, TutorialModel tutorial);

        void deleteBookmark(String username, String bookmarks_id);

        void loadCreations(String tutorialId);

        void postCreation(String tutorialId, String username);
    }
}
