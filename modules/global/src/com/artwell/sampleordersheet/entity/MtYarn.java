package com.artwell.sampleordersheet.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.BaseIntegerIdEntity;
import com.haulmont.cuba.core.global.DesignSupport;
import com.haulmont.chile.core.annotations.NamePattern;

@DesignSupport("{'imported':true}")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "sID"))
})
@NamePattern("%s|xName")
@Table(name = "mt_yarn")
@Entity(name = "sampleordersheet$MtYarn")
public class MtYarn extends BaseIntegerIdEntity {
    private static final long serialVersionUID = 7542823951570833020L;

    @Column(name = "xName")
    protected String xName;

    @Column(name = "xCode")
    protected String xCode;

    public void setXCode(String xCode) {
        this.xCode = xCode;
    }

    public String getXCode() {
        return xCode;
    }


    public void setXName(String xName) {
        this.xName = xName;
    }

    public String getXName() {
        return xName;
    }


}