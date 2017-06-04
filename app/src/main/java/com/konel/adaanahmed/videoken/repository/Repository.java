package com.konel.adaanahmed.videoken.repository;

import android.support.annotation.NonNull;

import com.konel.adaanahmed.videoken.db.Lesson;
import com.konel.adaanahmed.videoken.db.Note;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 04 Jun 2017 2:23 AM
 */


public class Repository {

    public static Lesson insertOrUpdateLesson(@NonNull Realm realm,
                                              @NonNull Lesson lesson) {

        realm.beginTransaction();
        lesson = realm.copyToRealmOrUpdate(lesson);
        realm.commitTransaction();
        return lesson;
    }

    public static Lesson updateNoteInLesson(@NonNull Realm realm,
                                            @NonNull Lesson lesson,
                                            @NonNull Note note) {
        realm.beginTransaction();
        lesson.addNote(note);
        realm.commitTransaction();
        return lesson;
    }


    public static Lesson findLesson(@NonNull Realm realm, String videoId) {
        return realm.where(Lesson.class)
                .equalTo("videoId", videoId)
                .findFirst();
    }

    public static RealmResults<Lesson> findLessons(@NonNull Realm realm) {
        return realm.where(Lesson.class).findAll();
    }

}
