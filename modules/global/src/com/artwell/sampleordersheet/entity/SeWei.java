package com.artwell.sampleordersheet.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

@MetaClass(name = "sampleordersheet$SeWei")
public class SeWei extends BaseUuidEntity {
    private static final long serialVersionUID = -2518468733340424529L;

    @MetaProperty
    protected Integer mID;

    @MetaProperty
    protected String xCBit;

    @MetaProperty
    protected String xPart;

    @MetaProperty
    protected MtYarn yarn;

    public void setYarn(MtYarn yarn) {
        this.yarn = yarn;
    }

    public MtYarn getYarn() {
        return yarn;
    }


    public void setMID(Integer mID) {
        this.mID = mID;
    }

    public Integer getMID() {
        return mID;
    }

    public void setXCBit(String xCBit) {
        this.xCBit = xCBit;
    }

    public String getXCBit() {
        return xCBit;
    }

    public void setXPart(String xPart) {
        this.xPart = xPart;
    }

    public String getXPart() {
        return xPart;
    }


}