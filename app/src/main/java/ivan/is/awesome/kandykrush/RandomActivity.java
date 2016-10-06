package ivan.is.awesome.kandykrush;

import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RandomActivity extends FragmentActivity{
    public MediaPlayer mp[] = new MediaPlayer[11];
    public ImageButton stopButton, playButton;
    public Animation rotation;
    public ImageView bg;
    public TextView above;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        Toast toast = Toast.makeText(this, "Tap xxx_Luminati_xxx for Meme Madness or slide from the right side for more fun", Toast.LENGTH_LONG);
        toast.show();
        setupMedia();
        buttonSetup();
        drawerSetup();
        rotation = AnimationUtils.loadAnimation(RandomActivity.this, R.anim.rotation);
        rotation.setFillAfter(true);
    }

    private void drawerSetup() {

        ListView mDrawerList = (ListView) findViewById(R.id.left_drawer);
        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        // Set the adapter for the list view
        mDrawerList.setAdapter(new ListAdapter(this, mp));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mp[position].start();
                bg.startAnimation(rotation);
            }
        });
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object *//* nav drawer icon to replace 'Up' caret */
                R.string.title,  /* "open drawer" description */
                R.string.title  /* "close drawer" description */
        ) {

            /**
             * Called when a drawer has settled in a completely closed state.
             */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            /**
             * Called when a drawer has settled in a completely open state.
             */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    public void setupMedia() {
        mp[0]=(MediaPlayer.create(this, R.raw.camera));
        mp[1]=(MediaPlayer.create(this, R.raw.never));
        mp[2]=(MediaPlayer.create(this, R.raw.combo));
        mp[3]=(MediaPlayer.create(this, R.raw.dankstorm));
        mp[4]=(MediaPlayer.create(this, R.raw.noscope));
        mp[5]=(MediaPlayer.create(this, R.raw.nuke));
        mp[6]=(MediaPlayer.create(this, R.raw.son));
        mp[7]=(MediaPlayer.create(this, R.raw.spooky));
        mp[8]=(MediaPlayer.create(this, R.raw.triple));
        mp[9]=(MediaPlayer.create(this, R.raw.wow));
        mp[10]=(MediaPlayer.create(this, R.raw.weed));
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public void onBackPressed() {
        new Thread(new Runnable() {
            public void run() {
                for (int x = 0; x < mp.length; x++) {
                    if (mp[x].isPlaying()) {
                        mp[x].stop();
                    }
                    mp[x].release();
                    mp[x]=null;
                }
            }
        }).start();
        this.finish();
        // Otherwise defer to system default behavior.
        super.onBackPressed();
    }
    public void buttonSetup(){
        playButton = (ImageButton) findViewById(R.id.playButton);
        stopButton = (ImageButton) findViewById(R.id.stopButton);
        above = (TextView)findViewById(R.id.above);
        bg = (ImageButton) findViewById(R.id.bg);
        bg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_UP:
                        mp[(int)(Math.random()*10)].start();
                        bg.startAnimation(rotation);
                        String st[] = getResources().getStringArray(R.array.phrases);
                        above.setText(st[(int)(Math.random()*8)]);
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
                        new Thread(new Runnable() {
                            public void run() {
                                for (int x = 0; x < mp.length; x++) {
                                    if (mp[x].isPlaying()) {
                                        mp[x].stop();
                                    }
                                    mp[x].release();
                                    mp[x]=null;
                                }
                                setupMedia();
                            }
                        }).start();
                        bg.clearAnimation();
                        return true;
                }
                return false;
            }
        });
    }
}
