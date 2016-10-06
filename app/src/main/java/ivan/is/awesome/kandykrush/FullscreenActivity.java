package ivan.is.awesome.kandykrush;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class FullscreenActivity extends Activity{


    ImageButton startButton;
    TextView version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        startButton = (ImageButton)findViewById(R.id.startButton);
        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String versionName = pInfo.versionName;
        version = (TextView)findViewById(R.id.version);
        version.setText("v"+versionName);
        startButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        return true; // if you want to handle the touch event
                    case MotionEvent.ACTION_UP:
                        v.getBackground().clearColorFilter();
                        getBoard(v);
                        return true; // if you want to handle the touch event
                }
                return false;
            }
        });
    }
    public void getBoard(View view) {
        Intent intent = new Intent(this, RandomActivity.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        this.finish();
        // Otherwise defer to system default behavior.
        super.onBackPressed();
    }
}
