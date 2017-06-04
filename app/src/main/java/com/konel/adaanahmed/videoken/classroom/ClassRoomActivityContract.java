package com.konel.adaanahmed.videoken.classroom;

import android.support.annotation.NonNull;

import com.konel.adaanahmed.videoken.base.BasePresenter;
import com.konel.adaanahmed.videoken.base.BaseView;
import com.konel.adaanahmed.videoken.base.VkBaseActivity;
import com.konel.adaanahmed.videoken.db.Note;

import java.util.ArrayList;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 04 Jun 2017 4:27 AM
 */


interface ClassRoomActivityContract {

    interface View extends BaseView {
        void showVideo(String videoId, int startTime);

        void showNotes(ArrayList<Note> notes);

        void addNote(@NonNull Note note);

        void showMicTapText();

    }

    interface Presenter extends BasePresenter<View> {
        void onMicClicked(VkBaseActivity activity);

        void onTextReceived(String text, int time);
    }
}
