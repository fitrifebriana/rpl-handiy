package com.handiy.handiy.furniture;

import com.handiy.handiy.BasePresenter;
import com.handiy.handiy.BaseView;

import java.util.List;

/**
 * Created by FitriFebriana on 4/14/2017.
 */

public class FurnitureContract {
    interface View extends BaseView {
        void showFurnitureData(List<Object> furnitures, String categoryId);

        void showError(String message);

        void showProgress();

        void hideProgress();

        void showDecorationDetailsView(String extras);
    }

    interface Presenter extends BasePresenter {
        void loadFurnitureData(String categoryId);

        void openFurnitureDetails(String extras);
    }
}
