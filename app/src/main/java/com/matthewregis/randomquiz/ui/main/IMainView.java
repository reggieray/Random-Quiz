package com.matthewregis.randomquiz.ui.main;

import com.matthewregis.randomquiz.ui.base.MvpView;

/**
 * Created by reg on 27/11/2016.
 */

public interface IMainView extends MvpView{

    void StartRandom10Quiz();

    void StartSuddenDeathQuiz();

    void ShowTopScores();

}
