package com.example.userrepoapp.network;

import com.example.userrepoapp.model.RepoItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiClientInterface {
    @GET("users/{userName}/repos")
    Call<List<RepoItem>> getUserRepository(@Path("userName") String userName);
}
