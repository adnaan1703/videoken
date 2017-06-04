package com.konel.adaanahmed.videoken.classroom;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.konel.adaanahmed.videoken.base.VkBaseActivity;
import com.konel.adaanahmed.videoken.base.VkWeakReference;
import com.konel.adaanahmed.videoken.db.Lesson;
import com.konel.adaanahmed.videoken.db.Note;
import com.konel.adaanahmed.videoken.navigation.NavigationUtil;
import com.konel.adaanahmed.videoken.navigation.model.ClassRoomActivityNavigationModel;
import com.konel.adaanahmed.videoken.repository.Repository;

import io.realm.Realm;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 04 Jun 2017 4:31 AM
 */


class ClassRoomActivityPresenter implements ClassRoomActivityContract.Presenter,
        Realm.Transaction.OnSuccess, Realm.Transaction.OnError {

    private Intent intent;
    private VkWeakReference<ClassRoomActivityContract.View> mView;
    private ClassRoomActivityNavigationModel model;
    private Lesson mLesson;

    ClassRoomActivityPresenter(@NonNull ClassRoomActivityContract.View view,
                               @NonNull Intent intent) {
        setView(view);
        this.intent = intent;
    }

    @Override
    public void onStart(ClassRoomActivityContract.View view) {
        if (mView.get() == null)
            setView(view);
        model = intent.getParcelableExtra(NavigationUtil.KEY_DATA);
        mView.getNonNull().showVideo(model.getYoutubeId(), model.getStartTime());
        mLesson = new Lesson(model.getYoutubeId());
        dataReceivedFromDb(Repository.findLesson(model.getYoutubeId()));
    }

    @Override
    public void onStop() {
        clearView();
        if (mLesson != null)
            mLesson.removeAllChangeListeners();

    }

    @Override
    public void setView(ClassRoomActivityContract.View view) {
        mView = new VkWeakReference<>(view);
    }

    @Override
    public void clearView() {
        mView.clear();

    }

    @Override
    public void onMicClicked(VkBaseActivity activity) {
        NavigationUtil.startSpeechToTextActivityForResult(activity, "Speak to add notes");
    }

    @Override
    public void onTextReceived(String text, int time) {
        Note note = new Note(text, time);
        mLesson.addNote(note);
        Repository.insertOrUpdateLesson(mLesson, this, this);
    }

    private void dataReceivedFromDb(Lesson lesson) {
        mLesson = lesson;
        if (mLesson == null) {
            mLesson = new Lesson(model.getYoutubeId());
            Repository.insertOrUpdateLesson(mLesson, this, this);
        }
    }

    @Override
    public void onError(Throwable error) {
        mView.getNonNull().showNoteAdditionFailure("Error while saving!");
    }

    @Override
    public void onSuccess() {
        mView.getNonNull().showNoteAdditionSuccess("Successfully saved!");
    }

}