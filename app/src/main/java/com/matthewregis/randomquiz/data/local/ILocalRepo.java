package com.matthewregis.randomquiz.data.local;

import com.matthewregis.randomquiz.data.models.ScoreModel;

import java.util.List;

/**
 * Created by reg on 02/12/2016.
 */

public interface ILocalRepo {

    void SaveHighScores(ScoreModel scoreModel);

    void DeleteScore(ScoreModel scoreModel);

    void DeleteAllScore();

    ScoreModel GetHighScoreForSuddenDeath();

    ScoreModel GetHighScoreForRandom10();

    List<ScoreModel> GetRandom10TopScores();

    List<ScoreModel> GetSuddenDeathTopScores();

    List<ScoreModel> GetTopScores();

}
