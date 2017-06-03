package com.konel.adaanahmed.videoken.classroom;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.konel.adaanahmed.videoken.Config;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 03 Jun 2017 4:34 PM
 */


public class VideoPlaybackFragment extends YouTubePlayerSupportFragment implements YouTubePlayer.OnInitializedListener {

    private YouTubePlayer player;
    private String videoId = null;
    private int startTime = 0;

    private static final String KEY_VIDEO_ID = "videoId";
    private static final String KEY_TIME = "startTime";


    public VideoPlaybackFragment() {
    }

    public static VideoPlaybackFragment newInstance() {
        return newInstance(null, 0);
    }

    public static VideoPlaybackFragment newInstance(String videoId) {
        return newInstance(videoId, 0);
    }

    public static VideoPlaybackFragment newInstance(String videoId, int timeInMillis) {

        VideoPlaybackFragment fragment = new VideoPlaybackFragment();
        Bundle args = new Bundle();
        if (!TextUtils.isEmpty(videoId))
            args.putString(KEY_VIDEO_ID, videoId);
        args.putInt(KEY_TIME, timeInMillis);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            videoId = getArguments().getString(KEY_VIDEO_ID, "");
            startTime = getArguments().getInt(KEY_TIME, 0);
        }
        this.initialize(Config.GOOGLE_DEV_KEY, this);
    }

    private void loadVideo(@NonNull String videoId, int startTime) {
        this.videoId = videoId;
        this.startTime = startTime;
        if (player != null) {
            if (player.isPlaying())
                player.pause();

            player.loadVideo(videoId, startTime);
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer,
                                        boolean wasRestored) {
        if (!wasRestored) {
            player = youTubePlayer;
            player.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
            if (!TextUtils.isEmpty(videoId))
                player.cueVideo(videoId, startTime);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult error) {
        if (error.isUserRecoverableError()) {
            error.getErrorDialog(getActivity(), 0).show();
        } else {
            Toast.makeText(getContext(), "Error while initialisation :" + error.toString(), Toast.LENGTH_SHORT).show();
        }

    }
}
