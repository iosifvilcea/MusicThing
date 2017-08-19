package blankthings.musicthing.ui.views;

/**
 * Created by iosif on 8/19/17.
 */

public interface PlaylistViewContract extends ViewContract {

    void playYoutube(final String uri);

    void playSoundcloud(final String uri);

    void playSpotify(final String uri);

}
