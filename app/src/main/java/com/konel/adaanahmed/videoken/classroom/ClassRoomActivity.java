package com.konel.adaanahmed.videoken.classroom;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.konel.adaanahmed.videoken.R;
import com.konel.adaanahmed.videoken.VkBaseActivity;
import com.konel.adaanahmed.videoken.navigation.NavigationUtil;
import com.konel.adaanahmed.videoken.navigation.model.ClassRoomActivityNavigationModel;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author : Adnaan 'Zohran' Ahmed <adnaan.1703@gmail.com>
 * @version : 1.0.0
 * @since : 03 Jun 2017 2:20 PM
 */


public class ClassRoomActivity extends VkBaseActivity implements View.OnClickListener {

    @BindView(R.id.activity_classroom_audio_button)
    FrameLayout audioButton;

    private ClassRoomActivityNavigationModel model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom);
        ButterKnife.bind(this);
        initComponents();
    }

    private void initComponents() {

        model = getIntent().getParcelableExtra(NavigationUtil.KEY_DATA);
        audioButton.setOnClickListener(this);
        VideoPlaybackFragment fragment = VideoPlaybackFragment.newInstance(model.getYoutubeId(), model.getStartTime());
        getSupportFragmentManager().beginTransaction().add(R.id.activity_classroom_video_container, fragment).commitNow();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_classroom_audio_button:
                promptSpeechInput();
        }
    }

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Bak be bro!");
        try {
            startActivityForResult(intent, 100);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "he is dumb", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 100: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    showToast(result.get(0));
                }
                break;
            }

        }
    }
}
