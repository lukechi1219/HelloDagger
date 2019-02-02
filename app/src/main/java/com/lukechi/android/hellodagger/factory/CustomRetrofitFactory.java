package com.lukechi.android.hellodagger.factory;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.squareup.moshi.Moshi;
import com.tickaroo.tikxml.TikXml;
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * https://medium.com/@cdmunoz/offline-first-android-app-with-mvvm-dagger2-rxjava-livedata-and-room-part-3-af6eeafeb29b
 */
public class CustomRetrofitFactory {

    //    private final GsonConverterFactory gsonConverterFactory;
    private final MoshiConverterFactory moshiConverterFactory;
    private final TikXmlConverterFactory tikXmlConverterFactory;

    private final RxJava2CallAdapterFactory rxJava2CallAdapterFactory;

    private final OkHttpClient okHttpClient;

    private final OkHttpClient okHttpClientForXml;

    @Inject
    public CustomRetrofitFactory(@Named("OkHttpClientForXml") OkHttpClient okHttpClientForXml,
                                 OkHttpClient okHttpClient) {

//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .registerTypeAdapterFactory(AutoValueGsonFactory.create())
//                .create();

        Moshi moshi = new Moshi.Builder()
                .add(AutoValueMoshiFactory.create())
                .build();

        /**
         * TODO: TikXml didn't support TypeAdapterFactory? for inner class
         *
         * @GsonTypeAdapterFactory
         * AutoValueGsonAdapterFactoryProcessor
         */
        TikXml tikXml = new TikXml.Builder()
                .exceptionOnUnreadXml(false)
                .build();

//        this.gsonConverterFactory = GsonConverterFactory.create(gson);
        this.moshiConverterFactory = MoshiConverterFactory.create(moshi);
        this.tikXmlConverterFactory = TikXmlConverterFactory.create(tikXml);

        this.rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create();
        // ?? what the difference of createWithScheduler
//        this.rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());

        this.okHttpClientForXml = okHttpClientForXml;
        this.okHttpClient = okHttpClient;
    }

    /**
     * use moshiConverterFactory & okHttpClient
     */
    public Retrofit buildRetrofit(String baseUrl) {

        return new Retrofit.Builder()
//                .addConverterFactory(gsonConverterFactory)
                .addConverterFactory(moshiConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .build();
    }

    /**
     * use tikXmlConverterFactory and okHttpClientForXml
     */
    public Retrofit buildRetrofitForXml(String baseUrl) {

        return new Retrofit.Builder()
                .addConverterFactory(tikXmlConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .client(okHttpClientForXml)
                .baseUrl(baseUrl)
                .build();
    }
}
