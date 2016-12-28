package com.matthewregis.randomquiz.injection.component;

import com.matthewregis.randomquiz.injection.PerActivity;
import com.matthewregis.randomquiz.injection.module.ActivityModule;
import com.matthewregis.randomquiz.ui.main.MainActivity;
import com.matthewregis.randomquiz.ui.quiz.QuizActivity;
import com.matthewregis.randomquiz.ui.topscores.TopScoresActivity;

import dagger.Subcomponent;

/**
 * Created by reg on 27/12/2016.
 */

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(QuizActivity quizActivity);

    void inject(TopScoresActivity topScoresActivity);
}