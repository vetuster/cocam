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
public class Round {
    private static final String ROUND_OUTPUT_PREFIX = "R";
    private static final String ROUND_OUTPUT_PATTERN = "00";
    
    public static String getRoundIdOutput(final int roundId) {
        StringBuilder roundIdOutput = new StringBuilder(ROUND_OUTPUT_PREFIX);
        DecimalFormat df = new DecimalFormat(ROUND_OUTPUT_PATTERN);
        roundIdOutput.append(df.format(roundId));
        return roundIdOutput.toString();
    }
}