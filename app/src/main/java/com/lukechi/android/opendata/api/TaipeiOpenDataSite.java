package com.lukechi.android.opendata.api;

import android.content.Context;
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

    private final GsonConverterFactory gsonConverterFactory;
    private final RxJava2CallAdapterFactory rxJava2CallAdapterFactory;
    private final OkHttpClient okHttpClient;

    private static Retrofit retrofit = null;

    @Inject
    public TaipeiOpenDataSite(GsonConverterFactory gsonConverterFactory, RxJava2CallAdapterFactory rxJava2CallAdapterFactory, OkHttpClient okHttpClient) {
        this.gsonConverterFactory = gsonConverterFactory;
        this.rxJava2CallAdapterFactory = rxJava2CallAdapterFactory;
        this.okHttpClient = okHttpClient;
    }

    // need context arg ??
    public synchronized Retrofit getClient(Context context) {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJava2CallAdapterFactory)
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .build();
        }
        return retrofit;
    }
}
