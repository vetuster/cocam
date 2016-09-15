/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import java.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author PSUANZES
 */
public class CocamAdapter extends XmlAdapter<String, LocalDate> {

    @Override
    public LocalDate unmarshal(String value) {
        return (CocamDatatypeConverter.parseLocalDate(value));
    }

    @Override
    public String marshal(LocalDate value) {
        if (value == null) {
            return null;
        }
        return (CocamDatatypeConverter.printLocalDate(value));
    }

}
