package com.blanke.xgank.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by blanke on 16-5-31.
 */
public abstract class ClassAdapterUtils<T, M> {
    public abstract M to(T t);

    public M get(T t) {
        return to(t);
    }

    public List<M> get(List<T> ts) {
        List<M> res = null;
        if (ts instanceof ArrayList) {
            res = new ArrayList<>(ts.size());
        } else {
            res = new LinkedList<>();
        }
        for (T t : ts) {
            res.add(to(t));
        }
        return res;
    }
}
