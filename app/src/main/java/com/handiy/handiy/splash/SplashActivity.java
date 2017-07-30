package com.handiy.handiy.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.handiy.handiy.R;
import com.handiy.handiy.signin.SigninActivity;

/**
 * Created by FitriFebriana on 4/14/2017.
 */

public class SplashActivity extends AppCompatActivity implements SplashContract.View {

    private SplashContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        presenter = new SplashPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.start();
        presenter.finishLoading();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showMainView() {
        Intent intent = new Intent(SplashActivity.this, SigninActivity.class);
        startActivity(intent);
        finish();
    }
}
