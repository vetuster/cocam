/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.flatfile;

import com.trksoft.util.StringUtil;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author jasuarez
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "fieldNo",
    "fieldName",
    "fieldLength"
})
@XmlRootElement
public class FieldDesc implements Comparable<FieldDesc>{
    
    @XmlAttribute(required = true)    
    private String fieldName;
    @XmlAttribute(required = true)
    private int fieldNo;
    @XmlAttribute(required = false)
    private Integer fieldLength;


    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String FieldName) {
        this.fieldName = FieldName;
    }
    
    public int getFieldNo() {
        return fieldNo;
    }

    public void setFieldNo(int FieldNo) {
        this.fieldNo = FieldNo;
    }

    public Integer getFieldLength() {
        return fieldLength;
    }

    public void setFieldLength(Integer fieldLength) {
        this.fieldLength = fieldLength;
    }

    @Override
    public int compareTo(FieldDesc fieldDesc) {
        return Integer.compare(fieldNo, fieldDesc.getFieldNo());
    }

    @Override
    public String toString() {
        return "fieldNo(" + fieldNo
            + "),fieldName("
            + (fieldName != null? fieldName : StringUtil.NULL_LIT)
            + "),fieldLength(" + fieldLength + ')';
    }
}
