package com.handiy.handiy.main.timeline;

import com.handiy.handiy.BasePresenter;
import com.handiy.handiy.BaseView;
import com.handiy.handiy.data.TutorialModel;

import java.util.List;

/**
 * Created by FitriFebriana on 4/14/2017.
 */

public class TimelineContract {
    interface View extends BaseView {
        void showTimelineData(List<TutorialModel> timelines);

        void showError(String message);

        void showProgress();

        void hideProgress();

        void showTimelineDetailsView(String extras);
    }

    interface Presenter extends BasePresenter {
        void loadTimelineData();

        void openTimelineDetails(String extras);
    }
}
