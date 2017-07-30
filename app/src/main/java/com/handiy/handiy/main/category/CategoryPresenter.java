package com.handiy.handiy.main.category;

/**
 * Created by FitriFebriana on 4/14/2017.
 */

public class CategoryPresenter implements CategoryContract.Presenter {

    private final CategoryContract.View categoryView;

    public CategoryPresenter(CategoryContract.View categoryView) {
        this.categoryView = categoryView;
    }

    @Override
    public void start() {
    }
}

