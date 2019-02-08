package com.lukechi.android.hellodagger.thirdparty;

import android.content.Context;

/**
 * 3rd Party Lib class
 */
public class ThirdPartyClass {

    // 無法加上 @Inject
    private final Context context;

    private final String info = "real ThirdPartyClass";

    // 無法加上 @Inject
    public ThirdPartyClass(Context context) {
        this.context = context;
    }

    public String getInfo() {
        System.out.println(info);
        System.out.println("ThirdPartyClass: " + context);
        return info;
    }
}
