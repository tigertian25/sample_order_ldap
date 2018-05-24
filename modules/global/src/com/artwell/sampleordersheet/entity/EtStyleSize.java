package com.artwell.sampleordersheet.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

@MetaClass(name = "sampleordersheet$EtStyleSize")
public class EtStyleSize extends BaseUuidEntity {
    private static final long serialVersionUID = -477808471690924663L;

    @MetaProperty
    protected Integer sIndex;

    @MetaProperty
    protected String xPart;

    @MetaProperty
    protected String xRule;

    @MetaProperty
    protected Double xLen1;

    @MetaProperty
    protected Double xLen2;

    @MetaProperty
    protected Double xLen3;

    @MetaProperty
    protected Double xLen4;

    @MetaProperty
    protected Double xLen5;

    @MetaProperty
    protected Double xLen6;

    @MetaProperty
    protected Double xLen7;

    @MetaProperty
    protected Double xLen8;

    @MetaProperty
    protected Double xLen9;

    @MetaProperty
    protected Double xLen10;

    @MetaProperty
    protected Double xLen11;

    @MetaProperty
    protected Double xLen12;

    @MetaProperty
    protected Double xLoss;

    @MetaProperty
    protected Double xLoss2;

    public void setSIndex(Integer sIndex) {
        this.sIndex = sIndex;
    }

    public Integer getSIndex() {
        return sIndex;
    }

    public void setXPart(String xPart) {
        this.xPart = xPart;
    }

    public String getXPart() {
        return xPart;
    }

    public void setXRule(String xRule) {
        this.xRule = xRule;
    }

    public String getXRule() {
        return xRule;
    }

    public void setXLen1(Double xLen1) {
        this.xLen1 = xLen1;
    }

    public Double getXLen1() {
        return xLen1;
    }

    public void setXLen2(Double xLen2) {
        this.xLen2 = xLen2;
    }

    public Double getXLen2() {
        return xLen2;
    }

    public void setXLen3(Double xLen3) {
        this.xLen3 = xLen3;
    }

    public Double getXLen3() {
        return xLen3;
    }

    public void setXLen4(Double xLen4) {
        this.xLen4 = xLen4;
    }

    public Double getXLen4() {
        return xLen4;
    }

    public void setXLen5(Double xLen5) {
        this.xLen5 = xLen5;
    }

    public Double getXLen5() {
        return xLen5;
    }

    public void setXLen6(Double xLen6) {
        this.xLen6 = xLen6;
    }

    public Double getXLen6() {
        return xLen6;
    }

    public void setXLen7(Double xLen7) {
        this.xLen7 = xLen7;
    }

    public Double getXLen7() {
        return xLen7;
    }

    public void setXLen8(Double xLen8) {
        this.xLen8 = xLen8;
    }

    public Double getXLen8() {
        return xLen8;
    }

    public void setXLen9(Double xLen9) {
        this.xLen9 = xLen9;
    }

    public Double getXLen9() {
        return xLen9;
    }

    public void setXLen10(Double xLen10) {
        this.xLen10 = xLen10;
    }

    public Double getXLen10() {
        return xLen10;
    }

    public void setXLen11(Double xLen11) {
        this.xLen11 = xLen11;
    }

    public Double getXLen11() {
        return xLen11;
    }

    public void setXLen12(Double xLen12) {
        this.xLen12 = xLen12;
    }

    public Double getXLen12() {
        return xLen12;
    }

    public void setXLoss(Double xLoss) {
        this.xLoss = xLoss;
    }

    public Double getXLoss() {
        return xLoss;
    }

    public void setXLoss2(Double xLoss2) {
        this.xLoss2 = xLoss2;
    }

    public Double getXLoss2() {
        return xLoss2;
    }


}