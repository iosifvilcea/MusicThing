package blankthings.musicthing.ui.presenters;

/**
 * Created by iosif on 8/19/17.
 */

public interface PlaylistPresenterContract extends PresenterContract {

    void play();

    void stop();

    void next();

    void previous();

}
