package com.handiy.handiy.wear;

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

public class WearPresenter implements WearContract.Presenter{
    private final WearContract.View wearView;
    private List<Object> wears = new ArrayList<>();

    public WearPresenter(WearContract.View wearView) {
        this.wearView = wearView;
    }


    @Override
    public void start() {
        loadWearData("3");
    }

    @Override
    public void loadWearData(final String categoryId) {
        final Context context = wearView.getContext();
        final String txtError = context.getResources().getString(R.string.error);

        APIService apiService = APIService.factory.create();
        Call<TutorialModel.TutorialListModel> call = apiService.getAllTutorials(categoryId);
        call.enqueue(new Callback<TutorialModel.TutorialListModel>() {

            @Override
            public void onResponse(Call<TutorialModel.TutorialListModel> call, Response<TutorialModel.TutorialListModel> response) {
                Log.d("testing", "response: "+response.message()+" *** "+response.code()+" *** "+response.isSuccessful() +
                        " *** " + response.raw().toString());
                wearView.hideProgress();
                if (response.isSuccessful()) {
                    wears.clear();
                    wears.addAll(response.body().getResult());
                    wearView.showWearData(wears, "3");
                }
            }

            @Override
            public void onFailure(Call<TutorialModel.TutorialListModel> call, Throwable t) {
                wears.clear();
                wearView.hideProgress();
                wearView.showError(txtError);
            }
        });
    }

    @Override
    public void openWearDetails(String extras) {
        wearView.showWearDetailsView(extras);
    }

}
