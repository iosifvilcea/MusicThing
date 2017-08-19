package blankthings.musicthing.ui.presenters;

import android.util.Log;

import blankthings.musicthing.data.PlaylistData;
import blankthings.musicthing.data.Track;
import blankthings.musicthing.ui.views.PlaylistViewContract;

/**
 * Created by iosif on 8/18/17.
 */

public class PlaylistPresenter
        extends BasePresenter
        implements PlaylistPresenterContract {

    public static final String TAG = PlaylistPresenter.class.getSimpleName();

    private static final int STARTING_TRACK_POSITION = 0;
    private int currentSongPosition = STARTING_TRACK_POSITION;

    private PlaylistViewContract view;

    private boolean isPlaying = false;
    private PlaylistData playlist;

    public PlaylistPresenter(PlaylistViewContract view) {
        super(view);
        this.view = view;
    }


    @Override
    public void init() {
        playlist = new PlaylistData();
    }


    @Override
    public void breakDown() {

    }


    @Override
    public void play() {
        if (!isPlaying) {
            play(currentSongPosition);

        } else {
            Log.e(TAG, "Stopping ..");
            view.playYoutube("");
        }

        isPlaying = !isPlaying;
    }


    public void play(final int trackPosition) {
        final Track track = playlist.getSongs()[trackPosition];
        Log.e(TAG, "Playing track " + track.getSong());
        view.setTrackInfo(track);
        view.playYoutube(track.getUrl());
    }


    @Override
    public void next() {
        final int lastPosAvailable = playlist.getSongs().length - 1;
        if (currentSongPosition == lastPosAvailable) {
            currentSongPosition = STARTING_TRACK_POSITION;
        } else {
            currentSongPosition++;
        }

        play(currentSongPosition);
    }


    @Override
    public void previous() {
        final int lastPosAvailable = playlist.getSongs().length - 1;
        if (currentSongPosition == STARTING_TRACK_POSITION) {
            currentSongPosition = lastPosAvailable;
        } else {
            currentSongPosition--;
        }

        play(currentSongPosition);
    }

}
