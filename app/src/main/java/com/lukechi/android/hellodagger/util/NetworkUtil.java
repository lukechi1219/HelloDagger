package com.lukechi.android.hellodagger.util;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkUtil {

    public static OkHttpClient buildOkHttpClient() {

        return new OkHttpClient().newBuilder()
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
    }
}
