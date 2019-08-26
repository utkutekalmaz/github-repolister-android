package com.example.userrepoapp;

import com.example.userrepoapp.model.RepoItem;

import java.util.List;

public interface AppContract {

    interface View {

        void showRepos(List<RepoItem> movieList);

    }

    interface Presenter {
        //void start();
        void fetchRepos(String userID);
    }
}
