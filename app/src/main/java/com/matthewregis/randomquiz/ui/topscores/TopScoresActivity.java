package com.matthewregis.randomquiz.ui.topscores;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.matthewregis.randomquiz.R;
import com.matthewregis.randomquiz.data.models.ScoreModel;
import com.matthewregis.randomquiz.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopScoresActivity extends BaseActivity implements ITopScoresView {

    @Inject TopScoresPresenter mTopScoresPresenter;

    @BindView(R.id.top_scores_recycler_view)
    android.support.v7.widget.RecyclerView topScoresRecyclerView;

    TopScoresListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_top_scores);
        ButterKnife.bind(this);
        mTopScoresPresenter.attachView(this);
        mTopScoresPresenter.onCreated();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTopScoresPresenter.detachView();
    }

    @Override
    public void SetTopScoresListView(List<ScoreModel> scoreModelList) {
        mAdapter = new TopScoresListAdapter(scoreModelList);
        topScoresRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        topScoresRecyclerView.setAdapter(mAdapter);
    }
}
