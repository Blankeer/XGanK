package com.blanke.xgank.core.column;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ImageView;

import com.blanke.xgank.R;
import com.blanke.xgank.base.BaseMvpLceViewStateFragment;
import com.blanke.xgank.bean.Article;
import com.blanke.xgank.config.ProjectConfig;
import com.blanke.xgank.core.column.persenter.ColumnPersenter;
import com.blanke.xgank.core.column.persenter.ColumnPersenterImpl;
import com.blanke.xgank.core.column.view.ColumnView;
import com.blanke.xgank.core.main.MainActivity;
import com.blanke.xgank.core.main.di.MainComponent;
import com.blanke.xgank.utils.ImgUtils;
import com.bumptech.glide.Glide;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;
import com.neu.refresh.NeuSwipeRefreshLayout;
import com.neu.refresh.NeuSwipeRefreshLayoutDirection;
import com.yatatsu.autobundle.AutoBundleField;

import org.byteam.superadapter.SimpleMulItemViewType;
import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.internal.SuperViewHolder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.ScaleInLeftAnimator;

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
    NeuSwipeRefreshLayout mSwipeRefreshLayout;
    @Inject
    ColumnPersenterImpl mColumnPersenter;
    private List<Article> mArticles;
    private SuperAdapter<Article> mAdapter;
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
        mColumnRecyclerview.setHasFixedSize(true);
        mColumnRecyclerview.setItemAnimator(new ScaleInLeftAnimator());
        mColumnRecyclerview.setLayoutManager
                (new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new SuperAdapter<Article>(getActivity(), null, new SimpleMulItemViewType<Article>() {
            @Override
            public int getItemViewType(int position, Article article) {
                return ImgUtils.isImg(article.url()) ? 0 : 1;
            }

            @Override
            public int getLayoutId(int viewType) {
                return viewType == 0 ? R.layout.item_column_img : R.layout.item_column_text;
            }
        }) {
            @Override
            public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, Article item) {
                if (viewType == 0) {//img
                    ImageView iv = holder.getView(R.id.item_img);
                    Glide.with(ColumnFragment.this)
                            .load(item.url())
                            .into(iv);
                } else {
                    holder.setText(R.id.item_title, item.url());
                    holder.setText(R.id.item_type, item.type());
//                    holder.setText(R.id.item_time,item.type());
                }
            }
        };
        mColumnRecyclerview.setAdapter(new ScaleInAnimationAdapter(mAdapter));
        mArticles = new ArrayList<>();
        mSwipeRefreshLayout.setDirection(NeuSwipeRefreshLayoutDirection.BOTH);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);

        mSwipeRefreshLayout.setOnRefreshListener(new NeuSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(NeuSwipeRefreshLayoutDirection direction) {
                if (direction == NeuSwipeRefreshLayoutDirection.TOP) {
                    page = 0;
                }
                loadData(true);
            }
        });
    }

    private void stopRefreLoad() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
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
        if (page == 0) {
            mArticles.clear();
            mAdapter.clear();
            mAdapter.notifyDataSetChanged();//不要这句会报错
        }
        mArticles.addAll(data);
        mAdapter.addAll(data);
        page++;
        stopRefreLoad();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        mColumnPersenter.getAllArticle(pullToRefresh, type, ProjectConfig.PAGE_SIZE, page);
    }
}
