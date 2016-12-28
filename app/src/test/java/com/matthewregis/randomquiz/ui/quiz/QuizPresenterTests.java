package com.matthewregis.randomquiz.ui.quiz;

import android.content.Context;

import com.matthewregis.randomquiz.R;
import com.matthewregis.randomquiz.data.local.LocalRepo;
import com.matthewregis.randomquiz.data.models.GameMode;
import com.matthewregis.randomquiz.data.models.ScoreModel;
import com.matthewregis.randomquiz.data.remote.OpentdbApi;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by reg on 28/11/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class QuizPresenterTests {

    private QuizPresenter quizPresenter;

    @Mock
    Context mContext;
    @Mock
    LocalRepo mLocalRepo;
    @Mock
    OpentdbApi mOpentdbApi;


    @Mock
    IQuizView mQuizView;


    @Before
    public void SetUp() throws Exception {
        quizPresenter = new QuizPresenter(mContext, mLocalRepo, mOpentdbApi);
        quizPresenter.attachView(mQuizView);
    }

    @After
    public void TearDown() throws Exception{
        quizPresenter.detachView();
    }

    @Test
    public void ShouldDismissLoadingDialogShowErrorMessageCloseQuizOnFailureToGetQuiz() throws Exception {
        //setup
        String msg = "Test error message";
        when(mContext.getString(R.string.unable_to_get_questions_message)).thenReturn(msg);
        //call method to test
        quizPresenter.onGetRandom10QuizOnFailure(400, null, null);
        //verify it did what it was expected to do
        verify(mQuizView, times(1)).DismissLoading();
        verify(mQuizView, times(1)).ShowToastNotification(msg);
        verify(mQuizView, times(1)).CloseQuiz();
    }

    @Test
    public void ShouldCloseQuizOnRandomObjectAnswer() throws Exception {
        String msg = "Whats going on";
        when(mContext.getString(R.string.what_was_that)).thenReturn(msg);
        quizPresenter.onAnswerSelection(12);
        verify(mQuizView, times(1)).ShowToastNotification(msg);
        verify(mQuizView, times(1)).CloseQuiz();
    }

    @Test
    public void ShouldShowCorrectNotificationOnTrueAnswer() throws Exception {
        quizPresenter.onAnswerSelection(true);
        verify(mQuizView,times(1)).ClearQuestionAndAnswers();
        verify(mQuizView, times(1)).ShowCorrectToast();
    }

    @Test
    public void ShouldShowWrongNotificationOnFalseAnswer() throws Exception {
        quizPresenter.onAnswerSelection(false);
        verify(mQuizView,times(1)).ClearQuestionAndAnswers();
        verify(mQuizView, times(1)).ShowWrongToast();
    }

    @Test
    public void ShouldTopScoreMessageAfterAnswered10CorrectQuestionsOnGameModeRandom10() throws Exception {
        ScoreModel scoreModel = new ScoreModel();
        int range = (10 - 1) + 1;
        int scoreModelTopScore =  (int)(Math.random() * range) + 1;
        scoreModel.setScore(scoreModelTopScore);
        when(mLocalRepo.GetHighScoreForRandom10()).thenReturn(scoreModel);
        quizPresenter.setGameMode(GameMode.RANDOM_10);
        for (int i = 0; i < 10; i++) {
            quizPresenter.onAnswerSelection(true);
        }
        quizPresenter.checkEndQuiz();
        verify(mQuizView, times(1)).ShowTopScoreEndOfQuizMessage("New Random 10 Top Score Of 10.\nPlease Enter A Name:", 10);
    }

    @Test
    public void ShouldPopUpEndMessageAnswered8CorrectQuestionsOnGameModeRandom10() throws Exception {
        ScoreModel scoreModel = new ScoreModel();
        scoreModel.setScore(10);
        quizPresenter.setGameMode(GameMode.RANDOM_10);
        when(mLocalRepo.GetHighScoreForRandom10()).thenReturn(scoreModel);

        for (int i = 0; i < 10; i++) {
            if (i < 8) {
                quizPresenter.onAnswerSelection(true);
            } else {
                quizPresenter.onAnswerSelection(false);
            }
        }
        quizPresenter.checkEndQuiz();
        verify(mQuizView, times(1)).ShowEndOfQuizMessage("You got 8 questions right.\n" + "You got 2 question wrong.");
    }

    @Test
    public void ShouldNotEndQuizIfAnswered10QuestionsOnGameModeRandom10() throws Exception {
        quizPresenter.setGameMode(GameMode.RANDOM_10);
        for (int i = 0; i < 8; i++) {
            quizPresenter.onAnswerSelection(true);
        }
        quizPresenter.checkEndQuiz();
        verify(mQuizView, times(0)).CloseQuiz();
    }

    @Test
    public void ShouldShowEndOfQuizIfAnsweredWrongOnGameModeSuddenDeath() throws Exception {
        ScoreModel scoreModel = new ScoreModel();
        scoreModel.setScore(1);
        when(mLocalRepo.GetHighScoreForSuddenDeath()).thenReturn(scoreModel);
        quizPresenter.setGameMode(GameMode.SUDDEN_DEATH);
        quizPresenter.onAnswerSelection(false);
        quizPresenter.checkEndQuiz();
        verify(mQuizView, times(1)).ShowEndOfQuizMessage("You got 0 questions right.");
    }

}
