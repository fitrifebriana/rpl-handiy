package com.handiy.handiy.main.category;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handiy.handiy.R;
import com.handiy.handiy.decoration.DecorationActivity;
import com.handiy.handiy.furniture.FurnitureActivity;
import com.handiy.handiy.wear.WearActivity;

import java.util.List;

/**
 * Created by FitriFebriana on 4/14/2017.
 */

public class CategoryFragment extends Fragment implements CategoryContract.View, View.OnClickListener {

    private CategoryContract.Presenter presenter;

    private CardView cvFurniture;
    private CardView cvDecoration;
    private CardView cvWear;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, null);

        initView(view);
        presenter = new CategoryPresenter(this);
        presenter.start();

        cvFurniture.setOnClickListener(this);
        cvDecoration.setOnClickListener(this);
        cvWear.setOnClickListener(this);

        return view;
    }

    private void initView(View view) {
        cvFurniture = (CardView) view.findViewById(R.id.category_cardview_furniture);
        cvDecoration = (CardView) view.findViewById(R.id.category_cardview_decoration);
        cvWear = (CardView) view.findViewById(R.id.category_cardview_wear);
    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void showFurnitureData(List<Object> furnitures, String next_page) {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showDecorationDetailsView(String extras) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.category_cardview_furniture:
                showFurnitureView();
                break;
            case R.id.category_cardview_decoration:
                showDecorationView();
                break;
            case R.id.category_cardview_wear:
                showWearView();
                break;
        }
    }

    private void showWearView() {
        Intent intent = new Intent(getActivity(), WearActivity.class);
        startActivity(intent);
    }

    private void showDecorationView() {
        Intent intent = new Intent(getActivity(), DecorationActivity.class);
        startActivity(intent);
    }

    private void showFurnitureView() {
        Intent intent = new Intent(getActivity(), FurnitureActivity.class);
        startActivity(intent);
    }
}
