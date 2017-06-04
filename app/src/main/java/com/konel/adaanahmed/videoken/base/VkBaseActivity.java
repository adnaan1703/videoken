package com.konel.adaanahmed.videoken.base;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaan.1703@gmail.com>
 * @version : 1.0.0
 * @since : 03 Jun 2017 12:34 PM
 */


public abstract class VkBaseActivity extends AppCompatActivity {

    public void showToast(String str, boolean showLong) {
        int duration = Toast.LENGTH_SHORT;
        if (showLong)
            duration = Toast.LENGTH_LONG;
        Toast.makeText(getApplicationContext(), str, duration).show();
    }

    public void showToast(String str) {
        showToast(str, false);
    }

}
