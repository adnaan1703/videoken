package com.konel.adaanahmed.videoken.classroom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;

import com.konel.adaanahmed.videoken.CodeUtil;
import com.konel.adaanahmed.videoken.R;
import com.konel.adaanahmed.videoken.base.VkBaseActivity;
import com.konel.adaanahmed.videoken.navigation.NavigationUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author : Adnaan 'Zohran' Ahmed <adnaan.1703@gmail.com>
 * @version : 1.0.0
 * @since : 03 Jun 2017 2:20 PM
 */


public class ClassRoomActivity extends VkBaseActivity implements View.OnClickListener, ClassRoomActivityContract.View {

    @BindView(R.id.activity_classroom_audio_button)
    FrameLayout audioButton;

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
    public void showNoteAdditionSuccess(String message) {
        showToast(message);
    }

    @Override
    public void showNoteAdditionFailure(String message) {
        showToast(message);
    }
}
