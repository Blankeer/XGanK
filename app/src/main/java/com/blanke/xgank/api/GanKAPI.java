package com.blanke.xgank.api;


import com.blanke.xgank.api.response.ArticleResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by blanke on 16-5-18.
 */
public interface GanKAPI {
    @GET("{type}/{size}/{page}")
    Observable<ArticleResponse> getArticles(
            @Path("type") String type,
            @Path("size") int size,
            @Path("page") int page);
}
