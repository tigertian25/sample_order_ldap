package com.artwell.sampleordersheet.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.cuba.core.entity.BaseUuidEntity;
import com.haulmont.chile.core.annotations.MetaProperty;
import java.util.Date;

@MetaClass(name = "sampleordersheet$Style")
public class Style extends BaseUuidEntity {
    private static final long serialVersionUID = 6909109180712278560L;

    @MetaProperty
    protected Integer sID;

    @MetaProperty
    protected String xKindName;

    @MetaProperty
    protected String xNote;

    @MetaProperty
    protected String sNumber;

    @MetaProperty
    protected String xSampleType;

    @MetaProperty
    protected Date xDate1;

    @MetaProperty
    protected String xTeam;

    @MetaProperty
    protected String xStyleNo;

    @MetaProperty
    protected String xCustStyleNo;

    @MetaProperty
    protected String xSeason;

    @MetaProperty
    protected String rCust;

    @MetaProperty
    protected String xGauge;

    @MetaProperty
    protected String xYSpec;

    @MetaProperty
    protected String xStyle;

    @MetaProperty
    protected String xWeaveDesc;

    @MetaProperty
    protected String xWGT;

    @MetaProperty
    protected Date xDate2;

    @MetaProperty
    protected String sOwner;

    @MetaProperty
    protected String xPic2;

    @MetaProperty
    protected String xPic;

    @MetaProperty
    protected String xUnit;

    @MetaProperty
    protected String xSize1;

    @MetaProperty
    protected String xSize2;

    @MetaProperty
    protected String xSize3;

    @MetaProperty
    protected String xSize4;

    @MetaProperty
    protected String xSize5;

    @MetaProperty
    protected String xSize6;

    @MetaProperty
    protected String xSize7;

    @MetaProperty
    protected String xSize8;

    @MetaProperty
    protected String xSize9;

    @MetaProperty
    protected String xSize10;

    @MetaProperty
    protected String xSize11;

    @MetaProperty
    protected String xSize12;

    @MetaProperty
    protected Date sLastUpdate;

    @MetaProperty
    protected String status;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }


    public void setSLastUpdate(Date sLastUpdate) {
        this.sLastUpdate = sLastUpdate;
    }

    public Date getSLastUpdate() {
        return sLastUpdate;
    }


    public void setXKindName(String xKindName) {
        this.xKindName = xKindName;
    }

    public String getXKindName() {
        return xKindName;
    }

    public void setXNote(String xNote) {
        this.xNote = xNote;
    }

    public String getXNote() {
        return xNote;
    }


    public Date getXDate1() {
        return xDate1;
    }

    public void setXDate1(Date xDate1) {
        this.xDate1 = xDate1;
    }


    public Date getXDate2() {
        return xDate2;
    }

    public void setXDate2(Date xDate2) {
        this.xDate2 = xDate2;
    }


    public void setRCust(String rCust) {
        this.rCust = rCust;
    }

    public String getRCust() {
        return rCust;
    }


    public void setXUnit(String xUnit) {
        this.xUnit = xUnit;
    }

    public String getXUnit() {
        return xUnit;
    }

    public void setXSize1(String xSize1) {
        this.xSize1 = xSize1;
    }

    public String getXSize1() {
        return xSize1;
    }

    public void setXSize2(String xSize2) {
        this.xSize2 = xSize2;
    }

    public String getXSize2() {
        return xSize2;
    }

    public void setXSize3(String xSize3) {
        this.xSize3 = xSize3;
    }

    public String getXSize3() {
        return xSize3;
    }

    public void setXSize4(String xSize4) {
        this.xSize4 = xSize4;
    }

    public String getXSize4() {
        return xSize4;
    }

    public void setXSize5(String xSize5) {
        this.xSize5 = xSize5;
    }

    public String getXSize5() {
        return xSize5;
    }

    public void setXSize6(String xSize6) {
        this.xSize6 = xSize6;
    }

    public String getXSize6() {
        return xSize6;
    }

    public void setXSize7(String xSize7) {
        this.xSize7 = xSize7;
    }

    public String getXSize7() {
        return xSize7;
    }

    public void setXSize8(String xSize8) {
        this.xSize8 = xSize8;
    }

    public String getXSize8() {
        return xSize8;
    }

    public void setXSize9(String xSize9) {
        this.xSize9 = xSize9;
    }

    public String getXSize9() {
        return xSize9;
    }

    public void setXSize10(String xSize10) {
        this.xSize10 = xSize10;
    }

    public String getXSize10() {
        return xSize10;
    }

    public void setXSize11(String xSize11) {
        this.xSize11 = xSize11;
    }

    public String getXSize11() {
        return xSize11;
    }

    public void setXSize12(String xSize12) {
        this.xSize12 = xSize12;
    }

    public String getXSize12() {
        return xSize12;
    }


    public void setSNumber(String sNumber) {
        this.sNumber = sNumber;
    }

    public String getSNumber() {
        return sNumber;
    }

    public void setXSampleType(String xSampleType) {
        this.xSampleType = xSampleType;
    }

    public String getXSampleType() {
        return xSampleType;
    }

    public void setXTeam(String xTeam) {
        this.xTeam = xTeam;
    }

    public String getXTeam() {
        return xTeam;
    }

    public void setXStyleNo(String xStyleNo) {
        this.xStyleNo = xStyleNo;
    }

    public String getXStyleNo() {
        return xStyleNo;
    }

    public void setXCustStyleNo(String xCustStyleNo) {
        this.xCustStyleNo = xCustStyleNo;
    }

    public String getXCustStyleNo() {
        return xCustStyleNo;
    }

    public void setXSeason(String xSeason) {
        this.xSeason = xSeason;
    }

    public String getXSeason() {
        return xSeason;
    }

    public void setXGauge(String xGauge) {
        this.xGauge = xGauge;
    }

    public String getXGauge() {
        return xGauge;
    }

    public void setXYSpec(String xYSpec) {
        this.xYSpec = xYSpec;
    }

    public String getXYSpec() {
        return xYSpec;
    }

    public void setXStyle(String xStyle) {
        this.xStyle = xStyle;
    }

    public String getXStyle() {
        return xStyle;
    }

    public void setXWeaveDesc(String xWeaveDesc) {
        this.xWeaveDesc = xWeaveDesc;
    }

    public String getXWeaveDesc() {
        return xWeaveDesc;
    }

    public void setXWGT(String xWGT) {
        this.xWGT = xWGT;
    }

    public String getXWGT() {
        return xWGT;
    }

    public void setSOwner(String sOwner) {
        this.sOwner = sOwner;
    }

    public String getSOwner() {
        return sOwner;
    }

    public void setXPic2(String xPic2) {
        this.xPic2 = xPic2;
    }

    public String getXPic2() {
        return xPic2;
    }

    public void setXPic(String xPic) {
        this.xPic = xPic;
    }

    public String getXPic() {
        return xPic;
    }


    public void setSID(Integer sID) {
        this.sID = sID;
    }

    public Integer getSID() {
        return sID;
    }


}