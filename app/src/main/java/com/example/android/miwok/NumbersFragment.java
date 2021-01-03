package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class NumbersFragment extends Fragment {

    public NumbersFragment() {
        //Required empty public constructor
    }


    private MediaPlayer mMediaPlayer;
    /*
     * This Listener will be called Media Player has stopped
     * A single completion listner for all media player objects
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

        final ArrayList<Word> englishNumbers = new ArrayList<Word>();

        englishNumbers.add(new Word("one", "lutti",
                R.drawable.number_one, R.raw.number_one));
        englishNumbers.add(new Word("two", "otiiko",
                R.drawable.number_two, R.raw.number_two));
        englishNumbers.add(new Word("three", "tolookosu",
                R.drawable.number_three, R.raw.number_three));
        englishNumbers.add(new Word("four", "oyyisa",
                R.drawable.number_four, R.raw.number_four));
        englishNumbers.add(new Word("five", "massokka",
                R.drawable.number_five, R.raw.number_five));
        englishNumbers.add(new Word("six", "temokka",
                R.drawable.number_six, R.raw.number_six));
        englishNumbers.add(new Word("seven", "kenekaku",
                R.drawable.number_seven, R.raw.number_seven));
        englishNumbers.add(new Word("eight", "kawinta",
                R.drawable.number_eight, R.raw.number_eight));
        englishNumbers.add(new Word("nine", "wo'e",
                R.drawable.number_nine, R.raw.number_nine));
        englishNumbers.add(new Word("ten", "na'aacha",
                R.drawable.number_ten, R.raw.number_ten));

        WordAdapter itemsAdapter = new WordAdapter(getActivity(), englishNumbers,
                R.color.category_numbers);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        //This list view is defined in all_lists XML
        listView.setAdapter(itemsAdapter);  //This adapter will be called upon by listview

        //Click Listener for each list item to play audio file
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Word nowWord = englishNumbers.get(position);//This is Word item getting clicked

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
