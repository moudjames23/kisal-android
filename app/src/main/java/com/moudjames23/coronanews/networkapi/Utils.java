package com.moudjames23.coronanews.networkapi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import com.moudjames23.coronanews.app.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Moudjames on 18/12/2017.
 */
public class Utils {

    private Context context;
    private NetworkAPI networkAPI;

    public Context getContext() {
        return context;
    }

    private int READ_TIMEOUT = 20;
    private int CONNECT_TIMEOUT = 30;

    public Utils(Context context)
    {
        this.context = context;
        //setupNetworkAPI();
    }


    /**
     * Verifie s'il y a une source de connexion
     * @return un boolean si ou
     */
    public boolean isOnline()
    {
        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        return  info != null && info.isConnectedOrConnecting();
    }

    /**
     * Verifie s'il y a une source de connexion
     * @return un boolean si ou
     */
    public static boolean isOnline(Context context)
    {
        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        return  info != null && info.isConnectedOrConnecting();
    }

    /**
     * initialise retrofit
     * @return une instance de NetworkAPI
     */
    public NetworkAPI setupNetworkAPI()
    {
        if(networkAPI == null)
        {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .build();


            networkAPI = new Retrofit.Builder()
                    .baseUrl(Constant.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                    .create(NetworkAPI.class);

        }

        return networkAPI;
    }



}
