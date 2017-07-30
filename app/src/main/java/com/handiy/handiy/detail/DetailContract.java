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

        void showError(String message);

        void showProgress();

        void hideProgress();
    }

    interface Presenter extends BasePresenter {
        void loadDetails(String tutorialId);

        void postBookmark(String username, TutorialModel tutorial);
    }
}
