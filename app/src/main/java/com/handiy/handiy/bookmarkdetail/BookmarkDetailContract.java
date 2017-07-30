package com.handiy.handiy.bookmarkdetail;

import com.handiy.handiy.BasePresenter;
import com.handiy.handiy.BaseView;
import com.handiy.handiy.data.TutorialModel;

/**
 * Created by FitriFebriana on 5/28/2017.
 */

public interface BookmarkDetailContract {
    interface View extends BaseView {
        void DetailsData(TutorialModel tutorialDetails);

        void showError(String message);
    }

    interface Presenter extends BasePresenter {
        void loadEventDetailsDataLocal(TutorialModel tutorialDetails);

        void loadEventDetailsDataRemote(int id_event);
    }
}
