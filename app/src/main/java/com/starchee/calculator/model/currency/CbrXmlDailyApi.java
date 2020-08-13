package com.starchee.calculator.model.currency;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface CbrXmlDailyApi {
    @GET("daily_json.js")
    Single<ServerResponse> getAllCurrency();
}
