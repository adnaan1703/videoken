package com.konel.adaanahmed.videoken.classroom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.konel.adaanahmed.videoken.CodeUtil;
import com.konel.adaanahmed.videoken.R;
import com.konel.adaanahmed.videoken.base.VkBaseActivity;
import com.konel.adaanahmed.videoken.db.Note;
import com.konel.adaanahmed.videoken.navigation.NavigationUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author : Adnaan 'Zohran' Ahmed <adnaan.1703@gmail.com>
 * @version : 1.0.0
 * @since : 03 Jun 2017 2:20 PM
 */


public class ClassRoomActivity extends VkBaseActivity implements View.OnClickListener,
        ClassRoomActivityContract.View, ClassRoomNotesAdapter.ClassRoomNotesInteractionListener {

    @BindView(R.id.activity_classroom_audio_button)
    FrameLayout audioButton;
    @BindView(R.id.activity_classroom_notes_list)
    RecyclerView notesList;
    @BindView(R.id.activity_classroom_audio_tap_text)
    TextView audioTapText;

    private ClassRoomNotesAdapter adapter;

    private ClassRoomActivityPresenter presenter;
    private IVideoPlaybackDelegator videoPlaybackDelegator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom);
        ButterKnife.bind(this);
        initComponents();
    }

    private void initComponents() {
        audioButton.setOnClickListener(this);
        notesList.setVisibility(View.GONE);
        audioTapText.setVisibility(View.GONE);
        presenter = new ClassRoomActivityPresenter(this, getIntent());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_classroom_audio_button:
                presenter.onMicClicked(this);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case NavigationUtil.SPEECH_TO_TEXT_ACTIVITY_RESULT_CODE: {
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if (CodeUtil.isArrayEmpty(result))
                        showToast("Sorry unable to understand!");
                    else
                        manageTextInput(result.get(0));
                }
                break;
            }

        }
    }

    private void manageTextInput(String text) {
        int videoPlaybackTime = 0;
        if (videoPlaybackDelegator != null)
            videoPlaybackTime = videoPlaybackDelegator.getCurrentTime();
        presenter.onTextReceived(text, videoPlaybackTime);
        adapter.addNote(new Note(text, videoPlaybackTime));
    }

    @NonNull
    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    public void showVideo(String videoId, int startTime) {
        VideoPlaybackFragment fragment = VideoPlaybackFragment.newInstance(videoId, startTime);
        getSupportFragmentManager().beginTransaction().add(R.id.activity_classroom_video_container, fragment).commitNow();
        videoPlaybackDelegator = fragment;
    }

    @Override
    public void showNotes(ArrayList<Note> notes) {
        if (CodeUtil.isArrayEmpty(notes))
            return;

        adapter = new ClassRoomNotesAdapter(notes, this);
        audioTapText.setVisibility(View.GONE);
        notesList.setVisibility(View.VISIBLE);
        notesList.setLayoutManager(new LinearLayoutManager(this));
        notesList.setAdapter(adapter);
    }

    @Override
    public void showMicTapText() {
        notesList.setVisibility(View.GONE);
        audioTapText.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoteAdditionSuccess(String message) {
        showToast(message);
    }

    @Override
    public void showNoteAdditionFailure(String message) {
        showToast(message);
        Log.e("Realm", message);
    }

    @Override
    public void onNoteClicked(int startTime) {
        if (videoPlaybackDelegator != null)
            videoPlaybackDelegator.seekToTome(startTime);
    }
}
