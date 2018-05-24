package com.artwell.sampleordersheet.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s %s %s %s %s %s %s %s %s %s %s %s %s|seZuName,xQty1,xQty2,xQty3,xQty4,xQty5,xQty6,xQty7,xQty8,xQty9,xQty10,xQty11,xQty12")
@MetaClass(name = "sampleordersheet$SeZu")
public class SeZu extends BaseUuidEntity {
    private static final long serialVersionUID = 4320083604486166856L;

    @MetaProperty
    protected String seZuName;

    @MetaProperty
    protected Integer styleID;

    @MetaProperty
    protected String xMColor;

    @MetaProperty
    protected String xEColor;

    @MetaProperty
    protected String xMColorNo;

    @MetaProperty
    protected Integer xQty1;

    @MetaProperty
    protected Integer xQty2;

    @MetaProperty
    protected Integer xQty3;

    @MetaProperty
    protected Integer xQty4;

    @MetaProperty
    protected Integer xQty5;

    @MetaProperty
    protected Integer xQty6;

    @MetaProperty
    protected Integer xQty7;

    @MetaProperty
    protected Integer xQty8;

    @MetaProperty
    protected Integer xQty9;

    @MetaProperty
    protected Integer xQty10;

    @MetaProperty
    protected Integer xQty11;

    @MetaProperty
    protected Integer xQty12;

    @MetaProperty
    protected Integer sumQty;

    public void setStyleID(Integer styleID) {
        this.styleID = styleID;
    }

    public Integer getStyleID() {
        return styleID;
    }


    public void setXEColor(String xEColor) {
        this.xEColor = xEColor;
    }

    public String getXEColor() {
        return xEColor;
    }

    public void setXMColorNo(String xMColorNo) {
        this.xMColorNo = xMColorNo;
    }

    public String getXMColorNo() {
        return xMColorNo;
    }


    public void setXMColor(String xMColor) {
        this.xMColor = xMColor;
    }

    public String getXMColor() {
        return xMColor;
    }


    public void setSumQty(Integer sumQty) {
        this.sumQty = sumQty;
    }

    public Integer getSumQty() {
        return sumQty;
    }


    public void setXQty2(Integer xQty2) {
        this.xQty2 = xQty2;
    }

    public Integer getXQty2() {
        return xQty2;
    }

    public void setXQty3(Integer xQty3) {
        this.xQty3 = xQty3;
    }

    public Integer getXQty3() {
        return xQty3;
    }

    public void setXQty4(Integer xQty4) {
        this.xQty4 = xQty4;
    }

    public Integer getXQty4() {
        return xQty4;
    }

    public void setXQty5(Integer xQty5) {
        this.xQty5 = xQty5;
    }

    public Integer getXQty5() {
        return xQty5;
    }

    public void setXQty6(Integer xQty6) {
        this.xQty6 = xQty6;
    }

    public Integer getXQty6() {
        return xQty6;
    }

    public void setXQty7(Integer xQty7) {
        this.xQty7 = xQty7;
    }

    public Integer getXQty7() {
        return xQty7;
    }

    public void setXQty8(Integer xQty8) {
        this.xQty8 = xQty8;
    }

    public Integer getXQty8() {
        return xQty8;
    }

    public void setXQty9(Integer xQty9) {
        this.xQty9 = xQty9;
    }

    public Integer getXQty9() {
        return xQty9;
    }

    public void setXQty10(Integer xQty10) {
        this.xQty10 = xQty10;
    }

    public Integer getXQty10() {
        return xQty10;
    }

    public void setXQty11(Integer xQty11) {
        this.xQty11 = xQty11;
    }

    public Integer getXQty11() {
        return xQty11;
    }

    public void setXQty12(Integer xQty12) {
        this.xQty12 = xQty12;
    }

    public Integer getXQty12() {
        return xQty12;
    }


    public void setXQty1(Integer xQty1) {
        this.xQty1 = xQty1;
    }

    public Integer getXQty1() {
        return xQty1;
    }


    public void setSeZuName(String seZuName) {
        this.seZuName = seZuName;
    }

    public String getSeZuName() {
        return seZuName;
    }
    
    public SeZu() { }
	public SeZu(String seZuName,Integer xQty1,Integer xQty2,Integer xQty3,Integer xQty4,Integer xQty5,Integer xQty6,Integer xQty7,Integer xQty8,Integer xQty9,Integer xQty10,Integer xQty11,Integer xQty12) {
		this.seZuName=seZuName;
		this.xQty1=xQty1;this.xQty2=xQty2;this.xQty3=xQty3;this.xQty4=xQty4;this.xQty5=xQty5;this.xQty6=xQty6;
		this.xQty7=xQty7;this.xQty8=xQty8;this.xQty9=xQty9;this.xQty10=xQty10;this.xQty11=xQty11;this.xQty12=xQty12;
	}

}