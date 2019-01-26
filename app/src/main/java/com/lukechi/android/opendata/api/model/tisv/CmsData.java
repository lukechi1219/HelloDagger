package com.lukechi.android.opendata.api.model.tisv;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;
import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

@Xml(name = "cms:CmsData")
@AutoValue
public abstract class CmsData implements Parcelable {

    @PropertyElement(name = "cms:ID")
    public abstract String id();

    @PropertyElement(name = "cms:Location")
    public abstract String location();

    @PropertyElement(name = "cms:Content")
    public abstract String content();
}
