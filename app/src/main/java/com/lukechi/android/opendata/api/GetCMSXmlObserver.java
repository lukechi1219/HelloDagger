package com.lukechi.android.opendata.api;

import com.lukechi.android.opendata.model.CmsData;
import com.lukechi.android.opendata.model.GetCMSXml;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class GetCMSXmlObserver extends BaseObserver<GetCMSXml> {

    @Inject
    public GetCMSXmlObserver() {
    }

    @Override
    public void onNext(GetCMSXml getCMSXml) {

        System.out.println(getCMSXml.centerName());

        List<CmsData> list = getCMSXml.cmsDataList();

        System.out.println(list.get(0).id());
        System.out.println(list.get(0).location());
        System.out.println(list.get(0).content());
    }
}
