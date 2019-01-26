package com.lukechi.android.opendata.api.model.tisv;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;
import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.Path;
import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

import java.util.List;

/**
 * implements Parcelable ?
 */
@Xml(name = "cms:ExchangeData")
@AutoValue
public abstract class GetCMSXml implements Parcelable {

    @PropertyElement(name = "cms:CenterName")
    public abstract String centerName();

    @PropertyElement(name = "cms:ExchangeTime")
    public abstract String exchangeTime();

    /**
     * TODO: TikXml didn't support TypeAdapterFactory? for inner class
     */
    @Path("cms:CmsDataSet")
    @Element(name = "cms:CmsData")
    public abstract List<CmsData> cmsDataList();
}
