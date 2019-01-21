package com.lukechi.android.hellodagger.core.impl;

import javax.inject.Inject;

public class FakeBazService extends BazService {

    @Inject
    public FakeBazService() {
        super(null);
    }

    @Override
    public String work() {
        String result = "work data: Fake";
        System.out.println(result);
        return result;
    }
}
