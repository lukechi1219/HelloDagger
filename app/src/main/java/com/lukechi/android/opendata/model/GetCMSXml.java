package com.lukechi.android.opendata.model;

import com.google.auto.value.AutoValue;
import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

/**
 * implements Parcelable ?
 */
@Xml(name = "cms:ExchangeData")
@AutoValue
public abstract class GetCMSXml {

    @PropertyElement(name = "cms:CenterName")
    public abstract String centerName();

    @PropertyElement(name = "cms:ExchangeTime")
    abstract String exchangeTime();
}
