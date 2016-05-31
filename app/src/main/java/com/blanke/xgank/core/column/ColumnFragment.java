package com.blanke.xgank.core.column;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.blanke.xgank.R;
import com.blanke.xgank.base.BaseMvpLceViewStateFragment;
import com.blanke.xgank.bean.Article;
import com.blanke.xgank.config.ProjectConfig;
import com.blanke.xgank.core.column.persenter.ColumnPersenter;
import com.blanke.xgank.core.column.persenter.ColumnPersenterImpl;
import com.blanke.xgank.core.column.view.ColumnView;
import com.blanke.xgank.core.main.MainActivity;
import com.blanke.xgank.core.main.di.MainComponent;
import com.blanke.xgank.utils.ClassAdapterUtils;
import com.blanke.xgank.utils.DateUtils;
import com.blanke.xgank.utils.ImgUtils;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;
import com.yatatsu.autobundle.AutoBundleField;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by blanke on 16-5-30.
 */
public class ColumnFragment
        extends BaseMvpLceViewStateFragment
        <SwipeRefreshLayout, List<Article>, ColumnView, ColumnPersenter>
        implements ColumnView {

    @Bind(R.id.column_recyclerview)
    RecyclerView mColumnRecyclerview;
    @Bind(R.id.contentView)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Inject
    ColumnPersenterImpl mColumnPersenter;
    private List<Article> mArticles;
    private BaseMultiItemQuickAdapter mAdapter;
    private int page = 0;
    @AutoBundleField
    String type;
    MainComponent mainComponent;

    @Override
    public int getLayout() {
        return R.layout.fragment_column;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = getActivity();
        if (activity instanceof MainActivity) {
            mainComponent = ((MainActivity) activity).getMainComponent();
            mainComponent.provideColumnComponent().inject(this);
//            KLog.d("mColumnPersenter: " + (mColumnPersenter == null));
        }
    }

    @Override
    protected void initData() {
        ColumnFragmentAutoBundle.bind(this);
        mAdapter = new ColumnAdapter(getActivity());
        mColumnRecyclerview.setAdapter(mAdapter);
        mArticles = new ArrayList<>();
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            page = 0;
            loadData(false);
        });
    }

    @Override
    public LceViewState<List<Article>, ColumnView> createViewState() {
        return new RetainingLceViewState<>();
    }

    @Override
    public List<Article> getData() {
        return mArticles;
    }

    @Override
    public ColumnPersenter createPresenter() {
        return mColumnPersenter;
    }

    @Override
    public void setData(List<Article> data) {
        this.mArticles.addAll(data);
        mAdapter.addData(new ClassAdapterUtils<Article, ArticleType>() {
            @Override
            public ArticleType to(Article article) {
                return new ArticleType(article);
            }
        }.get(data));
        page++;
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        mColumnPersenter.getAllArticle(pullToRefresh, type, ProjectConfig.PAGE_SIZE, page);
    }

    class ArticleType extends MultiItemEntity {
        public static final int TEXT = 1, IMG = 2;
        public Article data;

        public ArticleType(Article data) {
            this.data = data;
            setItemType(ImgUtils.isImg(data.url()) ? IMG : TEXT);
        }
    }

    public class ColumnAdapter extends BaseMultiItemQuickAdapter<ArticleType> {

        public ColumnAdapter(Context context) {
            super(context, null);
            addItmeType(ArticleType.TEXT, R.layout.item_text);
            addItmeType(ArticleType.IMG, R.layout.item_img);
        }

        @Override
        protected void convert(BaseViewHolder helper, ArticleType item) {
            switch (helper.getItemViewType()) {
                case ArticleType.TEXT:
                    helper.setText(R.id.item_title, item.data.url());
                    helper.setText(R.id.item_tag, item.data.type());
                    helper.setText(R.id.item_time,
                            DateUtils.getTimestampString(item.data.publishedAt()));

                    break;
                case ArticleType.IMG:

                    break;
            }
        }

    }

}
