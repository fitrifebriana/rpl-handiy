package com.handiy.handiy.detail;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.handiy.handiy.R;
import com.handiy.handiy.data.BookmarkModel;
import com.handiy.handiy.data.CreationModel;
import com.handiy.handiy.data.TutorialModel;
import com.handiy.handiy.data.source.remote.APIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by FitriFebriana on 5/28/2017.
 */

public class DetailPresenter implements DetailContract.Presenter{

    private final DetailContract.View detailsView;
    private List<Object> details = new ArrayList<>();
    private List<Object> creations = new ArrayList<>();

    public DetailPresenter(DetailContract.View detailsView) {
        this.detailsView = detailsView;
    }

    @Override
    public void start() {
    }


    @Override
    public void loadDetails(final String tutorialId) {
        final Context context = detailsView.getContext();
        final String txtError = context.getResources().getString(R.string.error);

        APIService apiService = APIService.factory.create();
        Call<TutorialModel.TutorialListModel.StepsListModel> call = apiService.getDetailTutorial(tutorialId);
        call.enqueue(new Callback<TutorialModel.TutorialListModel.StepsListModel>() {
            @Override
            public void onResponse(Call<TutorialModel.TutorialListModel.StepsListModel> call, Response<TutorialModel.TutorialListModel.StepsListModel> response) {
                Log.d("testing", "response: "+response.message()+" *** "+response.code()+" *** "+response.isSuccessful() +
                        " *** " + response.raw().toString());
                detailsView.hideProgress();
                if (response.isSuccessful()) {
                    details.clear();
                    details.addAll(response.body().getSteps());
                    detailsView.showDetailsData(details);

                }
            }

            @Override
            public void onFailure(Call<TutorialModel.TutorialListModel.StepsListModel> call, Throwable t) {
                details.clear();
                detailsView.hideProgress();
                detailsView.showError(txtError);
            }
        });
    }

    @Override
    public void postBookmark(final String username, String tutorialId) {
        final Context context = detailsView.getContext();

        APIService apiService = APIService.factory.create();
        Call<BookmarkModel.BookmarkListModel> call =  apiService.postBookmark(username, tutorialId);
        call.enqueue(new Callback<BookmarkModel.BookmarkListModel>() {
            @Override
            public void onResponse(Call<BookmarkModel.BookmarkListModel> call, Response<BookmarkModel.BookmarkListModel> response) {
                Log.d("testing", "response: "+response.message()+" *** "+response.code()+" *** "+response.isSuccessful() +
                        " *** " + response.raw().toString());
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Bookmarked", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BookmarkModel.BookmarkListModel> call, Throwable t) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                Log.d("test", t.getMessage());
            }
        });
    }

    @Override
    public void deleteBookmark(String username, String bookmarks_id) {
        final Context context = detailsView.getContext();

        APIService apiService = APIService.factory.create();
        Call<BookmarkModel.BookmarkListModel> call = apiService.deleteBookmark(username, bookmarks_id);
        call.enqueue(new Callback<BookmarkModel.BookmarkListModel>() {

            @Override
            public void onResponse(Call<BookmarkModel.BookmarkListModel> call, Response<BookmarkModel.BookmarkListModel> response) {
                Log.d("testing", "response: "+response.message()+" *** "+response.code()+" *** "+response.isSuccessful() +
                        " *** " + response.raw().toString());
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Bookmark Unchecked", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BookmarkModel.BookmarkListModel> call, Throwable t) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                Log.d("test", t.getMessage());
            }
        });
    }

    @Override
    public void loadCreations(String tutorialId) {
        final Context context = detailsView.getContext();
        final String txtError = context.getResources().getString(R.string.error);

        APIService apiService = APIService.factory.create();
        Call<CreationModel.CreationListModel> call = apiService.getAllCreationsPerTutorial(tutorialId);
        call.enqueue(new Callback<CreationModel.CreationListModel>() {
            @Override
            public void onResponse(Call<CreationModel.CreationListModel> call, Response<CreationModel.CreationListModel> response) {
                Log.d("testing", "response: "+response.message()+" *** "+response.code()+" *** "+response.isSuccessful() +
                        " *** " + response.raw().toString());
                detailsView.hideProgress();
                if (response.isSuccessful()) {
                    creations.clear();
                    creations.addAll(response.body().getResult());
                    detailsView.showCreationsData(creations);

                }
            }

            @Override
            public void onFailure(Call<CreationModel.CreationListModel> call, Throwable t) {
                creations.clear();
                detailsView.hideProgress();
                detailsView.showError(txtError);
            }
        });
    }

    @Override
    public void postCreation(String username, String tutorialId) {
        final Context context = detailsView.getContext();
        final String txtError = context.getResources().getString(R.string.error);

        APIService apiService = APIService.factory.create();
        Call<CreationModel.CreationListModel> call = apiService.postCreation(username, tutorialId);
        call.enqueue(new Callback<CreationModel.CreationListModel>() {
            @Override
            public void onResponse(Call<CreationModel.CreationListModel> call, Response<CreationModel.CreationListModel> response) {
                Log.d("testing", "response: "+response.message()+" *** "+response.code()+" *** "+response.isSuccessful() +
                        " *** " + response.raw().toString());
                detailsView.hideProgress();
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CreationModel.CreationListModel> call, Throwable t) {
                detailsView.showError(txtError);
            }
        });
    }

    @Override
    public void deleteCreation(String tutorialId, String username) {
        final Context context = detailsView.getContext();
        final String txtError = context.getResources().getString(R.string.error);

        APIService apiService = APIService.factory.create();
        Call<CreationModel.CreationListModel> call = apiService.deleteCreation(tutorialId, username);
        call.enqueue(new Callback<CreationModel.CreationListModel>() {
            @Override
            public void onResponse(Call<CreationModel.CreationListModel> call, Response<CreationModel.CreationListModel> response) {
                Log.d("testing", "response: "+response.message()+" *** "+response.code()+" *** "+response.isSuccessful() +
                        " *** " + response.raw().toString());
                detailsView.hideProgress();
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CreationModel.CreationListModel> call, Throwable t) {
                detailsView.showError(txtError);
            }
        });
    }
}
