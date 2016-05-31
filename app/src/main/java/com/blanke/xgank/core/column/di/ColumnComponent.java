package com.blanke.xgank.core.column.di;

import com.blanke.xgank.app.di.scopes.PerActivity;
import com.blanke.xgank.core.column.ColumnFragment;

import dagger.Subcomponent;

/**
 * Created by blanke on 16-5-31.
 */
@Subcomponent
@PerActivity
public interface ColumnComponent {
    void inject(ColumnFragment columnFragment);

}
