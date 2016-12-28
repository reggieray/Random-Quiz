package com.matthewregis.randomquiz.ui.main;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by reg on 28/11/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTests {

    MainPresenter mainPresenter;

    @Mock
    IMainView mMainView;
    @Mock
    Context mContext;


    @Before
    public void SetUp() throws Exception{
        mainPresenter = new MainPresenter(mContext);
        mainPresenter.attachView(mMainView);
    }

    @After
    public void TearDown() throws Exception{
        mainPresenter.detachView();
    }

    @Test
    public void ShouldStartRandom10QuizOnRandomQuizBtnOnClick() throws Exception {
        mainPresenter.onRandom10BtnOnClick();
        verify(mMainView, times(1)).StartRandom10Quiz();
    }

    @Test
    public void ShouldStartSuddenDeathQuizOnSuddenDeathBtnOnClick() throws Exception {
        mainPresenter.onSuddenDeathBtnOnClick();
        verify(mMainView, times(1)).StartSuddenDeathQuiz();
    }

    @Test
    public void ShouldShowTopScoresOnTopScoresBtnOnClick() throws Exception {
        mainPresenter.onTopScoresBtnOnClick();
        verify(mMainView, times(1)).ShowTopScores();
    }


}
