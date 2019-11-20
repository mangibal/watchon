package com.iqbalfauzi.watchon.data.repository.remote;

import android.os.Handler;

import com.iqbalfauzi.watchon.data.model.ResultEntity;
import com.iqbalfauzi.watchon.data.model.DataResponse;
import com.iqbalfauzi.watchon.helper.ApiClient;
import com.iqbalfauzi.watchon.utils.EspressoIdlingResource;
import com.iqbalfauzi.watchon.utils.EspressoIdlingResourceJava;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by Iqbal Fauzi on 16:29 11/11/19
 */
public class RemoteRepositoryJava {

    private static RemoteRepositoryJava INSTANCE;
    private final long SERVICE_LATENCY = 2000;
    private ApiClient apiClient;
    private android.os.Handler responseHandler = new Handler();

    public RemoteRepositoryJava(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public static RemoteRepositoryJava getInstance(ApiClient apiClient) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteRepositoryJava(apiClient);
        }
        return INSTANCE;
    }

    public interface GetMovieCallback {
        void onSuccess(List<ResultEntity> movieList);

        void onError();
    }

    public interface GetMovieDetailCallback {
        void onSuccess(ResultEntity movieDetail);

        void onError();
    }

    public interface GetTvShowsCallback {
        void onSuccess(List<ResultEntity> tvShowsList);

        void onError();
    }

    public interface GetTvShowDetailCallback {
        void onSuccess(ResultEntity tvShowDetail);

        void onError();
    }

    public void getMovies(GetMovieCallback getMovieCallback) {
        EspressoIdlingResourceJava.increment();
        responseHandler.postDelayed(() -> apiClient.create().getMovies().enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                getMovieCallback.onSuccess(response.body().getResults());
                EspressoIdlingResourceJava.decrement();
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Timber.d("Error " + t);
            }
        }), SERVICE_LATENCY);
    }

    public void getMovieDetail(String movieId, GetMovieDetailCallback getMovieDetailCallback) {
        EspressoIdlingResourceJava.increment();
        responseHandler.postDelayed(() -> apiClient.create().getMovieDetails(movieId).enqueue(new Callback<ResultEntity>() {
            @Override
            public void onResponse(Call<ResultEntity> call, Response<ResultEntity> response) {
                getMovieDetailCallback.onSuccess(response.body());
                EspressoIdlingResourceJava.decrement();
            }

            @Override
            public void onFailure(Call<ResultEntity> call, Throwable t) {
                Timber.d("Error " + t);
            }
        }), SERVICE_LATENCY);
    }

    public void getTvShows(GetTvShowsCallback getTvShowsCallback) {
        EspressoIdlingResourceJava.increment();
        responseHandler.postDelayed(() -> apiClient.create().getTvShows().enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                getTvShowsCallback.onSuccess(response.body().getResults());
                EspressoIdlingResourceJava.decrement();
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Timber.d("Error " + t);
            }
        }), SERVICE_LATENCY);
    }

    public void getTvShowDetail(String tvId, GetTvShowDetailCallback getTvShowDetailCallback) {
        EspressoIdlingResourceJava.increment();
        responseHandler.postDelayed(() -> apiClient.create().getTvShowDetail(tvId).enqueue(new Callback<ResultEntity>() {
            @Override
            public void onResponse(Call<ResultEntity> call, Response<ResultEntity> response) {
                getTvShowDetailCallback.onSuccess(response.body());
                EspressoIdlingResourceJava.decrement();
            }

            @Override
            public void onFailure(Call<ResultEntity> call, Throwable t) {
                Timber.d("Error " + t);
            }
        }), SERVICE_LATENCY);
    }

}
