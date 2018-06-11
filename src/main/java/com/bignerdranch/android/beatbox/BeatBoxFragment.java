package com.bignerdranch.android.beatbox;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.android.beatbox.databinding.FragmentBeatBoxBinding;
import com.bignerdranch.android.beatbox.databinding.ListItemSoundBinding;

import java.util.List;

public class BeatBoxFragment extends Fragment {

    private String TAG = "BeatBoxFragment_DEBUG";
    private BeatBox mBeatBox;

    public static BeatBoxFragment newInstance () {
        return new BeatBoxFragment();
    }


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//       Log.d (TAG, "Entered onCreateView");

        FragmentBeatBoxBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_beat_box, container, false);

        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        binding.recyclerView.setAdapter(new SoundAdapter(mBeatBox.getSounds()));
//        binding.recyclerView.setAdapter (new SoundAdapter ());

//        Log.d (TAG, "About to return binding.getRoot in onCreateView");

        return binding.getRoot ();
    }

    @Override
    public void onDestroy () {
        super.onDestroy();
        mBeatBox.release();
    }

    @Override
    public void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

//        Log.d (TAG, "Creating BeatBox . . . ");

        mBeatBox = new BeatBox(getActivity());

    //        Log.d (TAG, ". . .BeatBox has been created");
    }

    private class SoundHolder extends RecyclerView.ViewHolder {
        private ListItemSoundBinding mBinding;

        private SoundHolder (ListItemSoundBinding binding) {
            super (binding.getRoot());
            mBinding = binding;
            mBinding.setViewModel (new SoundViewModel(mBeatBox));
        }

        public void bind (Sound sound) {
            mBinding.getViewModel ().setSound (sound);
            mBinding.executePendingBindings();
        }
    }

    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder> {
        private List<Sound> mSounds;

        public SoundAdapter (List<Sound> sounds) {
          //  Log.d (TAG, "Inside soundAdapter");
            mSounds = sounds;
        }

        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            Log.d (TAG, "entered onCreateViewHolder");

            LayoutInflater inflater = LayoutInflater.from(getActivity());
            ListItemSoundBinding binding = DataBindingUtil.
                    inflate(inflater, R.layout.list_item_sound, parent, false);

            return new SoundHolder(binding);
        }

        @Override
        public void onBindViewHolder(SoundHolder holder, int position) {
            Sound sound = mSounds.get (position);
            holder.bind (sound);
        }

        @Override
        public int getItemCount() {
            Log.d (TAG, "Returning an itemCount of " + mSounds.size ());
            return mSounds.size ();
        }
    }
}
