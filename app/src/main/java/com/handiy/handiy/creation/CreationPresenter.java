package com.handiy.handiy.creation;

import android.content.Context;
import android.util.Log;

import com.handiy.handiy.R;
import com.handiy.handiy.data.CreationModel;
import com.handiy.handiy.data.source.remote.APIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by FitriFebriana on 7/28/2017.
 */

public class CreationPresenter implements CreationContract.Presenter {
    private final CreationContract.View creationView;
    private List<Object> creations = new ArrayList<>();

    public CreationPresenter(CreationContract.View creationView) {
        this.creationView = creationView;
    }

    @Override
    public void start() {
        loadCreation("idzarunianti");
    }

    @Override
    public void loadCreation(final String username) {
        creationView.showProgress();

        Context context = creationView.getContext();
        final String txtError = context.getResources().getString(R.string.error);

        APIService apiService = APIService.factory.create();
        Call<CreationModel.CreationListModel> call = apiService.getAllCreations(username);
        call.enqueue(new Callback<CreationModel.CreationListModel>() {
            @Override
            public void onResponse(Call<CreationModel.CreationListModel> call, Response<CreationModel.CreationListModel> response) {
                Log.d("testing", "response: "+response.message()+" *** "+response.code()+" *** "+response.isSuccessful() +
                        " *** " + response.raw().toString());
                creationView.hideProgress();
                if (response.isSuccessful()) {
                    creations.clear();
                    creations.addAll(response.body().getResult());
                    creationView.showCreationData(creations);

                }
            }

            @Override
            public void onFailure(Call<CreationModel.CreationListModel> call, Throwable t) {
                creations.clear();
                creationView.hideProgress();
                creationView.showErrorMessage(txtError);
                Log.d("test", t.getMessage());
            }
        });
    }


}
