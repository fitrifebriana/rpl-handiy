package com.handiy.handiy.signin;


import android.content.Context;
import android.util.Log;

import com.handiy.handiy.data.UserModel;
import com.handiy.handiy.data.source.remote.APIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by FitriFebriana on 7/30/2017.
 */

public class SignInPresenter implements SignInContract.Presenter {
    private final SignInContract.View signInView;
    List<UserModel> list;

    public SignInPresenter(SignInContract.View signInView) {
        this.signInView = signInView;
    }

    @Override
    public void start() {

    }

    @Override
    public void postSignIn(String username, String email, String name) {
        Log.e("masuk","a");
        signInView.showProgress();
        Call<UserModel.UserDataModel> call = APIService.factory.create().postSignIn(username, email, name);
        call.enqueue(new Callback<UserModel.UserDataModel>() {
            @Override
            public void onResponse(Call<UserModel.UserDataModel> call, Response<UserModel.UserDataModel> response) {
               signInView.hideProgress();
                if (response.isSuccessful()){
                    list.clear();
                    signInView.showMainView(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<UserModel.UserDataModel> call, Throwable t) {
                list.clear();
                signInView.hideProgress();
            }
        });
    }
}
