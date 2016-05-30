package com.blanke.xgank.api.response;

import android.os.Parcelable;

import com.blanke.xgank.bean.Article;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

/**
 * 接口访问的json，包含 error results
 * Created by blanke on 16-5-18.
 */
@AutoValue
public abstract class ArticleResponse implements Parcelable {
    public abstract boolean error();

    public abstract List<Article> results();

    //auto-value-gson扩展
    public static TypeAdapter<ArticleResponse> typeAdapter(Gson gson) {
        return new AutoValue_ArticleResponse.GsonTypeAdapter(gson);
    }
}
