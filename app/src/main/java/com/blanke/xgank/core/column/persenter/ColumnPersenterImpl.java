package com.blanke.xgank.core.column.persenter;

import com.blanke.xgank.api.GanKAPI;
import com.blanke.xgank.api.response.ArticleResponse;
import com.blanke.xgank.bean.Article;
import com.blanke.xgank.core.column.view.ColumnView;
import com.socks.library.KLog;

import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
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
    public void getAllArticle(boolean pullToRefresh, String type, int size, int page) {
        ColumnView view = getView();
        if (view != null) {
            view.showLoading(pullToRefresh);
            mGanKAPI.getArticles(type, size, page)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .map(ArticleResponse::results)
                    .filter(articles -> getView() != null)
                    .subscribe(new Action1<List<Article>>() {
                        @Override
                        public void call(List<Article> articles) {
                            getView().setData(articles);
                            getView().showContent();
                            KLog.d(articles);
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            KLog.e(throwable);
                            getView().showError(throwable, pullToRefresh);
                        }
                    });
        }
    }
}
