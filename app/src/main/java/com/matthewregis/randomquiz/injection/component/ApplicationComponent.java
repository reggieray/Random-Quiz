package com.matthewregis.randomquiz.injection.component;

import android.app.Application;
import android.content.Context;

import com.matthewregis.randomquiz.RandomQuizApplication;
import com.matthewregis.randomquiz.data.local.LocalRepo;
import com.matthewregis.randomquiz.data.remote.OpentdbApi.OpentdbApi;
import com.matthewregis.randomquiz.injection.ApplicationContext;
import com.matthewregis.randomquiz.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by reg on 27/12/2016.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();
    Application application();
    LocalRepo localReop();
    OpentdbApi opentbApi();
}