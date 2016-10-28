package ivan.is.awesome.kandykrush.activity;

import android.media.AudioManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import ivan.is.awesome.kandykrush.R;
import ivan.is.awesome.kandykrush.fragments.MainPage;
import ivan.is.awesome.kandykrush.fragments.ListPlayback;

public class MainActivity extends AppCompatActivity{
    private static final int NUM_PAGES = 2;
    private ViewPager pages;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.hide();
        fab.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_BUTTON_PRESS:
                        return true;
                    case MotionEvent.ACTION_BUTTON_RELEASE:
                        return true;

                }
                return false;
            }
        });
        pages = (ViewPager) findViewById(R.id.pager);
        PagerAdapter mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        pages.setAdapter(mPagerAdapter);

    }
    @Override
    public void onBackPressed() {
        if (pages.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            pages.setCurrentItem(pages.getCurrentItem() - 1);
        }
    }
    private class ScreenSlidePagerAdapter extends FragmentPagerAdapter {
        ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    fab.hide();
                    return new MainPage();
                case 1:
                    fab.show();
                    return new ListPlayback();
            }
            return new MainPage();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}