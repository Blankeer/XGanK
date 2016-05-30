package com.blanke.xgank.bean;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.squareup.sqldelight.ColumnAdapter;

import org.threeten.bp.ZonedDateTime;

/**
 * Created by blanke on 16-5-27.
 */
@AutoValue
public abstract class Article implements ArticleModel, Parcelable {
    public final static Mapper<Article> MAPPER =
            new Mapper<>(AutoValue_Article::new, null, null);

    public final static class Marshal extends ArticleMarshal<Marshal> {

        public Marshal(ColumnAdapter<ZonedDateTime> publishedAtAdapter, ColumnAdapter<ZonedDateTime> createdAtAdapter) {
            super(publishedAtAdapter, createdAtAdapter);
        }

        public Marshal(ArticleModel copy, ColumnAdapter<ZonedDateTime> publishedAtAdapter, ColumnAdapter<ZonedDateTime> createdAtAdapter) {
            super(copy, publishedAtAdapter, createdAtAdapter);
        }
    }

    public static TypeAdapter<Article> typeAdapter(Gson gson) {
        return new AutoValue_Article.GsonTypeAdapter(gson);
    }
}
