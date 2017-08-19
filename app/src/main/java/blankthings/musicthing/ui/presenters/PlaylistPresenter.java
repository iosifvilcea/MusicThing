package blankthings.musicthing.ui.presenters;

import blankthings.musicthing.ui.views.PlaylistViewContract;

/**
 * Created by iosif on 8/18/17.
 */

public class PlaylistPresenter
        extends BasePresenter
        implements PlaylistPresenterContract {

    private PlaylistViewContract view;
    private boolean isPlaying = false;

    public PlaylistPresenter(PlaylistViewContract view) {
        super(view);
        this.view = view;
    }


    @Override
    public void init() {
        
    }


    @Override
    public void breakDown() {

    }


    @Override
    public void play() {
        if (isPlaying) {
            String url = "qmhGwzfD-Q4";
            view.playYoutube(url);
        } else {
            // TODO: 8/19/17 - idk man, intent pause it.
        }

        isPlaying = !isPlaying;
    }


    @Override
    public void next() {

    }


    @Override
    public void previous() {

    }
}
