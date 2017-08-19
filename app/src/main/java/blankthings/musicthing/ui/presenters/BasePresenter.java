package blankthings.musicthing.ui.presenters;

import blankthings.musicthing.ui.views.ViewContract;

/**
 * Created by iosif on 8/18/17.
 */

public abstract class BasePresenter<V extends ViewContract> implements PresenterContract {


    public BasePresenter(V view) {
        if (view == null) {
            throw new NullPointerException("View cannot be null.");
        }
    }

}
