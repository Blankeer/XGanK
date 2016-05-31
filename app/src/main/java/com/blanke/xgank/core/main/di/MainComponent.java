package com.blanke.xgank.core.main.di;

import com.blanke.xgank.app.di.AppComponent;
import com.blanke.xgank.app.di.scopes.PerActivity;
import com.blanke.xgank.core.column.di.ColumnComponent;
import com.blanke.xgank.core.main.MainActivity;

import dagger.Component;

/**
 * Created by blanke on 16-5-31.
 */
@PerActivity
@Component(dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);

    ColumnComponent provideColumnComponent();
}
