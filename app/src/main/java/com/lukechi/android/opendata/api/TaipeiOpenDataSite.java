package com.lukechi.android.opendata.api;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Inject;

public class TaipeiOpenDataSite {

    /*
     * for Taipei City Open Data traffic_realtime
     */
    private final static String BASE_URL = "https://tcgbusfs.blob.core.windows.net/";
    // https://tcgbusfs.blob.core.windows.net/

    private final OkHttpClient okHttpClient;

    private static Retrofit retrofit = null;

    @Inject
    public TaipeiOpenDataSite(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    // need context arg ??
    public Retrofit getClient(Context context) {

        if (retrofit == null) {

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
