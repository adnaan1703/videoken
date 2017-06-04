package com.konel.adaanahmed.videoken.base;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author : Adnaan 'Zohran' Ahmed <adnaan.1703@gmail.com>
 * @version : 1.0.0
 * @since : 03 Jun 2017 8:10 PM
 */


public abstract class VkBaseAsyncTaskLoader<T> extends AsyncTaskLoader<T> {

    private static final AtomicInteger atomicId = new AtomicInteger(0);
    private T mData;

    public VkBaseAsyncTaskLoader(Context context) {
        super(context);
        onContentChanged();
    }

    public static int getUniqueLoaderId() {
        return atomicId.getAndIncrement();
    }

    @Override
    protected void onStartLoading() {

        if (takeContentChanged() || mData == null) {
            // If the data has changed since the last time it was loaded
            // or is not currently available start a load.
            forceLoad();
        } else {
            // If we currently have a result available,
            // deliver it immediately
            deliverResult(mData);
        }
    }

    @Override
    public void deliverResult(T data) {

        mData = data;
        if (isReset()) {
            // An async query came in while the loader is stopped.
            // We don't need the result.
            if (mData != null) {
                onReleaseResources();
            }
        } else if (isStarted()) {
            // If the loader is currently started.
            // We can immediately deliver its results.
            super.deliverResult(data);
        }
    }

    @Override
    protected void onStopLoading() {
        // Attempt to cancel the current load task if possible
        cancelLoad();
    }

    @Override
    public void onCanceled(T data) {
        super.onCanceled(data);
        onReleaseResources();
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (mData != null) {
            onReleaseResources();
        }
    }

    private void onReleaseResources() {
        mData = null;
    }

}
