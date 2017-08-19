package blankthings.musicthing.ui.presenters;

import blankthings.musicthing.ui.views.ViewContract;

/**
 * Created by iosif on 8/18/17.
 */

public abstract class BasePresenter<V extends ViewContract> implements PresenterContract {

    V view;

    public BasePresenter(V view) {
        this.view = view;
    }

}
