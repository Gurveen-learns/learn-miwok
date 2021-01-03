package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class ColorsFragment extends Fragment {

    public ColorsFragment() {
        // Required empty public constructor
    }

    private MediaPlayer mMediaPlayer;
    /*
     * This Listener will be called Media Player has stopped
     * */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.all_lists, container, false);
        final ArrayList<Word> englishColors = new ArrayList<Word>();

        englishColors.add(new Word("one", "lutti", R.drawable.color_black, R.raw.color_black));
        englishColors.add(new Word("two", "otiiko", R.drawable.color_brown, R.raw.color_brown));
        englishColors.add(new Word("three", "tolookosu", R.drawable.color_gray, R.raw.color_gray));
        englishColors.add(new Word("four", "oyyisa", R.drawable.color_white, R.raw.color_white));
        englishColors.add(new Word("five", "massokka", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        englishColors.add(new Word("six", "temokka", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
        englishColors.add(new Word("seven", "kenekaku", R.drawable.color_green, R.raw.color_green));
        englishColors.add(new Word("eight", "kawinta", R.drawable.color_red, R.raw.color_red));
        englishColors.add(new Word("nine", "wo'e", R.drawable.color_green, R.raw.color_green));
        englishColors.add(new Word("ten", "na'aacha", R.drawable.color_red, R.raw.color_red));

        WordAdapter itemsAdapter = new WordAdapter(getActivity(), englishColors, R.color.category_colors);
        ListView listView = (ListView) rootView.findViewById(R.id.list); //This list view is defined in all_lists XML
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Word nowWord = englishColors.get(position);

                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(getActivity(), nowWord.getmSoundResourceId());
                mMediaPlayer.start();

                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
        return rootView;
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }

    @Override
    public void onStop() {
        //Whenever activity is stopped media player should stop playing and release resources
        super.onStop();
        releaseMediaPlayer();
    }
}

