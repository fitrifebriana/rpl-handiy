package com.handiy.handiy.splash;

import android.os.Handler;

/**
 * Created by FitriFebriana on 4/14/2017.
 */

public class SplashPresenter implements SplashContract.Presenter{
    private static int SPLASH_TIME_OUT = 2000;

    private final SplashContract.View splashView;

    public SplashPresenter(SplashContract.View splashView) {
        this.splashView = splashView;
    }

    @Override
    public void start(){

    }

    @Override
    public void finishLoading() {
        try {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    splashView.showMainView();
                }
            }, SPLASH_TIME_OUT);

        } catch (NullPointerException e) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    splashView.showMainView();
                }
            }, SPLASH_TIME_OUT);
        }
    }
}
