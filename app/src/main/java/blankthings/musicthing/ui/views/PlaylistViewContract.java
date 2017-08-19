package blankthings.musicthing.ui.views;

import blankthings.musicthing.data.Track;

/**
 * Created by iosif on 8/19/17.
 */

public interface PlaylistViewContract extends ViewContract {

    void setTrackInfo(Track song);

    void playYoutube(final String uri);

    void playSoundcloud(final String uri);

    void playSpotify(final String uri);

}
