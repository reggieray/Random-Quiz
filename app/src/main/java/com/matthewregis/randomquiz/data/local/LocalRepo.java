package com.matthewregis.randomquiz.data.local;

import android.content.Context;

import com.matthewregis.randomquiz.data.models.GameMode;
import com.matthewregis.randomquiz.data.models.ScoreModel;
import com.matthewregis.randomquiz.injection.ApplicationContext;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by reg on 02/12/2016.
 */
@Singleton
public class LocalRepo implements ILocalRepo {

    Realm mRealm;

    public LocalRepo() {
        mRealm = Realm.getDefaultInstance();
    }

    @Inject
    public LocalRepo(@ApplicationContext Context context){
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public void SaveHighScores(ScoreModel scoreModel) {
        mRealm.beginTransaction();
        ScoreModel ScoresModel = mRealm.copyToRealm(scoreModel);
        mRealm.commitTransaction();
    }

    @Override
    public void DeleteScore(ScoreModel scoreModel) {
        mRealm.beginTransaction();
        mRealm.where(ScoreModel.class).equalTo("Id", scoreModel.getId()).findFirst().deleteFromRealm();
        mRealm.commitTransaction();
    }

    @Override
    public void DeleteAllScore() {
        mRealm.beginTransaction();
        mRealm.where(ScoreModel.class).findAll().deleteAllFromRealm();
        mRealm.commitTransaction();
    }

    @Override
    public ScoreModel GetHighScoreForSuddenDeath() {
        RealmResults<ScoreModel> topScoreModels = mRealm.where(ScoreModel.class).equalTo("GameMode", GameMode.SUDDEN_DEATH.toString()).findAllSorted("Score", Sort.DESCENDING);
        if (topScoreModels.size() > 0) {
            return topScoreModels.first();
        } else {
            ScoreModel scoreModel = new ScoreModel();
            scoreModel.setScore(0);
            return scoreModel;
        }
    }

    @Override
    public ScoreModel GetHighScoreForRandom10() {
        RealmResults<ScoreModel> topScoreModels = mRealm.where(ScoreModel.class).equalTo("GameMode", GameMode.RANDOM_10.toString()).findAllSorted("Score", Sort.DESCENDING);
        if (topScoreModels.size() > 0) {
            return topScoreModels.first();
        } else {
            ScoreModel scoreModel = new ScoreModel();
            scoreModel.setScore(0);
            return scoreModel;
        }
    }

    @Override
    public List<ScoreModel> GetRandom10TopScores() {
        RealmResults<ScoreModel> topScoreModels = mRealm.where(ScoreModel.class).equalTo("GameMode", GameMode.RANDOM_10.toString()).findAllSorted("Score", Sort.DESCENDING);
        return topScoreModels;
    }

    @Override
    public List<ScoreModel> GetSuddenDeathTopScores() {
        RealmResults<ScoreModel> topScoreModels = mRealm.where(ScoreModel.class).equalTo("GameMode", GameMode.SUDDEN_DEATH.toString()).findAllSorted("Score", Sort.DESCENDING);
        return topScoreModels;
    }

    @Override
    public List<ScoreModel> GetTopScores() {
        RealmResults<ScoreModel> topScoreModels = mRealm.where(ScoreModel.class).findAllSorted("Score", Sort.DESCENDING);
        return topScoreModels;
    }


}