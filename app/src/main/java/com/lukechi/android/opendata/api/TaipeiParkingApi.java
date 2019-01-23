package com.lukechi.android.opendata.api;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TaipeiParkingApi {

    private final static String BASE_URL = "https://tcgbusfs.blob.core.windows.net/";
    // https://tcgbusfs.blob.core.windows.net/

    private static Retrofit retrofit = null;

    public static Retrofit getClient(Context context) {

        if (retrofit == null) {

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .addNetworkInterceptor((Interceptor.Chain chain) -> {
                        Request req = chain.request();

//                        Headers.Builder headersBuilder = req.headers().newBuilder();
//                        String credential = Credentials.basic(...);
//                        headersBuilder.set("Authorization", credential);
//                        Response res = chain.proceed(req.newBuilder().headers(headersBuilder.build()).build());

                        Response res = chain.proceed(req.newBuilder().build());

                        return res.newBuilder().header("Content-Encoding", "gzip").header("Content-Type", "application/json").build();
                    })
                    .build();

            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    //    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
