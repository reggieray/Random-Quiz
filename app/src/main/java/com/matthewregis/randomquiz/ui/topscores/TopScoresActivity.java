package com.matthewregis.randomquiz.ui.topscores;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.matthewregis.randomquiz.R;
import com.matthewregis.randomquiz.data.models.ScoreModel;
import com.matthewregis.randomquiz.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopScoresActivity extends BaseActivity implements ITopScoresView {

    @Inject
    TopScoresPresenter mTopScoresPresenter;

    @BindView(R.id.top_scores_recycler_view)
    RecyclerView topScoresRecyclerView;

    TopScoresListAdapter mAdapter;
    @BindView(R.id.top_scores_empty_view)
    TextView topScoresEmptyView;

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

    @Override
    public void ShowListView() {
        topScoresEmptyView.setVisibility(View.GONE);
        topScoresRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void ShowEmptyView() {
        topScoresRecyclerView.setVisibility(View.GONE);
        topScoresEmptyView.setVisibility(View.VISIBLE);
    }
}
