package com.lukechi.android.opendata.api;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

// TODO: rename to BaseSingleObserver
public abstract class BaseObserver<T> implements SingleObserver<T> {

    @Override
    public void onSubscribe(Disposable d) {
        System.out.println("onSubscribe");
        System.out.println("Disposable " + d);
        System.out.println("----");
    }

    @Override
    public void onError(Throwable t) {
//                        System.out.println(response.errorBody());
//                        System.out.println(e.getMessage());
        t.printStackTrace();
    }

//    @Override
//    public void onComplete() {
//        System.out.println("onComplete");
//        System.out.println("----");
//    }
}
