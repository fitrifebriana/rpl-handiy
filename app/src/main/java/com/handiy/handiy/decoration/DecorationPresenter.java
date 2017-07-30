package com.handiy.handiy.decoration;

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

public class DecorationPresenter implements DecorationContract.Presenter{
    private final DecorationContract.View decorationView;
    private List<Object> decorations = new ArrayList<>();

    public DecorationPresenter(DecorationContract.View decorationView) {
        this.decorationView = decorationView;
    }

    @Override
    public void loadDecorationData(final String categoryId) {
        decorationView.showProgress();

        Context context = decorationView.getContext();
        final String txtError = context.getResources().getString(R.string.error);

        APIService apiService = APIService.factory.create();
        Call<TutorialModel.TutorialListModel> call = apiService.getAllTutorials(categoryId);
        call.enqueue(new Callback<TutorialModel.TutorialListModel>() {
            @Override
            public void onResponse(Call<TutorialModel.TutorialListModel> call, Response<TutorialModel.TutorialListModel> response) {
                if (response.isSuccessful()) {
                    Log.d("testing", "response: "+response.message()+" *** "+response.code()+" *** "+response.isSuccessful() +
                            " *** " + response.raw().toString());
                    decorationView.hideProgress();
                    if (response.isSuccessful()) {
                        decorations.clear();
                        decorations.addAll(response.body().getResult());
                        decorationView.showDecorationData(decorations, "1");

                    }
                }
            }

            @Override
            public void onFailure(Call<TutorialModel.TutorialListModel> call, Throwable t) {
                decorations.clear();
                decorationView.hideProgress();
                decorationView.showError(txtError);
            }
        });
    }

    @Override
    public void openDecorationDetails(String extras) {
        decorationView.showDecorationDetailsView(extras);
    }

    @Override
    public void start() {
        loadDecorationData("1");
    }
}
