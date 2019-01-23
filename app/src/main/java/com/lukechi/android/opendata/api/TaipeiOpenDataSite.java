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

public class TaipeiOpenDataSite {

    /*
     * for Taipei City Open Data traffic_realtime
     */
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

                        String contentType = res.headers().get("Content-Type");
                        System.out.println("contentType: " + contentType);

                        String x_ms_meta_contentEncoding = res.headers().get("x-ms-meta-contentEncoding");
                        String contentEncoding = res.headers().get("Content-Encoding");
                        System.out.println("x_ms_meta_contentEncoding: " + x_ms_meta_contentEncoding);
                        System.out.println("contentEncoding: " + contentEncoding);

                        // hack for Taipei OpenData
                        boolean needAddContentTypeJson = (contentType == null || !contentType.equals("application/json"));
                        // hack for Taipei OpenData
                        boolean needAddContentEncodingGzip = (x_ms_meta_contentEncoding != null && x_ms_meta_contentEncoding.contains("gzip")
                                && (contentEncoding == null || !contentEncoding.contains("gzip")));

                        if (!needAddContentTypeJson && !needAddContentEncodingGzip) {
                            return res;
                        }
                        Response.Builder resBuilder = res.newBuilder();

                        if (needAddContentTypeJson) {
                            resBuilder = resBuilder.header("Content-Type", "application/json");
                        }
                        if (needAddContentEncodingGzip) {
                            resBuilder = resBuilder.header("Content-Encoding", "gzip");
                        }
                        return resBuilder.build();
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
