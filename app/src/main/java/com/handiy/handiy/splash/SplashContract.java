package com.handiy.handiy.splash;

import com.handiy.handiy.BasePresenter;
import com.handiy.handiy.BaseView;

/**
 * Created by FitriFebriana on 4/14/2017.
 */

public interface SplashContract {
    interface View extends BaseView {
        void showMainView();
    }

    interface Presenter extends BasePresenter {
        void finishLoading();
    }
}
