package com.example.android.miwok;

public class Word {

    private String mEnglishTranslation;

    private String mMiwokTranslation;
    private int mImgResourceId = NO_IMAGE_ID;
    private static int NO_IMAGE_ID = -1;

    private int mSoundResourceId;


    public Word(String mEnglishTranslation, String mMiwokTranslation, int mSoundResourceId) {
        this.mEnglishTranslation = mEnglishTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.mSoundResourceId = mSoundResourceId;
    }

    public Word(String mEnglishTranslation, String mMiwokTranslation, int mImgResourceId, int mSoundResourceId) {
        this.mEnglishTranslation = mEnglishTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.mImgResourceId = mImgResourceId;
        this.mSoundResourceId = mSoundResourceId;
    }

    public String getmEnglishTranslation() {
        return mEnglishTranslation;
    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getmImgResourceId() {
        return mImgResourceId;
    }

    public boolean hasImage() {
        if (mImgResourceId == NO_IMAGE_ID) {
            return false;
        }
        return true;
    }

    public int getmSoundResourceId() {
        return mSoundResourceId;
    }

}
