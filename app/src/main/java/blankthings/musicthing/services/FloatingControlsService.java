package blankthings.musicthing.services;

import android.app.Service;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import blankthings.musicthing.R;
import blankthings.musicthing.ui.presenters.PlaylistPresenter;
import blankthings.musicthing.ui.views.PlaylistViewContract;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class FloatingControlsService extends Service implements PlaylistViewContract {

    public static final String TAG = FloatingControlsService.class.getSimpleName();

    private static final int INITIAL_WINDOW_POS_X = 0;
    private static final int INITIAL_WINDOW_POS_Y = 100;

    private float currentTouchX;
    private float currentTouchY;

    private WindowManager windowManager;
    private WindowManager.LayoutParams windowParams;

    private View floatingControlsView;

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
        currentTouchX = INITIAL_WINDOW_POS_X;
        currentTouchY = INITIAL_WINDOW_POS_Y;

        floatingControlsView = LayoutInflater
                .from(getApplicationContext())
                .inflate(R.layout.floating_controls_view, null);
    }


    private void setupButtons() {
        final View prev = floatingControlsView.findViewById(R.id.control_previous);
        prev.setOnClickListener((v) -> presenter.previous());

        final View next = floatingControlsView.findViewById(R.id.control_next);
        next.setOnClickListener((v) -> presenter.next());

        final View play = floatingControlsView.findViewById(R.id.control_play);
        play.setOnClickListener((v) -> presenter.play());

        final View close = floatingControlsView.findViewById(R.id.control_close);
        close.setOnClickListener((v) -> stopSelf());

        final View trackInfo = floatingControlsView.findViewById(R.id.track_info);
        trackInfo.setOnTouchListener(this::moveWindow);
    }


    public void goPrevious() {
        presenter.previous();
    }


    public void goNext() {
        presenter.next();
    }


    public boolean moveWindow(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                currentTouchX = event.getRawX();
                currentTouchY = event.getRawY();
                return true;

            case MotionEvent.ACTION_MOVE:
                windowParams.x = (int) (event.getRawX() - currentTouchX);
                windowParams.y = (int) (event.getRawY() - currentTouchX);
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
        presenter.breakDown();
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


    @Override
    public void playYoutube(String linkId) {
        final String ytAppUri = "vnd.youtube:";
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ytAppUri + linkId));
        appIntent.setFlags(FLAG_ACTIVITY_NEW_TASK);

        final String ytWebUri = "https://www.youtube.com/watch?v=";
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ytWebUri + linkId));
        webIntent.setFlags(FLAG_ACTIVITY_NEW_TASK);

        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            startActivity(webIntent);
        }
    }


    @Override
    public void playSoundcloud(String uri) {
        // TODO: 8/19/17
    }


    @Override
    public void playSpotify(String uri) {
        // TODO: 8/19/17
    }
}
