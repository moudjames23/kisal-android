package com.moudjames23.coronanews.networkapi;


import com.moudjames23.coronanews.model.ConfirmationResponse;
import com.moudjames23.coronanews.model.Home;
import com.moudjames23.coronanews.model.Stats;
import com.moudjames23.coronanews.model.Verification;
import com.moudjames23.coronanews.model.Video;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by Moud on 29-Sep-18.
 */
public interface NetworkAPI {


    @GET("api/home")
    Call<Home> home();

    @POST("/api/send-number")
    @FormUrlEncoded
    Call<Verification> sendPhoneNumber(@Field("phone") String phone);

    @POST("/api/code-validation")
    @FormUrlEncoded
    Call<ConfirmationResponse> confirmationCode(@Field("phone") String phone, @Field("code_confirmation") String code);
}
