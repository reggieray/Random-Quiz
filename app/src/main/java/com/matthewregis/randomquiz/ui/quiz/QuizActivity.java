package com.matthewregis.randomquiz.ui.quiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.matthewregis.randomquiz.R;
import com.matthewregis.randomquiz.data.models.GameMode;
import com.matthewregis.randomquiz.ui.base.BaseActivity;
import com.matthewregis.randomquiz.ui.main.MainActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class QuizActivity extends BaseActivity implements IQuizView {

    @Inject QuizPresenter mQuizPresenter;

    @BindView(R.id.quiz_question)
    TextView quizQuestion;
    @BindView(R.id.quiz_answer_area)
    LinearLayout quizAnswerArea;
    @BindView(R.id.main_question_layout)
    LinearLayout mainQuestionLayout;
    @BindView(R.id.quiz_progess_bar)
    ProgressBar quizProgessBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.i("QuizActivity First");
        activityComponent().inject(this);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);

        mQuizPresenter.attachView(this);
        Intent intent = getIntent();
        if (intent.hasExtra(MainActivity.EXTRA_MESSAGE)) {
            try {
                String gameMode = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
                if (gameMode.equalsIgnoreCase(getString(R.string.game_mode_sudden_death))) {
                    mQuizPresenter.setGameMode(GameMode.SUDDEN_DEATH);
                } else {
                    mQuizPresenter.setGameMode(GameMode.RANDOM_10);
                }

            } catch (Exception ex) {
                //Couldn't read extra message
            }
        }

        mQuizPresenter.getRandom10Quiz();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mQuizPresenter.detachView();
    }

    @Override
    public void ShowLoading() {
        mainQuestionLayout.setVisibility(View.GONE);
        quizProgessBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void DismissLoading() {
        quizProgessBar.setVisibility(View.GONE);
        mainQuestionLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void SetUpQuestion(String question) {
        quizQuestion.setText(Html.fromHtml(question));
    }

    @Override
    public void SetUpAnswers(List<Button> answers) {

        for (Button answer : answers) {
            answer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mQuizPresenter.onAnswerSelection(view.getTag());
                    mQuizPresenter.checkEndQuiz();
                }
            });
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, 30);
            answer.setLayoutParams(params);
            answer.setBackgroundResource(R.drawable.btn_layout);
            answer.setTextColor(getResources().getColor(R.color.white));
            quizAnswerArea.addView(answer);
        }
    }

    @Override
    public void ShowToastNotification(String message) {
        Toast.makeText(QuizActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void CloseQuiz() {
        finish();
    }

    @Override
    public void ShowEndOfQuizMessage(String message) {
        new AlertDialog.Builder(QuizActivity.this)
                .setTitle("End of Quiz :(")
                .setMessage(message)
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        mQuizPresenter.onEndOfQuizMessageDismiss();
                    }
                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mQuizPresenter.onEndOfQuizMessageOk();
                    }
                })
                .show();
    }

    @Override
    public void ShowTopScoreEndOfQuizMessage(String message, final int score) {

        final EditText input = new EditText(QuizActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        lp.setMargins(20,0,20,0);
        input.setLayoutParams(lp);


        new AlertDialog.Builder(QuizActivity.this)
                .setTitle("New Top Score! :)")
                .setMessage(message)
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        mQuizPresenter.onEndOfQuizMessageDismiss();
                    }
                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mQuizPresenter.SaveNewTopScore(score, input.getText().toString());
                        mQuizPresenter.onEndOfQuizMessageOk();
                    }
                })
                .setView(input)
                .show();

    }

    @Override
    public void ClearQuestionAndAnswers() {
        quizQuestion.setText(":( - Sorry no question here.");
        quizAnswerArea.removeAllViews();
    }

    @Override
    public void ShowCorrectToast() {
        Toast toast = new Toast(QuizActivity.this);
        ImageView view = new ImageView(QuizActivity.this);
        view.setImageResource(R.drawable.tick);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void ShowWrongToast() {
        Toast toast = new Toast(QuizActivity.this);
        ImageView view = new ImageView(QuizActivity.this);
        view.setImageResource(R.drawable.cross);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}
