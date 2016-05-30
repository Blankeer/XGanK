package com.blanke.xgank.core.column.persenter;

import com.blanke.xgank.api.GanKAPI;
import com.blanke.xgank.api.response.ArticleResponse;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by blanke on 16-5-30.
 */
public class ColumnPersenterImpl extends ColumnPersenter {

    private GanKAPI mGanKAPI;

    @Inject
    public ColumnPersenterImpl(GanKAPI mGanKAPI) {
        this.mGanKAPI = mGanKAPI;
    }

    @Override
    public void getAllArticle(String type, int size, int page) {
        mGanKAPI.getArticles(type, size, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(ArticleResponse::results)
                .filter(articles -> getView() != null)
                .subscribe(getView()::setData);
    }
}
