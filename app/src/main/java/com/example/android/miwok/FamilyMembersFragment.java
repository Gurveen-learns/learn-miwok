package com.example.android.miwok;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyMembersFragment extends Fragment {

    public FamilyMembersFragment() {
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
        ///Create English Numbers Array
        final ArrayList<Word> englishFamily = new ArrayList<Word>();

        englishFamily.add(new Word("one", "lutti", R.drawable.family_grandfather, R.raw.family_grandfather));
        englishFamily.add(new Word("two", "otiiko", R.drawable.family_grandmother, R.raw.family_grandmother));
        englishFamily.add(new Word("three", "tolookosu", R.drawable.family_father, R.raw.family_father));
        englishFamily.add(new Word("four", "oyyisa", R.drawable.family_mother, R.raw.family_mother));
        englishFamily.add(new Word("five", "massokka", R.drawable.family_older_brother, R.raw.family_older_brother));
        englishFamily.add(new Word("six", "temokka", R.drawable.family_older_sister, R.raw.family_older_sister));
        englishFamily.add(new Word("seven", "kenekaku", R.drawable.family_son, R.raw.family_son));
        englishFamily.add(new Word("eight", "kawinta", R.drawable.family_daughter, R.raw.family_daughter));
        englishFamily.add(new Word("nine", "wo'e", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        englishFamily.add(new Word("ten", "na'aacha", R.drawable.family_younger_sister, R.raw.family_younger_sister));

        WordAdapter itemsAdapter = new WordAdapter(getActivity(), englishFamily, R.color.category_family);
        ListView listView = (ListView) rootView.findViewById(R.id.list); //This list view is defined in all_lists XML

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Word nowWord = englishFamily.get(position);

                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(getActivity(), nowWord.getmSoundResourceId());
                mMediaPlayer.start();

                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
        return rootView;
    }

    /**
     * Clean up the media player by releasing its resources.
     */
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
