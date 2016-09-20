/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

/**
 *
 * @author PSUANZES
 */
public class FileNameManager {
    private static final String UNDERSCORE = "_";
    private static final String HYPEN = "-";
    private static final String XML_EXT = ".xml";
    private static final String SEPCOL_RECORD_DESC_PATH = 
        "resources/cocam-result_record-desc.xml";
    private static final String TEAM_GROUP = "TeamGroup";
    private static final String RESULT_RECORD = "ResultRecord";
    private static final String SEASON = "Season";
    private static final String TEAM_STAT_GROUP = "TeamStatGroup";
    
    public static String getResultRecordDescFilename() {
        return SEPCOL_RECORD_DESC_PATH;
    }
    
    public static String getTeamGroupFilename() {
        StringBuilder teamGroupFilename = new StringBuilder();
        CocamProps cocamProps = CocamProps.getInstance();
        //path - resources/
        teamGroupFilename.append(cocamProps.getDataFileDir());
        //prefix - Avilesino
        teamGroupFilename.append(cocamProps.getDataFilePrefix());
        // underscore
        teamGroupFilename.append(UNDERSCORE);
        // championship editio id
        teamGroupFilename.append(cocamProps.getChampionshipEditionId());
        // hypen
        teamGroupFilename.append(HYPEN);
        // TeamGroup
        teamGroupFilename.append(TEAM_GROUP);
        // xml ext
        teamGroupFilename.append(XML_EXT);
        return teamGroupFilename.toString();
    }
    
    public static String getResultRecordFilename(final int roundId) {
        StringBuilder seasonFilename = new StringBuilder();
        CocamProps cocamProps = CocamProps.getInstance();
        //path - resources/
        seasonFilename.append(cocamProps.getDataFileDir());
        // Season
        seasonFilename.append(RESULT_RECORD);
        // hypen
        seasonFilename.append(HYPEN);
        // round - jornada - R99
        seasonFilename.append(Round.getRoundIdOutput(roundId));
        // xml ext
        seasonFilename.append(XML_EXT);
        return seasonFilename.toString();
    }
    
    public static String getSeasonFilename(final int roundId) {
        StringBuilder seasonFilename = new StringBuilder();
        CocamProps cocamProps = CocamProps.getInstance();
        //path - resources/
        seasonFilename.append(cocamProps.getDataFileDir());
        // Season
        seasonFilename.append(SEASON);
        // hypen
        seasonFilename.append(HYPEN);
        // round - jornada - R99
        seasonFilename.append(Round.getRoundIdOutput(roundId));
        // xml ext
        seasonFilename.append(XML_EXT);
        return seasonFilename.toString();
    }
    
    public static String getTeamStatGroupFilename(final int roundId) {
        StringBuilder teamStatGroupFilename = new StringBuilder();
        CocamProps cocamProps = CocamProps.getInstance();
        //path - resources/
        teamStatGroupFilename.append(cocamProps.getDataFileDir());
        // TeamStatGroup
        teamStatGroupFilename.append(TEAM_STAT_GROUP);
        // hypen
        teamStatGroupFilename.append(HYPEN);
        // round - jornada - R99
        teamStatGroupFilename.append(Round.getRoundIdOutput(roundId));
        // xml ext
        teamStatGroupFilename.append(XML_EXT);
        return teamStatGroupFilename.toString();
    }
}
