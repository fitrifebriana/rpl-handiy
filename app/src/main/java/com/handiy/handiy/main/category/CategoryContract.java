package com.handiy.handiy.main.category;

import com.handiy.handiy.BasePresenter;
import com.handiy.handiy.BaseView;

import java.util.List;

/**
 * Created by FitriFebriana on 4/14/2017.
 */

public class CategoryContract {
    interface View extends BaseView {
        void showFurnitureData(List<Object> furnitures, String next_page);

        void showError(String message);

        void showProgress();

        void hideProgress();

        void showDecorationDetailsView(String extras);
    }

    interface Presenter extends BasePresenter {

    }
}
