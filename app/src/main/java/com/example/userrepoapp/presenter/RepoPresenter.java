package com.example.userrepoapp.presenter;

import android.util.Log;

import com.example.userrepoapp.AppContract;
import com.example.userrepoapp.model.RepoItem;
import com.example.userrepoapp.network.ApiClient;
import com.example.userrepoapp.network.ApiClientInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoPresenter implements AppContract.Presenter {

    public RepoPresenter(AppContract.View mView) {
        this.mView = mView;
    }

    private static final String TAG = "UserRepoApp";
    AppContract.View mView;
    ApiClientInterface apiService;

    @Override
    public void fetchRepos(String userID) {

        apiService = ApiClient.getClient().create(ApiClientInterface.class);

        Call<List<RepoItem>> call = apiService.getUserRepository(userID);
        call.enqueue(new Callback<List<RepoItem>>() {
            @Override
            public void onResponse(Call<List<RepoItem>> call, Response<List<RepoItem>> response) {
                List<RepoItem> repoList = response.body();
                for (RepoItem rep :
                        repoList) {
                    Log.i(TAG, "Presenter | onResponse: repository name: " + rep.getName());
                }
                mView.showRepos(repoList);
            }

            @Override
            public void onFailure(Call<List<RepoItem>> call, Throwable t) {

            }
        });

    }
}
