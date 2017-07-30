package com.handiy.handiy.signin;

import com.handiy.handiy.BasePresenter;
import com.handiy.handiy.BaseView;
import com.handiy.handiy.data.UserModel;

import java.util.List;

/**
 * Created by FitriFebriana on 7/30/2017.
 */

public class SignInContract {
    interface View extends BaseView {


        void showProgress();

        void hideProgress();

        void showErrorMessage(String message);

        void showMainView(List<UserModel> userDataModel);

    }

    interface Presenter extends BasePresenter {
        void postSignIn(String username, String email, String name);
    }
}
