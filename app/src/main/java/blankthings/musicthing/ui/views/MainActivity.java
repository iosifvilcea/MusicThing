package blankthings.musicthing.ui.views;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import blankthings.musicthing.R;
import blankthings.musicthing.data.Track;
import blankthings.musicthing.services.FloatingControlsService;
import blankthings.musicthing.ui.adapters.PlaylistAdapter;
import blankthings.musicthing.ui.presenters.PlaylistPresenter;
import blankthings.musicthing.utils.PlayManager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements PlaylistViewContract {

    private static final int SYS_OVERLAY_PERM_REQUEST_CODE = 9876;

    private List<Track> tracks;
    private PlaylistAdapter adapter;
    private PlaylistPresenter presenter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupRecycler();
        setupPresenter();
    }


    private void setupRecycler() {
        recyclerView.setHasFixedSize(true);

        adapter = new PlaylistAdapter();
        recyclerView.setAdapter(adapter);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final DividerItemDecoration decoration =
                new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(decoration);
    }


    private void setupPresenter() {
        presenter = new PlaylistPresenter(this);
        presenter.init();

        tracks = presenter.getTracks();
        adapter.setTracks(tracks);
    }


    private boolean checkOverlayPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && !Settings.canDrawOverlays(this)) {
            showSettingsWindow();
            return false;
        } else {
            return true;
        }
    }


    @TargetApi(23)
    private void showSettingsWindow() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, SYS_OVERLAY_PERM_REQUEST_CODE);
    }


    private void showFloatingControls() {
        // TODO: 8/19/17 - check if there is a FloatingControlsService currently running.
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


    @Override
    public void setTrackInfo(Track song) {

    }


    @Override
    public void playYoutube(String uri) {
        PlayManager.playYoutube(this, uri);
    }


    @Override
    public void playSoundcloud(String uri) {
        PlayManager.playSoundcloud(this, uri);
    }


    @Override
    public void playSpotify(String uri) {
        PlayManager.playSpotify(this, uri);
    }

}