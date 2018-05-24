package com.artwell.sampleordersheet.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.core.entity.BaseIntegerIdEntity;
import com.haulmont.cuba.core.global.DesignSupport;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@DesignSupport("{'imported':true}")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "dID"))
})
@Table(name = "et_style_qty")
@Entity(name = "sampleordersheet$EtStyleQty")
public class EtStyleQty extends BaseIntegerIdEntity {
    private static final long serialVersionUID = 5696630847288673341L;

    @Column(name = "xQty1")
    protected Integer xQty1;

    @Column(name = "xQty2")
    protected Integer xQty2;

    @Column(name = "xQty3")
    protected Integer xQty3;

    @Column(name = "xQty4")
    protected Integer xQty4;

    @Column(name = "xQty5")
    protected Integer xQty5;

    @Column(name = "xQty6")
    protected Integer xQty6;

    @Column(name = "xQty7")
    protected Integer xQty7;

    @Column(name = "xQty8")
    protected Integer xQty8;

    @Column(name = "xQty9")
    protected Integer xQty9;

    @Column(name = "xQty10")
    protected Integer xQty10;

    @Column(name = "xQty11")
    protected Integer xQty11;

    @Column(name = "xQty12")
    protected Integer xQty12;

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

    public void setXQty2(Integer xQty2) {
        this.xQty2 = xQty2;
    }

    public Integer getXQty2() {
        return xQty2;
    }



}