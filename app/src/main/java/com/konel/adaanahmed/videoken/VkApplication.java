package com.konel.adaanahmed.videoken;

import android.app.Application;

import io.realm.Realm;


/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 04 Jun 2017 2:36 AM
 */


public class VkApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
