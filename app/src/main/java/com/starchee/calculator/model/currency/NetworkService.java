package com.starchee.calculator.model.currency;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class NetworkService {

    private static NetworkService mInstance;
    private static final String BASE_URL = "https://www.cbr-xml-daily.ru/";
    private Retrofit retrofit;

    @Inject
    public NetworkService(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public CbrXmlDailyApi getJSONApi() {
        return retrofit.create(CbrXmlDailyApi.class);
    }
}
