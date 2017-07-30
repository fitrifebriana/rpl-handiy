package com.handiy.handiy.creation;

import com.handiy.handiy.BasePresenter;
import com.handiy.handiy.BaseView;

import java.util.List;

/**
 * Created by FitriFebriana on 7/28/2017.
 */

public interface CreationContract {
    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void showErrorMessage(String message);

        void showCreationData(List<Object> creationDataSet);
    }
    interface Presenter extends BasePresenter{
        void loadCreation(String username);
    }
}
