package com.usjr.finalsexam.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.usjr.finalsexam.R;

public class PlayVideoFragment extends Fragment {

    private static final String API_KEY    = "AIzaSyBkO-xekoxTzDKdC9jR2KCwT-B1lmFbIlI";
    private static final String KEY_VID_ID = "VID_ID";

    public static PlayVideoFragment newInstance(String videoId) {
        PlayVideoFragment fragment = new PlayVideoFragment();
        Bundle args = new Bundle();
        args.putString("VID_ID", videoId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_play_video, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        FragmentManager fm = getChildFragmentManager();
        YouTubePlayerSupportFragment youtubePlayerFragment =
                (YouTubePlayerSupportFragment) fm.findFragmentById(R.id.youtubePlayerFragment);

        youtubePlayerFragment.initialize(API_KEY, mInitializedListener);
    }

    private YouTubePlayer.OnInitializedListener mInitializedListener =
            new YouTubePlayer.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                    YouTubePlayer youTubePlayer,
                                                    boolean wasRestored) {
                    if (!wasRestored) {
                        String videoId = getArguments().getString(KEY_VID_ID);
                        youTubePlayer.loadVideo(videoId);
                    }
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                    YouTubeInitializationResult youTubeInitializationResult) {
                    Toast.makeText(getContext(), "Youtube Player failed to initialize.",
                            Toast.LENGTH_LONG).show();
                }
            };
}