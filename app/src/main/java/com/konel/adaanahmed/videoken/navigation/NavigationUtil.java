package com.konel.adaanahmed.videoken.navigation;

import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;

import com.konel.adaanahmed.videoken.base.VkBaseActivity;
import com.konel.adaanahmed.videoken.classroom.ClassRoomActivity;
import com.konel.adaanahmed.videoken.navigation.model.ClassRoomActivityNavigationModel;

import java.util.Locale;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaan.1703@gmail.com>
 * @version : 1.0.0
 * @since : 03 Jun 2017 2:22 PM
 */


public class NavigationUtil {

    public static final String KEY_DATA = "com.konel.adaanahmed.videoken.navigation.KEY_DATA";

    public static final int SPEECH_TO_TEXT_ACTIVITY_RESULT_CODE = 0x00f;

    public static void startClassRoomActivity(@NonNull Context context,
                                              @NonNull String videoId,
                                              int startTime) {
        context.startActivity(getClassRoomActivityIntent(context, videoId, startTime));
    }

    public static void startClassRoomActivity(@NonNull Context context, @NonNull String videoId) {
        context.startActivity(getClassRoomActivityIntent(context, videoId, 0));
    }

    @NonNull
    private static Intent getClassRoomActivityIntent(Context context, String videoId, int startTime) {
        ClassRoomActivityNavigationModel model = new ClassRoomActivityNavigationModel(videoId, startTime);
        Intent intent = new Intent(context, ClassRoomActivity.class);
        intent.putExtra(KEY_DATA, model);
        return intent;
    }

    public static void startSpeechToTextActivityForResult(@NonNull VkBaseActivity activity, @NonNull String promptMessage) {
        activity.startActivityForResult(getSpeechToTextActivityIntent(promptMessage),
                SPEECH_TO_TEXT_ACTIVITY_RESULT_CODE);
    }

    @NonNull
    private static Intent getSpeechToTextActivityIntent(@NonNull String promptMessage) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, promptMessage);
        return intent;
    }


}
