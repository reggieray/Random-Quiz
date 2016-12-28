package com.matthewregis.randomquiz.injection.component;

import com.matthewregis.randomquiz.injection.ConfigPersistent;
import com.matthewregis.randomquiz.injection.module.ActivityModule;

import dagger.Component;

/**
 * Created by reg on 27/12/2016.
 */

@ConfigPersistent
@Component(dependencies = ApplicationComponent.class)
public interface ConfigPersistentComponent {

    ActivityComponent activityComponent(ActivityModule activityModule);

}
