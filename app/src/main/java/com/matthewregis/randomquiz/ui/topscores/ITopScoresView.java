package com.matthewregis.randomquiz.ui.topscores;

import com.matthewregis.randomquiz.data.models.ScoreModel;
import com.matthewregis.randomquiz.ui.base.MvpView;

import java.util.List;

/**
 * Created by reg on 28/11/2016.
 */

public interface ITopScoresView extends MvpView{

    void SetTopScoresListView(List<ScoreModel> scoreModelList);

}
