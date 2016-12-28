package com.matthewregis.randomquiz.ui.topScores;

import android.content.Context;
import android.support.annotation.NonNull;

import com.matthewregis.randomquiz.data.local.ILocalRepo;
import com.matthewregis.randomquiz.data.local.LocalRepo;
import com.matthewregis.randomquiz.data.models.ScoreModel;
import com.matthewregis.randomquiz.ui.topscores.ITopScoresView;
import com.matthewregis.randomquiz.ui.topscores.TopScoresPresenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by reg on 28/11/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class TopScoresPresenterTests {

    TopScoresPresenter topScoresPresenter;

    @Mock
    ITopScoresView mTopScoresView;
    @Mock
    LocalRepo mLocalRepo;
    @Mock
    Context mContext;


    @Before
    public void SetUp() throws Exception {
        topScoresPresenter = new TopScoresPresenter(mLocalRepo, mContext);
        topScoresPresenter.attachView(mTopScoresView);
    }

    @After
    public void TearDown() throws Exception{
        topScoresPresenter.detachView();
    }

    @Test
    public void ShouldSetTopScoresOnCreated() throws Exception{
        List<ScoreModel> scoreModels = new ArrayList<>();
        when(mLocalRepo.GetTopScores()).thenReturn(scoreModels);
        topScoresPresenter.onCreated();
        verify(mTopScoresView,times(1)).SetTopScoresListView(scoreModels);
    }
}
