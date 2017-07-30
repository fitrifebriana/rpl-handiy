package com.handiy.handiy.furniture;

import android.content.Context;
import android.util.Log;

import com.handiy.handiy.R;
import com.handiy.handiy.data.TutorialModel;
import com.handiy.handiy.data.source.remote.APIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by FitriFebriana on 4/14/2017.
 */

public class FurniturePresenter implements FurnitureContract.Presenter{
    private final FurnitureContract.View furnitureView;
    private List<Object> furnitures = new ArrayList<>();

    public FurniturePresenter(FurnitureContract.View facilityView) {
        this.furnitureView = facilityView;
    }

    @Override
    public void start() {
        loadFurnitureData("2");
    }

    @Override
    public void loadFurnitureData(String categoryId) {
        furnitureView.showProgress();

        Context context = furnitureView.getContext();
        final String txtError = context.getResources().getString(R.string.error);

        APIService apiService = APIService.factory.create();
        Call<TutorialModel.TutorialListModel> call = apiService.getAllTutorials(categoryId);
        call.enqueue(new Callback<TutorialModel.TutorialListModel>() {
            @Override
            public void onResponse(Call<TutorialModel.TutorialListModel> call, Response<TutorialModel.TutorialListModel> response) {
                Log.d("testing", "response: "+response.message()+" *** "+response.code()+" *** "+response.isSuccessful() +
                        " *** " + response.raw().toString());
                furnitureView.hideProgress();
                if (response.isSuccessful()) {
                    furnitures.clear();
                    furnitures.addAll(response.body().getResult());
                    furnitureView.showFurnitureData(furnitures, "1");

                }
            }

            @Override
            public void onFailure(Call<TutorialModel.TutorialListModel> call, Throwable t) {
                furnitures.clear();
                furnitureView.hideProgress();
                furnitureView.showError(txtError);
            }
        });

    }

    @Override
    public void openFurnitureDetails(String extras) {
        furnitureView.showDecorationDetailsView(extras);
    }
}
