package com.lukechi.android.hellodagger.factory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Inject;

public class CustomRetrofitFactory {

    private final GsonConverterFactory gsonConverterFactory;

    private final RxJava2CallAdapterFactory rxJava2CallAdapterFactory;

    private final OkHttpClient okHttpClient;

    @Inject
    public CustomRetrofitFactory(OkHttpClient okHttpClient) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .registerTypeAdapterFactory(AutoValueGsonFactory.create())
                .create();

        this.gsonConverterFactory = GsonConverterFactory.create(gson);

        this.rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create();

        this.okHttpClient = okHttpClient;
    }

    public Retrofit buildRetrofit(String baseUrl) {

        return new Retrofit.Builder()
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .build();
    }
}
