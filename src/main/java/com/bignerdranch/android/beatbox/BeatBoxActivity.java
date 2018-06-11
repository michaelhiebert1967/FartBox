package com.bignerdranch.android.beatbox;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class BeatBoxActivity extends SingleFragmentActivity {

    private static final String TAG = "BeatBoxActivity_DEBUG";

    @Override
    protected Fragment createFragment () {
        Log.d (TAG, "About to grab a new Instance of BeatBox Fragment.");
        return BeatBoxFragment.newInstance ();
    }

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d (TAG, "Entered onCreate");
        super.onCreate(savedInstanceState);
        Log.d (TAG, "About to setContentView");
        setContentView(R.layout.fragment_beat_box);
        Log.d (TAG, "Leaving onCreate");
    }
    */
}
