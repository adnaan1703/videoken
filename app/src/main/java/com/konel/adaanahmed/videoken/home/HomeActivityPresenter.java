package com.konel.adaanahmed.videoken.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.konel.adaanahmed.videoken.CodeUtil;
import com.konel.adaanahmed.videoken.base.VkBaseAsyncTaskLoader;
import com.konel.adaanahmed.videoken.base.VkWeakReference;
import com.konel.adaanahmed.videoken.db.Lesson;
import com.konel.adaanahmed.videoken.navigation.NavigationUtil;
import com.konel.adaanahmed.videoken.repository.loaders.LessonsLoader;

import java.util.ArrayList;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 04 Jun 2017 3:19 AM
 */


class HomeActivityPresenter implements HomeActivityContract.Presenter,
        LoaderManager.LoaderCallbacks<ArrayList<Lesson>> {

    private VkWeakReference<HomeActivityContract.View> view;
    private LoaderManager loaderManager;

    HomeActivityPresenter(@NonNull HomeActivityContract.View view,
                          @NonNull LoaderManager loaderManager) {
        setView(view);
        this.loaderManager = loaderManager;
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
    }

    @Override
    public void getLessons() {
        loaderManager.initLoader(VkBaseAsyncTaskLoader.getUniqueLoaderId(), null, this);
    }

    @Override
    public void onQuerySubmitted(@NonNull String videoId) {
        NavigationUtil.startClassRoomActivity(view.getNonNull().getViewContext(), videoId);
    }

    @Override
    public Loader<ArrayList<Lesson>> onCreateLoader(int id, Bundle args) {
        return new LessonsLoader(view.getNonNull().getViewContext());
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Lesson>> loader, ArrayList<Lesson> data) {
        if (CodeUtil.isArrayEmpty(data))
            view.getNonNull().showNoLesson();
        else
            view.getNonNull().showLessons(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Lesson>> loader) {
    }
}
