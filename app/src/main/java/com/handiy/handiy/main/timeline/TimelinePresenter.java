package com.handiy.handiy.main.timeline;

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

public class TimelinePresenter implements TimelineContract.Presenter{

    private final TimelineContract.View timelineView;
    private List<TutorialModel> timelines = new ArrayList<>();

    public TimelinePresenter(TimelineContract.View timelineView) {
        this.timelineView = timelineView;
    }


    @Override
    public void start() {
        loadTimelineData();
    }

    @Override
    public void loadTimelineData() {
        final Context context = timelineView.getContext();
        final String txtError = context.getResources().getString(R.string.error);

        APIService apiService = APIService.factory.create();
        Call<TutorialModel.TutorialListModel> call = apiService.getAllTutorials();
        call.enqueue(new Callback<TutorialModel.TutorialListModel>() {

            @Override
            public void onResponse(Call<TutorialModel.TutorialListModel> call, Response<TutorialModel.TutorialListModel> response) {
                Log.d("testing", "response: "+response.message()+" *** "+response.code()+" *** "+response.isSuccessful() +
                        " *** " + response.raw().toString());
                timelineView.hideProgress();
                if (response.isSuccessful()) {
                    timelines.clear();
                    timelines.addAll(response.body().getResult());
                    timelineView.showTimelineData(timelines);

                }
            }

            @Override
            public void onFailure(Call<TutorialModel.TutorialListModel> call, Throwable t) {
                timelines.clear();
                timelineView.hideProgress();
                timelineView.showError(txtError);
            }
        });
    }

    @Override
    public void openTimelineDetails(String extras) {
        timelineView.showTimelineDetailsView(extras);
    }
}
