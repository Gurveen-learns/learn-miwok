package com.example.android.miwok;

import android.app.Activity;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceId;

    //Custom Adapter Constructor
    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceId) {
        super(context, 0, words);  //ArrayAdapter Constructor
        mColorResourceId = colorResourceId;
    }


    @Override
    public View getView(int position, View initialView, ViewGroup parent) {

        View listItemView = initialView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate
                    (R.layout.single_list_item, parent, false);
        }

        Word currentWord = getItem(position);  //This is current Word element listview is asking for
        //And we are changing it
        TextView englishTextView = (TextView) listItemView.findViewById(R.id.english_text_view);
        englishTextView.setText(currentWord.getmEnglishTranslation());

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(currentWord.getmMiwokTranslation());

        ImageView iconimage = (ImageView) listItemView.findViewById(R.id.image);
        if (currentWord.hasImage()) {
            iconimage.setImageResource(currentWord.getmImgResourceId());
            iconimage.setVisibility(View.VISIBLE);
        } else {
            iconimage.setVisibility(View.GONE); //View is completely gone and takes no space
        }

        ////Setting Background in every Single List////
        View textContainer = listItemView.findViewById(R.id.text_container);
        //This is layout view defined in single_list_item XML

        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        textContainer.setBackgroundColor(color);


        return listItemView;

    }
}
