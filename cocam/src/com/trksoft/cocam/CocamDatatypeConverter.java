/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author jasuarez
 */
public final class CocamDatatypeConverter {
    public static final String COCAM_DATE_PATTERN_PARSE = "dd/MM/yyyy";
    public static final String COCAM_DATE_PATTERN_PRINT = "yyyy-MM-dd";
    
    public static final String COCAM_HOUR_PATTERN = "HH:mm:ss";
    
    // marshall - LocalDate vs String
    public static LocalDate parseLocalDate(String dateString) {
        DateTimeFormatter formatter = 
            DateTimeFormatter.ofPattern(COCAM_DATE_PATTERN_PARSE);
        return LocalDate.parse(dateString, formatter);
    }
    
    //unmarshall - LocalDate vs String
    public static String printLocalDate(LocalDate localDate) {
        DateTimeFormatter formatter = 
            DateTimeFormatter.ofPattern(COCAM_DATE_PATTERN_PRINT);
        return formatter.format(localDate);
    }
    
    // marshall - LocalDate vs XMLGregorianCalendar
    public static LocalDate parseLocalDate(XMLGregorianCalendar xmlGC) {
        return xmlGC.toGregorianCalendar().toZonedDateTime().toLocalDate();
    }
    
    //unmarshall - LocalDate vs XMLGregorianCalendar
    public static XMLGregorianCalendar toXMLGC(LocalDate localDate)
        throws DatatypeConfigurationException {
        String lexicalDateRepresentation = printLocalDate(localDate);
        return DatatypeFactory.newInstance().
            newXMLGregorianCalendar(lexicalDateRepresentation);
    }
    
            
    public static LocalTime parseLocalTime(String timeString) {
        DateTimeFormatter formatter = 
            DateTimeFormatter.ofPattern(COCAM_HOUR_PATTERN);
        return LocalTime.parse(timeString, formatter);
    }
    
    public static String printLocalTime(LocalTime localTime) {
        DateTimeFormatter formatter = 
            DateTimeFormatter.ofPattern(COCAM_HOUR_PATTERN);
        return formatter.format(localTime);
    }
    
}
