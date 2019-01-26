package com.lukechi.android.opendata.api;

import com.lukechi.android.opendata.model.GetCMSXml;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GetCMSXmlObserver extends BaseObserver<GetCMSXml> {

    @Inject
    public GetCMSXmlObserver() {
    }

    @Override
    public void onNext(GetCMSXml getCMSXml) {

        System.out.println(getCMSXml.centerName());
    }
}
