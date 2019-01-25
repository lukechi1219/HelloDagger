package com.lukechi.android.opendata.api;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {
        System.out.println("onSubscribe");
        System.out.println(d);
    }

    @Override
    public void onError(Throwable t) {
//                        System.out.println(response.errorBody());
//                        System.out.println(e.getMessage());
        t.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("onComplete");
    }
}
