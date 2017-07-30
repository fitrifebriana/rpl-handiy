package com.handiy.handiy.wear;

import com.handiy.handiy.BasePresenter;
import com.handiy.handiy.BaseView;

import java.util.List;

/**
 * Created by FitriFebriana on 4/14/2017.
 */

public class WearContract {
    interface View extends BaseView {
        void showWearData(List<Object> wears, String categoryId);

        void showError(String message);

        void showProgress();

        void hideProgress();

        void showWearDetailsView(String extras);
    }

    interface Presenter extends BasePresenter {
        void loadWearData(String extras);

        void openWearDetails(String extras);
    }
}
