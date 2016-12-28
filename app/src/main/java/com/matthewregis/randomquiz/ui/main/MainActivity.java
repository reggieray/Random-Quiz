package com.matthewregis.randomquiz.ui.main;

import android.content.Intent;
import android.os.Bundle;

import com.matthewregis.randomquiz.R;
import com.matthewregis.randomquiz.ui.base.BaseActivity;
import com.matthewregis.randomquiz.ui.quiz.QuizActivity;
import com.matthewregis.randomquiz.ui.topscores.TopScoresActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements IMainView {
    public final static String EXTRA_MESSAGE = "com.matthewregis.randomquiz.MESSAGE";

    @Inject MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMainPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
    }

    @OnClick(R.id.main_btn_random_10)
    void BtnRandom10() {
        mMainPresenter.onRandom10BtnOnClick();
    }

    @OnClick(R.id.main_btn_sudden_death)
    void BtnSuddenDeath() {
        mMainPresenter.onSuddenDeathBtnOnClick();
    }

    @OnClick(R.id.main_btn_top_scores)
    void BtnTopScores() {
        mMainPresenter.onTopScoresBtnOnClick();
    }

    @Override
    public void StartRandom10Quiz() {
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }

    @Override
    public void StartSuddenDeathQuiz() {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra(EXTRA_MESSAGE, getString(R.string.game_mode_sudden_death));
        startActivity(intent);
    }

    @Override
    public void ShowTopScores() {
        Intent intent = new Intent(this, TopScoresActivity.class);
        startActivity(intent);
    }
}
