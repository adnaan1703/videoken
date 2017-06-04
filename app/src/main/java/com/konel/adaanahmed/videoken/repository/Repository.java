package com.konel.adaanahmed.videoken.repository;

import android.support.annotation.NonNull;

import com.konel.adaanahmed.videoken.db.Lesson;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 04 Jun 2017 2:23 AM
 */


public class Repository {

    public static void insertOrUpdateLesson(@NonNull final Lesson lesson,
                                            @NonNull Realm.Transaction.OnSuccess onSuccess,
                                            @NonNull Realm.Transaction.OnError onError) {

        Realm.getDefaultInstance().executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(lesson);
            }
        }, onSuccess, onError);
    }

    public static void insertOrUpdateLessons(@NonNull final ArrayList<Lesson> lessons,
                                             @NonNull Realm.Transaction.OnSuccess onSuccess,
                                             @NonNull Realm.Transaction.OnError onError) {
        Realm.getDefaultInstance().executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(lessons);
            }
        }, onSuccess, onError);
    }

    public static Lesson findLesson(String videoId) {
        return Realm.getDefaultInstance()
                .where(Lesson.class)
                .equalTo("videoId", videoId)
                .findFirst();

    }

    public void findLessons() {
        RealmResults<Lesson> lessons = Realm.getDefaultInstance()
                .where(Lesson.class)
                .findAll();
    }

}
