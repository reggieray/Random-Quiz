package com.matthewregis.randomquiz.ui.quiz;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.widget.Button;

import com.google.gson.Gson;
import com.loopj.android.http.BuildConfig;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.matthewregis.randomquiz.R;
import com.matthewregis.randomquiz.data.local.ILocalRepo;
import com.matthewregis.randomquiz.data.local.LocalRepo;
import com.matthewregis.randomquiz.data.models.GameMode;
import com.matthewregis.randomquiz.data.models.QuizModel;
import com.matthewregis.randomquiz.data.models.ResultsBean;
import com.matthewregis.randomquiz.data.models.ScoreModel;
import com.matthewregis.randomquiz.data.remote.OpentdbApi.OpentdbApi;
import com.matthewregis.randomquiz.injection.ApplicationContext;
import com.matthewregis.randomquiz.injection.ConfigPersistent;
import com.matthewregis.randomquiz.ui.base.BasePresenter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.UUID;

import javax.inject.Inject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by reg on 27/11/2016.
 */
@ConfigPersistent
public class QuizPresenter extends BasePresenter<IQuizView> {

    private Context mContext;
    private ILocalRepo mLocalRepo;

    OpentdbApi mOpentdbApi;
    Queue<ResultsBean> questions;
    List<Boolean> answered;

    GameMode mGameMode;

    @Inject
    public QuizPresenter(@ApplicationContext Context context, LocalRepo localRepo, OpentdbApi opentdbApi) {
        this.mContext = context;
        this.mLocalRepo = localRepo;
        this.mOpentdbApi = opentdbApi;
        this.initialize();
    }

    @Override
    public void attachView(IQuizView quizView){
        super.attachView(quizView);
    }

    private void initialize() {
        questions = new LinkedList<ResultsBean>();
        answered = new ArrayList<>();
        mGameMode = GameMode.RANDOM_10; //Default to Random 10
    }


    public void getRandom10Quiz() {
        getMvpView().ShowLoading();
        mOpentdbApi.Get10RandomQuestions(new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                if (BuildConfig.DEBUG) {
                    Log.d("http-error", String.format("{url:%s, statusCode:%d}", this.getRequestURI(), statusCode));
                }
                onGetRandom10QuizOnSuccess(statusCode, headers, response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                if (BuildConfig.DEBUG) {
                    Log.d("http-error", String.format("{url:%s, statusCode:%d}", this.getRequestURI(), statusCode));
                }
                onGetRandom10QuizOnFailure(statusCode, headers, throwable);
            }


        });
    }


    public void onGetRandom10QuizOnSuccess(int statusCode, Header[] headers, JSONObject response) {
        getMvpView().DismissLoading();
        try {
            Log.d("quiz model", response.toString());
            Gson myGson = new Gson();
            QuizModel quizModel = myGson.fromJson(response.toString(), QuizModel.class);
            setUpQuizQuestionQueue(quizModel);
            loadQuestion();
        } catch (Exception ex) {
            //Log Exception
        }
    }


    public void onGetRandom10QuizOnFailure(int statusCode, Header[] headers, Throwable throwable) {
        if (BuildConfig.DEBUG) {
            Log.d("throwable", throwable.getMessage());
        }
        getMvpView().DismissLoading();
        getMvpView().ShowToastNotification(mContext.getString(R.string.unable_to_get_questions_message));
        getMvpView().CloseQuiz();
    }


    public void setUpQuizQuestionQueue(QuizModel quizModel) {
        if (String.valueOf(quizModel.getResponse_code()).equalsIgnoreCase("0")) {
            for (ResultsBean question : quizModel.getResults()) {
                questions.add(question);
            }
        }
    }


    public void loadQuestion() {
        if(questions.size() < 1){
            return;
        }
        ResultsBean question = questions.poll();
        getMvpView().SetUpQuestion(question.getQuestion());
        List<Button> answerButtons = new ArrayList<>();
        Button CorrectAnswer = new Button(mContext);
        CorrectAnswer.setText(Html.fromHtml(question.getCorrect_answer()));
        CorrectAnswer.setTag(true);

        answerButtons.add(CorrectAnswer);

        for (String answerString : question.getIncorrect_answers()) {
            Button WrongAnswer = new Button(mContext);
            WrongAnswer.setText(Html.fromHtml((answerString)));
            WrongAnswer.setTag(false);
            answerButtons.add(WrongAnswer);
        }

        Collections.shuffle(answerButtons);
        getMvpView().SetUpAnswers(answerButtons);
    }


    public void onAnswerSelection(Object selectedAnswer) {
        getMvpView().ClearQuestionAndAnswers();
        try {
            Boolean isCorrect = (Boolean) selectedAnswer;
            answered.add(isCorrect);
            if (isCorrect) {
                getMvpView().ShowCorrectToast();
            } else {
                getMvpView().ShowWrongToast();
            }
        } catch (Exception ex) {
            getMvpView().ShowToastNotification(mContext.getString(R.string.what_was_that));
            getMvpView().CloseQuiz();
        }
    }


    public void setGameMode(GameMode gameMode) {
        this.mGameMode = gameMode;
    }


    public void checkEndQuiz() {
        if(mGameMode==GameMode.RANDOM_10){
            if(answered.size()>9){
                int correct = 0;
                int wrong = 0;
                for (Boolean ans: answered) {
                    if(ans){
                        correct++;
                    }else{
                        wrong++;
                    }
                }
                int topScore = mLocalRepo.GetHighScoreForRandom10().getScore();
                if(correct > topScore || (correct > 9)){
                    getMvpView().ShowTopScoreEndOfQuizMessage(String.format("New Random 10 Top Score Of %s.\nPlease Enter A Name:", String.valueOf(correct)), correct);
                }else{
                    getMvpView().ShowEndOfQuizMessage(String.format("You got %s questions right.\nYou got %s question wrong.", correct, wrong));
                }
            }else{
                loadQuestion();
            }
        }
        if(mGameMode==GameMode.SUDDEN_DEATH){
            if(answered.contains(false)){
                int score = answered.size() - 1;
                if(score > mLocalRepo.GetHighScoreForSuddenDeath().getScore()){
                    getMvpView().ShowTopScoreEndOfQuizMessage(String.format("New Sudden Death Top Score Of %s.\nPlease Enter A Name:", String.valueOf(score)), score);
                }else{
                    getMvpView().ShowEndOfQuizMessage(String.format("You got %s questions right.", String.valueOf(score)));
                }
            }else{
                if(questions.size()<1){
                    getRandom10Quiz();
                }else{
                    loadQuestion();
                }
            }
        }
    }


    public void onEndOfQuizMessageDismiss() {
        getMvpView().CloseQuiz();
    }


    public void onEndOfQuizMessageOk() {
        getMvpView().CloseQuiz();
    }


    public void SaveNewTopScore(int score, String Name) {
        ScoreModel scoreModel = new ScoreModel();
        scoreModel.setId(UUID.randomUUID().toString());
        scoreModel.setScore(score);
        scoreModel.setName(Name);
        scoreModel.setGameMode(mGameMode.toString());
        mLocalRepo.SaveHighScores(scoreModel);
    }
}
