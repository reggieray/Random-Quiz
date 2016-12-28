package com.matthewregis.randomquiz.ui.topscores;

import android.content.Context;

import com.matthewregis.randomquiz.data.local.ILocalRepo;
import com.matthewregis.randomquiz.data.local.LocalRepo;
import com.matthewregis.randomquiz.data.models.ScoreModel;
import com.matthewregis.randomquiz.injection.ApplicationContext;
import com.matthewregis.randomquiz.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by reg on 28/11/2016.
 */

public class TopScoresPresenter extends BasePresenter<ITopScoresView> {

    ILocalRepo mLocalRepo;

    Context mContext;

    @Inject
    public TopScoresPresenter(LocalRepo localRepo, @ApplicationContext Context context){
        this.mContext = context;
        this.mLocalRepo = localRepo;
    }

    @Override
    public void attachView(ITopScoresView topScoresView){
        super.attachView(topScoresView);
    }

    public void onCreated() {
        List<ScoreModel> scoreModelList = mLocalRepo.GetTopScores();
        if(scoreModelList.isEmpty()){
            getMvpView().ShowEmptyView();
        }else{
            getMvpView().SetTopScoresListView(scoreModelList);
            getMvpView().ShowListView();
        }

    }
}
