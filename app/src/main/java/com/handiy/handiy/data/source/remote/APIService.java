package com.handiy.handiy.data.source.remote;

import android.database.Observable;

import com.handiy.handiy.data.BookmarkModel;
import com.handiy.handiy.data.CreationModel;
import com.handiy.handiy.data.TutorialModel;
import com.handiy.handiy.data.UserModel;
import com.handiy.handiy.util.AppConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by FitriFebriana on 5/28/2017.
 */

public interface APIService {
    String BASE_URL = AppConstants.APIUrl.BASE_URL;

    @GET("/tutorial")
    Call<TutorialModel.TutorialListModel> getAllTutorials(@Query("category_id") String category_id);

    @GET("/tutorial")
    Call<TutorialModel.TutorialListModel> getAllTutorials();

    @GET("/tutorial/{id}")
    Call<TutorialModel.TutorialListModel.StepsListModel> getDetailTutorial(@Path("id") String id);

    @GET("/{username}/bookmarks")
    Call<BookmarkModel.BookmarkListModel> getAllBookmarks(@Path("username") String username);

    @GET("/{username}/creations")
    Call<CreationModel.CreationListModel> getAllCreations(@Path("username") String username);

    @Multipart
    @POST("/{username}/bookmarks")
    Observable<BookmarkModel.BookmarkListModel> postBookmark(@Path("username") String username, @Body TutorialModel tutorial);

    @FormUrlEncoded
    @POST("/users")
    Call<UserModel> postSignIn(@Field("username") String username,
                                                   @Field("email") String email,
                                                   @Field("name") String name);

    class factory {
        public static APIService create() {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.connectTimeout(10, TimeUnit.SECONDS);
            builder.writeTimeout(10, TimeUnit.SECONDS);

            OkHttpClient client = builder.build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit.create(APIService.class);
        }
    }

}
