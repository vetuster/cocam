/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.util;

/**
 *
 * @author triki
 */
public class StringUtil {
    public static final String NULL_LIT = "NULL";
    public static final String ENCLOSE_LEFT_DELIM = "(";
    public static final String ENCLOSE_RIGHT_DELIM = ")";
    public static final String XML_OPEN_TAG_LEFT_DELIM = "<";
    public static final String XML_OPEN_TAG_RIGHT_DELIM = ">";
    public static final String XML_CLOSE_TAG_LEFT_DELIM = "</";
    public static final String XML_CLOSE_TAG_RIGHT_DELIM = ">";
    
    
    public static boolean isNullOrEmpty(final String literal) {
        return literal == null || literal.trim().length() == 0;
    }
    
    
    public static String enclose(final String value) {
        StringBuilder sb = new StringBuilder(ENCLOSE_LEFT_DELIM);
        if (value == null) {
            sb.append(NULL_LIT);
        } else {
            sb.append(value);
        }
        sb.append(ENCLOSE_RIGHT_DELIM);
        return sb.toString();
    }
    
    public static String enclose(final Integer value) {
        if (value == null) {
            return enclose(NULL_LIT);
        }
        return enclose(value.toString());
    }
    
    public static String enclose(final Boolean value) {
        if (value == null) {
            return enclose(NULL_LIT);
        }
        return enclose(value.toString());
    }
}