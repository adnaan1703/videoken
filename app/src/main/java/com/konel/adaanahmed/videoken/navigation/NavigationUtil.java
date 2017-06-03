package com.konel.adaanahmed.videoken.navigation;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.konel.adaanahmed.videoken.classroom.ClassRoomActivity;
import com.konel.adaanahmed.videoken.navigation.model.ClassRoomActivityNavigationModel;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaan.1703@gmail.com>
 * @version : 1.0.0
 * @since : 03 Jun 2017 2:22 PM
 */


public class NavigationUtil {

    public static final String KEY_DATA = "com.konel.adaanahmed.videoken.navigation.KEY_DATA";

    public static void startClassRoomActivity(Context context, String youtubeUrl) {
        context.startActivity(getClassRoomActivityIntent(context, youtubeUrl));
    }

    @NonNull
    private static Intent getClassRoomActivityIntent(Context context, String youtubeUrl) {
        ClassRoomActivityNavigationModel model = new ClassRoomActivityNavigationModel(youtubeUrl);
        Intent intent = new Intent(context, ClassRoomActivity.class);
        intent.putExtra(KEY_DATA, model);
        return intent;
    }

}
