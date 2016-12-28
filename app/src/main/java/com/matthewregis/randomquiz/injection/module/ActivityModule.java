package com.matthewregis.randomquiz.injection.module;

import android.app.Activity;
import android.content.Context;

import com.matthewregis.randomquiz.injection.ActivityContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by reg on 27/12/2016.
 */

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return mActivity;
    }
}