/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.flatfile;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author PSUANZES
 */
public class MyTest {

    public static void main(String[] args) throws Exception {

        XMLGregorianCalendar xmlgc =
            DatatypeFactory.newInstance().newXMLGregorianCalendar("2016-09-29");
        
        System.out.println("xmlgc(" + xmlgc.toString() + ")");
        System.out.println("xmlgc(" + xmlgc.toXMLFormat() + ")");
    }
}
