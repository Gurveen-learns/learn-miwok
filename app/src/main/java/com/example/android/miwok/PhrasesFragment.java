package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
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
public class PhrasesFragment extends Fragment {


    public PhrasesFragment() {
        // Required empty public constructor
    }

    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    /*
     * This Listener will be called Media Player has stopped
     * */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };
    AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListner =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK) {
                        //Pause playback
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0); //Hear it from start
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        //Resume
                        mMediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        //Stop playback and clear resources
                        releaseMediaPlayer();
                    }

                }
            };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.all_lists, container, false);
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        ///Create English Numbers Array
        final ArrayList<Word> englishPhrases = new ArrayList<Word>();

        englishPhrases.add(new Word("one", "lutti", R.raw.phrase_come_here));
        englishPhrases.add(new Word("two", "otiiko", R.raw.phrase_are_you_coming));
        englishPhrases.add(new Word("three", "tolookosu", R.raw.phrase_how_are_you_feeling));
        englishPhrases.add(new Word("four", "oyyisa", R.raw.phrase_im_coming));
        englishPhrases.add(new Word("five", "massokka", R.raw.phrase_im_feeling_good));
        englishPhrases.add(new Word("six", "temokka", R.raw.phrase_lets_go));
        englishPhrases.add(new Word("seven", "kenekaku", R.raw.phrase_my_name_is));
        englishPhrases.add(new Word("eight", "kawinta", R.raw.phrase_what_is_your_name));
        englishPhrases.add(new Word("nine", "wo'e", R.raw.phrase_where_are_you_going));
        englishPhrases.add(new Word("ten", "na'aacha", R.raw.phrase_yes_im_coming));

        WordAdapter itemsAdapter = new WordAdapter(getActivity(), englishPhrases, R.color.category_phrases);
        ListView listView = (ListView) rootView.findViewById(R.id.list); //This list view is defined in all_lists XML
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Word nowWord = englishPhrases.get(position);

                releaseMediaPlayer();

                //request audio focus for playback
                int result = mAudioManager.requestAudioFocus(mAudioFocusChangeListner,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    //We have audio focus now
                    mMediaPlayer = MediaPlayer.create(getActivity(), nowWord.getmSoundResourceId());
                    mMediaPlayer.start();

                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
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

            //Abandon Audio Focus after playback
            mAudioManager.abandonAudioFocus(mAudioFocusChangeListner);
        }
    }

    @Override
    public void onStop() {
        //Whenever activity is stopped media player should stop playing and release resources
        super.onStop();
        releaseMediaPlayer();
    }

}
