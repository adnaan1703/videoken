package com.konel.adaanahmed.videoken.classroom;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.konel.adaanahmed.videoken.CodeUtil;
import com.konel.adaanahmed.videoken.base.VkBaseActivity;
import com.konel.adaanahmed.videoken.base.VkWeakReference;
import com.konel.adaanahmed.videoken.db.Lesson;
import com.konel.adaanahmed.videoken.db.Note;
import com.konel.adaanahmed.videoken.navigation.NavigationUtil;
import com.konel.adaanahmed.videoken.navigation.model.ClassRoomActivityNavigationModel;
import com.konel.adaanahmed.videoken.repository.Repository;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 04 Jun 2017 4:31 AM
 */

class ClassRoomActivityPresenter implements ClassRoomActivityContract.Presenter {

    private Intent mIntent;
    private VkWeakReference<ClassRoomActivityContract.View> mView;
    private ClassRoomActivityNavigationModel model;
    private Lesson mLesson;
    private Realm mRealm = Realm.getDefaultInstance();


    ClassRoomActivityPresenter(@NonNull ClassRoomActivityContract.View view,
                               @NonNull Intent intent) {
        setView(view);
        mIntent = intent;
    }

    @Override
    public void onStart(ClassRoomActivityContract.View view) {
        if (mView.get() == null)
            setView(view);
        model = mIntent.getParcelableExtra(NavigationUtil.KEY_DATA);
        mView.getNonNull().showVideo(model.getYoutubeId(), model.getStartTime());
        dataReceivedFromDb(Repository.findLesson(mRealm, model.getYoutubeId()));
    }

    @Override
    public void onStop() {
        clearView();
        if (mLesson != null)
            mLesson.removeAllChangeListeners();

        if (mRealm != null)
            mRealm.close();
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
        mLesson = Repository.updateNoteInLesson(mRealm, mLesson, note);
    }

    private void dataReceivedFromDb(Lesson lesson) {
        mLesson = lesson;
        if (mLesson == null) {
            mLesson = new Lesson(model.getYoutubeId());
            mLesson = Repository.insertOrUpdateLesson(mRealm, mLesson);
        }
        if (CodeUtil.isArrayEmpty(mLesson.getNotes()))
            mView.getNonNull().showMicTapText();
        else
            mView.getNonNull().showNotes(transMutateToArrayList(mLesson.getNotes()));
    }

    private ArrayList<Note> transMutateToArrayList(RealmList<Note> notes) {
        ArrayList<Note> noteArrayList = new ArrayList<>();
        for (Note note : notes) {
            noteArrayList.add(note);
        }
        return noteArrayList;
    }
}