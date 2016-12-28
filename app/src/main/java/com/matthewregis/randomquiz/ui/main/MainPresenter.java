package com.matthewregis.randomquiz.ui.main;

import android.content.Context;

import com.matthewregis.randomquiz.injection.ApplicationContext;
import com.matthewregis.randomquiz.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by reg on 27/11/2016.
 */

public class MainPresenter extends BasePresenter<IMainView> {

    Context mContext;

    @Inject
    public MainPresenter(@ApplicationContext Context context){
        this.mContext = context;
    }

    @Override
    public void attachView(IMainView mainView){
        super.attachView(mainView);
    }

    public void onRandom10BtnOnClick() {
        getMvpView().StartRandom10Quiz();
    }


    public void onSuddenDeathBtnOnClick() {
        getMvpView().StartSuddenDeathQuiz();
    }


    public void onTopScoresBtnOnClick() {
        getMvpView().ShowTopScores();
    }
}
