package com.konel.adaanahmed.videoken.base;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 04 Jun 2017 3:12 AM
 */


public interface BasePresenter<V> {
    void onStart(V view);

    void onStop();

    void setView(V mView);

    void clearView();
}
