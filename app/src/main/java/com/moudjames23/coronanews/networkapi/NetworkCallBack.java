package com.moudjames23.coronanews.networkapi;

import java.util.List;

/**
 * Created by Moudjames23 on 6/6/2017.
 */
public interface NetworkCallBack<T>{

     void showLoading();
     void hideLoading();

     void noInternetConnexion();

     void noItem();

     void onFailure(String errorMessage);
     void onResponse(List<T> data);
     void onResponse(T data);
}
