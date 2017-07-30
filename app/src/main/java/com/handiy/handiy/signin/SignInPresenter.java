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

    public SignInPresenter(SignInContract.View signInView) {
        this.signInView = signInView;
    }

    @Override
    public void start() {

    }

    @Override
    public void postSignIn(String username, String email, String name) {
        signInView.showProgress();
        Call<UserModel> call = APIService.factory.create().postSignIn(username, email, name);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                signInView.hideProgress();
                if (response.isSuccessful()) {
                    signInView.showLoginView(response.body().getUsername(), response.body().getEmail(), response.body().getName());
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                signInView.hideProgress();
            }
        });
    }
}
