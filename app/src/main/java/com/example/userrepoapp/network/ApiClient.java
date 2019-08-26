package com.example.userrepoapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.example.userrepoapp.constants.AppConstant.*;

public class ApiClient {

    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}