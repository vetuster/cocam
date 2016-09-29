/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import java.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author PSUANZES
 */
public class CocamAdapter extends XmlAdapter<XMLGregorianCalendar, LocalDate> {

    @Override
    public LocalDate unmarshal(XMLGregorianCalendar value) {
        return (CocamDatatypeConverter.parseLocalDate(value));
    }

    @Override
    public XMLGregorianCalendar marshal(LocalDate value)
        throws DatatypeConfigurationException {
        if (value == null) {
            return null;
        }
        return (CocamDatatypeConverter.toXMLGC(value));
    }

}
