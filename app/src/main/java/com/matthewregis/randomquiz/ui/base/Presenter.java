package com.matthewregis.randomquiz.ui.base;

/**
 * Created by reg on 27/12/2016.
 */

public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}
