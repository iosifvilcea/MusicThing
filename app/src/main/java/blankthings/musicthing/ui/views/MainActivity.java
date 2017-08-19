package blankthings.musicthing.ui.views;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;

import blankthings.musicthing.R;
import blankthings.musicthing.services.FloatingControlsService;

public class MainActivity extends AppCompatActivity implements ViewContract {

    private static final int SYS_OVERLAY_PERM_REQUEST_CODE = 9876;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && !Settings.canDrawOverlays(this)) {
            showSettingsWindow();

        } else {
            showFloatingControls();
        }
    }


    @TargetApi(23)
    private void showSettingsWindow() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, SYS_OVERLAY_PERM_REQUEST_CODE);
    }


    private void showFloatingControls() {
        startService(new Intent(MainActivity.this, FloatingControlsService.class));
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SYS_OVERLAY_PERM_REQUEST_CODE && resultCode == RESULT_OK) {
            showFloatingControls();
        }
    }


    @Override
    public void showLoading() {

    }


    @Override
    public void hideLoading() {

    }
}
