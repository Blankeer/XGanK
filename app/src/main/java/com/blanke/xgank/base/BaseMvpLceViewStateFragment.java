package com.blanke.xgank.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateFragment;

import butterknife.ButterKnife;

/**
 * Created by Blanke on 16-1-7.
 */
public abstract class BaseMvpLceViewStateFragment<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>> extends MvpLceViewStateFragment<CV, M, V, P> {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
