package com.konel.adaanahmed.videoken;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.realm.RealmList;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaan.1703@gmail.com>
 * @version : 1.0.0
 * @since : 03 Jun 2017 3:53 PM
 */


public class CodeUtil {
    public static String getYoutubeIdFromUrl(@NonNull String url) {

        String videoId = null;
        String pattern = "(?<=watch\\?v=|/videos/|embed/)[^#&?]*";

        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(url);

        if (matcher.find()) {
            videoId = matcher.group();
        }
        return videoId;
    }

    public static boolean isArrayEmpty(ArrayList arrayList) {
        return arrayList == null || arrayList.size() == 0;
    }

    public static boolean isArrayEmpty(RealmList realmList) {
        return realmList == null || realmList.size() == 0;
    }

    public static String millisToString(int millis) {
        StringBuilder builder = new StringBuilder();
        long hh = TimeUnit.MILLISECONDS.toHours(millis);
        long mm = TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1);
        long ss = TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1);

        if (hh == 0 && mm == 0) {
            builder.append(ss).append(" s");
        } else if (hh == 0) {
            builder.append(mm).append(" m ").append(ss).append(" s");
        } else {
            builder.append(hh).append(" h ").append(mm).append(" m ").append(ss).append(" s");
        }
        return builder.toString();
    }

}
