package com.moudjames23.coronanews.networkapi;


import android.content.Context;
import android.util.Log;

import com.moudjames23.coronanews.R;
import com.moudjames23.coronanews.app.Pref;
import com.moudjames23.coronanews.model.ConfirmationResponse;
import com.moudjames23.coronanews.model.Home;
import com.moudjames23.coronanews.model.Stats;
import com.moudjames23.coronanews.model.Verification;
import com.moudjames23.coronanews.model.Video;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Moudjames on 18/12/2017.
 */
public class WebService {

    private Utils app;
    private NetworkCallBack callBack;

    private final String TAG = "webservice";

    private Pref pref;


    public WebService(Context context, NetworkCallBack callBack) {
        this.app = new Utils(context);
        this.callBack = callBack;

        pref = new Pref(app.getContext());
    }

    public WebService(Context context) {
        this.app = new Utils(context);
    }





    public void home()
    {
        if(app.isOnline())
        {
            callBack.showLoading();
            Call<Home>  call = app.setupNetworkAPI().home();
            call.enqueue(new Callback<Home>() {
                @Override
                public void onResponse(Call<Home> call, Response<Home> response) {
                    callBack.hideLoading();
                    if(response != null && response.body() != null)
                        callBack.onResponse(response.body());
                }

                @Override
                public void onFailure(Call<Home> call, Throwable t) {
                    callBack.hideLoading();

                    callBack.onFailure(t.getMessage());
                }
            });
        }
    }

    public void sendPhoneNumber(String phone)
    {
        if(app.isOnline())
        {
            Log.d(TAG, "sendPhoneNumber: test 1");
            callBack.showLoading();

            Call<Verification> call = app.setupNetworkAPI().sendPhoneNumber(phone);
            call.enqueue(new Callback<Verification>() {
                @Override
                public void onResponse(Call<Verification> call, Response<Verification> response) {
                    callBack.hideLoading();

                    Log.d(TAG, "onResponse: success");
                    if(response != null && response.body() != null)
                        callBack.onResponse(response.body());
                }

                @Override
                public void onFailure(Call<Verification> call, Throwable t) {
                    callBack.hideLoading();

                    Log.d(TAG, "onFailure: " +t.getMessage());

                    callBack.onFailure(app.getContext().getString(R.string.network_error));
                }
            });

            Log.d(TAG, "sendPhoneNumber: test 3");
        }
        else
            callBack.noInternetConnexion();
    }

    public void confirmation(String phone, String code)
    {
        if(app.isOnline())
        {
            callBack.showLoading();

            Call<ConfirmationResponse> call = app.setupNetworkAPI().confirmationCode(phone, code);
            call.enqueue(new Callback<ConfirmationResponse>() {
                @Override
                public void onResponse(Call<ConfirmationResponse> call, Response<ConfirmationResponse> response) {

                    callBack.hideLoading();

                    if(response != null && response.body() != null)
                        callBack.onResponse(response.body());
                }

                @Override
                public void onFailure(Call<ConfirmationResponse> call, Throwable t) {
                    callBack.hideLoading();

                    callBack.onFailure(app.getContext().getString(R.string.network_error));
                }
            });
        }

    }

}
