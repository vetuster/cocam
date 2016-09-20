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
    private static final String CSV_EXT = ".csv";
    
    private static final String SEPCOL_RECORD_DESC_PATH = 
        "resources/cocam-result_record-desc.xml";
    private static final String RESULT_RECORD_GROUP_SCHEMA_PATH = 
        "resources/cocam-ResultRecordGroup.xsd";
    
    private static final String TEAM_GROUP = "TeamGroup";
    private static final String RESULT_RECORD_GROUP = "ResultRecordGroup";
    private static final String SEASON = "Season";
    private static final String TEAM_STAT_GROUP = "TeamStatGroup";
    private static final String RANKING_TEAM = "RankingTeam";
    
    protected static String getResultRecordDescFilename() {
        return SEPCOL_RECORD_DESC_PATH;
    }
    
    protected static String getResultRecordGroupSchemaFilename() {
        return RESULT_RECORD_GROUP_SCHEMA_PATH;
    }
    
    protected static String getTeamGroupFilename() {
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
    
    protected static String getResultRecordGroupFilename(final int roundId) {
        StringBuilder seasonFilename = new StringBuilder();
        CocamProps cocamProps = CocamProps.getInstance();
        //path - resources/
        seasonFilename.append(cocamProps.getDataFileDir());
        // Season
        seasonFilename.append(RESULT_RECORD_GROUP);
        // hypen
        seasonFilename.append(HYPEN);
        // round - jornada - R99
        seasonFilename.append(Round.getRoundIdOutput(roundId));
        // xml ext
        seasonFilename.append(XML_EXT);
        return seasonFilename.toString();
    }
    
    protected static String getSeasonFilename(final int roundId) {
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
    
    protected static String getTeamStatGroupFilename(final int roundId) {
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
    
    
    protected static String getRankingFilename(final int roundId) {
        StringBuilder rankingTeamFilename = new StringBuilder();
        CocamProps cocamProps = CocamProps.getInstance();
        //path - resources/
        rankingTeamFilename.append(cocamProps.getDataFileDir());
        // TeamStatGroup
        rankingTeamFilename.append(RANKING_TEAM);
        // hypen
        rankingTeamFilename.append(HYPEN);
        // round - jornada - R99
        rankingTeamFilename.append(Round.getRoundIdOutput(roundId));
        // xml ext
        rankingTeamFilename.append(CSV_EXT);
        return rankingTeamFilename.toString();
    }
}
