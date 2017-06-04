package com.konel.adaanahmed.videoken.home;

import android.support.annotation.NonNull;

import com.konel.adaanahmed.videoken.base.VkWeakReference;
import com.konel.adaanahmed.videoken.db.Lesson;
import com.konel.adaanahmed.videoken.navigation.NavigationUtil;
import com.konel.adaanahmed.videoken.repository.Repository;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 04 Jun 2017 3:19 AM
 */


class HomeActivityPresenter implements HomeActivityContract.Presenter {

    private VkWeakReference<HomeActivityContract.View> view;
    private Realm realm = Realm.getDefaultInstance();

    HomeActivityPresenter(@NonNull HomeActivityContract.View view) {
        setView(view);
    }

    public void setView(HomeActivityContract.View mView) {
        this.view = new VkWeakReference<>(mView);
    }

    @Override
    public void clearView() {
        this.view.clear();
    }

    @Override
    public void onStart(HomeActivityContract.View view) {
        if (this.view.get() == null)
            setView(view);
        getLessons();
    }

    @Override
    public void onStop() {
        clearView();
        if (realm != null && !realm.isClosed())
            realm.close();
    }

    @Override
    public void getLessons() {
        RealmResults<Lesson> results = Repository.findLessons(realm);
        if (results.size() == 0)
            view.getNonNull().showNoLesson();
        else
            view.getNonNull().showLessons(transMutateToArrayList(results));
    }

    private ArrayList<Lesson> transMutateToArrayList(RealmResults<Lesson> results) {
        ArrayList<Lesson> lessonArrayList = new ArrayList<>();
        for (Lesson lesson : results)
            lessonArrayList.add(lesson);
        return lessonArrayList;
    }

    @Override
    public void onQuerySubmitted(@NonNull String videoId) {
        NavigationUtil.startClassRoomActivity(view.getNonNull().getViewContext(), videoId);
    }

    @Override
    public void onNoteSelected(String videoId, int startTime) {
        NavigationUtil.startClassRoomActivity(view.getNonNull().getViewContext(), videoId, startTime);
    }
}
