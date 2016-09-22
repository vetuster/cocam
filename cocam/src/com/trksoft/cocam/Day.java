/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import java.text.DecimalFormat;


/**
 *
 * @author jasuarez
 */
public class Day {
    private static final String DAY_OUTPUT_PREFIX = "D";
    private static final String DAY_OUTPUT_PATTERN = "00";
    
    public static String getDayIdOutput(final int dayId) {
        StringBuilder roundIdOutput = new StringBuilder(DAY_OUTPUT_PREFIX);
        DecimalFormat df = new DecimalFormat(DAY_OUTPUT_PATTERN);
        roundIdOutput.append(df.format(dayId));
        return roundIdOutput.toString();
    }
}