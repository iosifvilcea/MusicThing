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
import blankthings.musicthing.ui.presenters.PlaylistPresenter;
import blankthings.musicthing.ui.views.ViewContract;

public class FloatingControlsService extends Service implements ViewContract {

    public static final String TAG = FloatingControlsService.class.getSimpleName();

    private static final int INITIAL_WINDOW_POS_X = 0;
    private static final int INITIAL_WINDOW_POS_Y = 100;

    private WindowManager windowManager;
    private WindowManager.LayoutParams windowParams;
    private View floatingControlsView;

    private float initialTouchX;
    private float initialTouchY;

    private boolean isPlaying = false;

    private PlaylistPresenter presenter;


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

        presenter = new PlaylistPresenter(this);
    }


    private void setupfloatingView() {
        floatingControlsView = LayoutInflater
                .from(getApplicationContext())
                .inflate(R.layout.floating_controls_view, null);
    }


    private void setupButtons() {
        final View prev = floatingControlsView.findViewById(R.id.control_previous);
        prev.setOnClickListener((v) -> goPrevious());

        final View next = floatingControlsView.findViewById(R.id.control_next);
        next.setOnClickListener((v) -> goNext());

        final View play = floatingControlsView.findViewById(R.id.control_play);
        play.setOnClickListener((v) -> goPlay());

        final View close = floatingControlsView.findViewById(R.id.control_close);
        close.setOnClickListener((v) -> goExit());

        floatingControlsView.setOnTouchListener(this::moveWindow);
    }


    public void goPrevious() {
        presenter.previous();
    }


    public void goNext() {
        presenter.next();
    }


    public void goPlay() {
        if (isPlaying) {
            presenter.stop();
        } else {
            presenter.play();
        }

        isPlaying = !isPlaying;
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


    @Override
    public void showLoading() {
        // TODO: 8/19/17 Animate fade in.
    }


    @Override
    public void hideLoading() {
        // TODO: 8/19/17 - Animate fade out.
    }
}
