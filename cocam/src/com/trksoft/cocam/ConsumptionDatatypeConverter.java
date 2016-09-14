/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author jasuarez
 */
public final class ConsumptionDatatypeConverter {
    public static final String TC_CONSUMPTION_DATE_PATTERN = "dd/MM/yyyy";
    
    public static final String TC_CONSUMPTION_HOUR_PATTERN = "HH:mm:ss";
    

    public static LocalDate parseLocalDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            TC_CONSUMPTION_DATE_PATTERN);
        return LocalDate.parse(dateString, formatter);
    }
    
    public static String printLocalDate(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            TC_CONSUMPTION_DATE_PATTERN);
        return formatter.format(localDate);
    }
    
            
    public static LocalTime parseLocalTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            TC_CONSUMPTION_HOUR_PATTERN);
        return LocalTime.parse(timeString, formatter);
    }
    
    public static String printLocalTime(LocalTime localTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            TC_CONSUMPTION_HOUR_PATTERN);
        return formatter.format(localTime);
    }
    
}
