package com.blanke.xgank.core.column;

import android.widget.FrameLayout;

import com.blanke.xgank.base.BaseMvpLceViewStateFragment;
import com.blanke.xgank.bean.Article;
import com.blanke.xgank.core.column.persenter.ColumnPersenter;
import com.blanke.xgank.core.column.view.ColumnView;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;

import java.util.List;

/**
 * Created by blanke on 16-5-30.
 */
public class ColumnFragment
        extends BaseMvpLceViewStateFragment
        <FrameLayout, List<Article>, ColumnView, ColumnPersenter> {

    @Override
    public LceViewState<List<Article>, ColumnView> createViewState() {
        return new RetainingLceViewState<>();
    }

    @Override
    public List<Article> getData() {
        return null;
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }

    @Override
    public ColumnPersenter createPresenter() {
        return null;
    }

    @Override
    public void setData(List<Article> data) {

    }

    @Override
    public void loadData(boolean pullToRefresh) {

    }
}
