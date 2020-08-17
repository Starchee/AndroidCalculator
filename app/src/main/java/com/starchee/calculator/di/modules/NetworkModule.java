package com.starchee.calculator.di.modules;

import android.app.Application;

import com.starchee.calculator.App;
import com.starchee.calculator.model.currency.NetworkService;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static final long cacheSize = 5 * 1024 * 1024;
    private static final String BASE_URL = "https://www.cbr-xml-daily.ru/";
    private static final String HEADER_CACHE_CONTROL = "Cache-Control";
    private static final String HEADER_PRAGMA = "Pragma";

    @Singleton
    @Provides
    Cache provideCache(Application application){
        File httpCacheDirectory = new File(application.getCacheDir(),"offlineCache");
        return new Cache(httpCacheDirectory, cacheSize);
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(Application application, Cache cache){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Interceptor offlineInterceptor = chain -> {
            Request request = chain.request();
            if (!((App)application).isInternetAvailable()) {
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxStale(7, TimeUnit.DAYS)
                        .build();

                request.newBuilder()
                        .removeHeader(HEADER_PRAGMA)
                        .removeHeader(HEADER_CACHE_CONTROL)
                        .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                        .build();
            }
            return chain.proceed(request);
        };


        Interceptor onlineInterceptor = chain -> {
            CacheControl cacheControl = new CacheControl.Builder()
                    .maxAge(1, TimeUnit.DAYS)
                    .build();
            okhttp3.Response response = chain.proceed(chain.request());

            return response.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                    .build();
        };


        return new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(offlineInterceptor)
                .addNetworkInterceptor(onlineInterceptor)
                .build();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    @Singleton
    @Provides
    NetworkService provideNetworkService(Retrofit retrofit) {
        return new NetworkService(retrofit);
    }
}
