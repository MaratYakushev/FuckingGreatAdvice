package com.ulsu.marat.fuckinggreatadvice.net;
import com.ulsu.marat.fuckinggreatadvice.model.FAdvice;
import com.ulsu.marat.fuckinggreatadvice.utils.Const;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetAdvice {
    @GET("api/random")
    Call<FAdvice> getRandomAdvice();
}
