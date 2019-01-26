package com.lukechi.android.opendata.api;

import android.content.Context;
import com.lukechi.android.hellodagger.factory.CustomRetrofitFactory;
import retrofit2.Retrofit;

import javax.inject.Inject;

public class TaipeiOpenDataSite {

    /*
     * for Taipei City Open Data traffic_realtime
     */
    private final static String BASE_URL = "https://tcgbusfs.blob.core.windows.net/";
    // https://tcgbusfs.blob.core.windows.net/

    private final CustomRetrofitFactory customRetrofitFactory;

    private static Retrofit retrofit = null;
    private static Retrofit retrofitForXml = null;

    @Inject
    public TaipeiOpenDataSite(CustomRetrofitFactory customRetrofitFactory) {
        this.customRetrofitFactory = customRetrofitFactory;
    }

    // need context arg ??
    public synchronized Retrofit getClient(Context context) {
        if (retrofit == null) {
            retrofit = customRetrofitFactory.buildRetrofit(BASE_URL);
        }
        return retrofit;
    }

    // need context arg ??
    public synchronized Retrofit getClientForXml(Context context) {
        if (retrofitForXml == null) {
            retrofitForXml = customRetrofitFactory.buildRetrofitForXml(BASE_URL);
        }
        return retrofitForXml;
    }
}
