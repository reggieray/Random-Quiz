package com.matthewregis.randomquiz.ui.quiz;

import android.widget.Button;

import com.matthewregis.randomquiz.ui.base.MvpView;

import java.util.List;

/**
 * Created by reg on 27/11/2016.
 */

public interface IQuizView extends MvpView {

    void ShowLoading();

    void DismissLoading();

    void SetUpQuestion(String question);

    void SetUpAnswers(List<Button> answers);

    void ShowToastNotification(String message);

    void CloseQuiz();

    void ShowEndOfQuizMessage(String message);

    void ShowTopScoreEndOfQuizMessage(String message, int score);

    void ClearQuestionAndAnswers();

    void ShowCorrectToast();

    void ShowWrongToast();

}
