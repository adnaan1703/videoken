package com.konel.adaanahmed.videoken.classroom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;

import com.konel.adaanahmed.videoken.R;
import com.konel.adaanahmed.videoken.VkBaseActivity;
import com.konel.adaanahmed.videoken.navigation.NavigationUtil;
import com.konel.adaanahmed.videoken.navigation.model.ClassRoomActivityNavigationModel;

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
                showToast("wow.. you are awesome!!");
        }
    }
}
