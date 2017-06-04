package com.konel.adaanahmed.videoken.repository.loaders;

import android.content.Context;

import com.konel.adaanahmed.videoken.base.VkBaseAsyncTaskLoader;
import com.konel.adaanahmed.videoken.db.Lesson;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;


/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 04 Jun 2017 1:56 AM
 */


public class LessonsLoader extends VkBaseAsyncTaskLoader<ArrayList<Lesson>> {

    public LessonsLoader(Context context) {
        super(context);
    }

    @Override
    public ArrayList<Lesson> loadInBackground() {
        RealmResults<Lesson> results = Realm.getDefaultInstance()
                .where(Lesson.class)
                .findAll();

        ArrayList<Lesson> lessons = new ArrayList<>();
        for (Lesson lesson : results)
            lessons.add(lesson);
        return lessons;
    }
}
