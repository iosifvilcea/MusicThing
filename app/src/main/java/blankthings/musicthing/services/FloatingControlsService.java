package blankthings.musicthing.services;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import blankthings.musicthing.R;

public class FloatingControlsService extends Service {

    public static final String TAG = FloatingControlsService.class.getSimpleName();

    private static final int INITIAL_WINDOW_POS_X = 0;
    private static final int INITIAL_WINDOW_POS_Y = 100;

    private WindowManager windowManager;
    private WindowManager.LayoutParams windowParams;
    private View floatingControlsView;

    private float initialTouchX;
    private float initialTouchY;


    public FloatingControlsService() {
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        setupfloatingView();
        setupButtons();
        setupWindowManager();
    }


    private void setupfloatingView() {
        floatingControlsView = LayoutInflater
                .from(getApplicationContext())
                .inflate(R.layout.floating_controls_view, null);
    }


    private void setupButtons() {
        View prev = floatingControlsView.findViewById(R.id.control_previous);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goPrevious();
            }
        });

        final View next = floatingControlsView.findViewById(R.id.control_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goNext();
            }
        });

        final View play = floatingControlsView.findViewById(R.id.control_play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goPlay();
            }
        });

        final View close = floatingControlsView.findViewById(R.id.control_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goExit();
            }
        });
        
        floatingControlsView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return moveWindow(v, event);
            }
        });
    }


    public void goPrevious() {
        Log.e(TAG, "Prev.");
    }


    public void goNext() {
        Log.e(TAG, "Next.");
    }



    public void goPlay() {
        // TODO: 8/19/17 - if playing, then stop and change drawable to paused.
        // TODO: 8/19/17 - if stopped, then play and change drawable to playing.
        Log.e(TAG, "Play / Pause");
    }


    public void goExit() {
        Log.e(TAG, "Exit.");
        stopSelf();
    }


    public boolean moveWindow(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initialTouchX = event.getRawX();
                initialTouchY = event.getRawY();
                return true;

            case MotionEvent.ACTION_MOVE:
                windowParams.x = INITIAL_WINDOW_POS_X + (int) (event.getRawX() - initialTouchX);
                windowParams.y = INITIAL_WINDOW_POS_Y + (int) (event.getRawY() - initialTouchY);
                windowManager.updateViewLayout(floatingControlsView, windowParams);
                return true;
        }

        return false;
    }



    private void setupWindowManager() {
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        windowParams = makeWindowParams();
        windowManager.addView(floatingControlsView, windowParams);
    }


    private WindowManager.LayoutParams makeWindowParams() {
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP;
        params.x = INITIAL_WINDOW_POS_X;
        params.y = INITIAL_WINDOW_POS_Y;

        return params;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (floatingControlsView != null) {
            windowManager.removeView(floatingControlsView);
        }
    }
}
