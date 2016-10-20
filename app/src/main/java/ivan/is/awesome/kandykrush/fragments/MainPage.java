package ivan.is.awesome.kandykrush.fragments;

import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

import ivan.is.awesome.kandykrush.R;

public class MainPage extends Fragment {
    public MediaPlayer[] mp;
    public ImageButton stopButton, playButton;
    public Animation rotation;
    public ImageView bg;
    public TextView above;
    ArrayList<String> titles = new ArrayList<>();
    public boolean inProgress =false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.main_frag_layout, container, false);
        buttonSetup(rootView);
        rotation = AnimationUtils.loadAnimation(rootView.getContext(), R.anim.rotation);
        rotation.setFillAfter(true);
        setupMedia();
        return rootView;
    }
    public void setupMedia() {
        Field[] fields = R.raw.class.getFields();
        // loop for every file in raw folder
        mp = new MediaPlayer[fields.length];
        for(int x=0; x < fields.length; x++) {
            String filename = fields[x].getName();
            mp[x] = MediaPlayer.create(getActivity(), getResources().getIdentifier(filename, "raw", getActivity().getPackageName()));
            filename = filename.substring(0, 1).toUpperCase() + filename.substring(1);
            filename = filename.replace("_", " ");
            titles.add(filename);
        }
    }
    public void buttonSetup(View v){
        playButton = (ImageButton) v.findViewById(R.id.playButton);
        stopButton = (ImageButton) v.findViewById(R.id.stopButton);
        above = (TextView)v.findViewById(R.id.above);
        bg = (ImageButton)v.findViewById(R.id.bg);
        bg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_UP:
                        mp[(int)(Math.random()*10)].start();
                        bg.startAnimation(rotation);
                        String st[] = getResources().getStringArray(R.array.phrases);
                        above.setText(st[(int)(Math.random()*st.length)]);
                        return true;
                }
                return false;
            }
        });
        playButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        return true;
                    case MotionEvent.ACTION_UP:
                        for (MediaPlayer aMp : mp) {
                            aMp.start();
                        }
                        bg.startAnimation(rotation);
                        return true;
                }
                return false;
            }
        });
        stopButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        return true;
                    case MotionEvent.ACTION_UP:
                        v.getBackground().clearColorFilter();
                        stopMedia();
                        bg.clearAnimation();
                        return true;
                }
                return false;
            }
        });
    }
    public void stopMedia(){
        if(!inProgress) {
            boolean check = false;
            for (int x = 0; x < mp.length; x++) {
                if (mp[x].isPlaying()) {
                    check = true;
                }
            }
            if (check) {
                inProgress = true;
                new Thread(new Runnable() {
                    public void run() {
                        for (int x = 0; x < mp.length; x++) {
                            if (mp[x].isPlaying()) {
                                mp[x].stop();
                            }
                            mp[x].release();
                            mp[x] = null;
                        }
                        setupMedia();
                        inProgress = false;
                    }
                }).start();
            }
        }
    }

}


