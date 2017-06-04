package com.konel.adaanahmed.videoken;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaan.1703@gmail.com>
 * @version : 1.0.0
 * @since : 03 Jun 2017 3:53 PM
 */


public class CodeUtil {
    public static String getYoutubeIdFromUrl(@NonNull String url) {

        String videoId = null;
        String pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";

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

}
