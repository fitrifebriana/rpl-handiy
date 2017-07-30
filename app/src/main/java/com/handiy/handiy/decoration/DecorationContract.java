package com.handiy.handiy.decoration;

import com.handiy.handiy.BasePresenter;
import com.handiy.handiy.BaseView;

import java.util.List;

/**
 * Created by FitriFebriana on 4/14/2017.
 */

public class DecorationContract {
    interface View extends BaseView {
        void showDecorationData(List<Object> decorations, String next_page);

        void showError(String message);

        void showProgress();

        void hideProgress();

        void showDecorationDetailsView(String extras);
    }

    interface Presenter extends BasePresenter {
        void loadDecorationData(String categoryId);

        void openDecorationDetails(String extras);
    }
}
