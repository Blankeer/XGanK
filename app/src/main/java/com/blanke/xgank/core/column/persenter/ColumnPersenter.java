package com.blanke.xgank.core.column.persenter;

import com.blanke.xgank.core.column.view.ColumnView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

/**
 * Created by blanke on 16-5-30.
 */
public abstract class ColumnPersenter extends MvpBasePresenter<ColumnView> {

    public abstract void getAllArticle(String type, int size, int page);
}
