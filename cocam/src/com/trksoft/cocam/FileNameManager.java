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
    private static final String RESULT_ENTITY_SCHEMA_PATH = 
        "resources/cocam-ResultEntity.xsd";
    
    private static final String TEAM_ENTITY = "TeamEntity";
    private static final String PLAYER_GROUP = "PlayerGroup";
    private static final String RESULT_ENTITY = "ResultEntity";
    private static final String SEASON = "Season";
    private static final String TEAM_STAT_GROUP = "TeamStatGroup";
    private static final String TEAM_RANKING = "TeamRanking";
    private static final String PLAYER_STAT_GROUP = "PlayerStatGroup";
    private static final String PLAYER_RANKING = "PlayerRanking";
    
    protected static String getResultRecordDescFilename() {
        return SEPCOL_RECORD_DESC_PATH;
    }
    
    protected static String getResultEntitySchemaFilename() {
        return RESULT_ENTITY_SCHEMA_PATH;
    }
    
    protected static String getTeamEntityFilename() {
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
        teamGroupFilename.append(TEAM_ENTITY);
        // xml ext
        teamGroupFilename.append(XML_EXT);
        return teamGroupFilename.toString();
    }
    
    protected static String getPlayerGroupFilename(final int dayId) {
        StringBuilder playerGroupFilename = new StringBuilder();
        CocamProps cocamProps = CocamProps.getInstance();
        //path - resources/
        playerGroupFilename.append(cocamProps.getDataFileDir());
        // TeamStatGroup
        playerGroupFilename.append(PLAYER_GROUP);
        // hypen
        playerGroupFilename.append(HYPEN);
        // day - jornada - R99
        playerGroupFilename.append(Day.getDayIdOutput(dayId));
        // xml ext
        playerGroupFilename.append(XML_EXT);
        return playerGroupFilename.toString();
    }
    
    protected static String getResultEntityFilename() {
        StringBuilder seasonFilename = new StringBuilder();
        CocamProps cocamProps = CocamProps.getInstance();
        //path - resources/
        seasonFilename.append(cocamProps.getDataFileDir());
        // Season
        seasonFilename.append(RESULT_ENTITY);
        // xml ext
        seasonFilename.append(XML_EXT);
        return seasonFilename.toString();
    }
    
    protected static String getSeasonFilename(final int dayId) {
        StringBuilder seasonFilename = new StringBuilder();
        CocamProps cocamProps = CocamProps.getInstance();
        //path - resources/
        seasonFilename.append(cocamProps.getDataFileDir());
        // Season
        seasonFilename.append(SEASON);
        // hypen
        seasonFilename.append(HYPEN);
        // day - jornada - R99
        seasonFilename.append(Day.getDayIdOutput(dayId));
        // xml ext
        seasonFilename.append(XML_EXT);
        return seasonFilename.toString();
    }
    
    protected static String getTeamStatGroupFilename(final int dayId) {
        StringBuilder teamStatGroupFilename = new StringBuilder();
        CocamProps cocamProps = CocamProps.getInstance();
        //path - resources/
        teamStatGroupFilename.append(cocamProps.getDataFileDir());
        // TeamStatGroup
        teamStatGroupFilename.append(TEAM_STAT_GROUP);
        // hypen
        teamStatGroupFilename.append(HYPEN);
        // day - jornada - R99
        teamStatGroupFilename.append(Day.getDayIdOutput(dayId));
        // xml ext
        teamStatGroupFilename.append(XML_EXT);
        return teamStatGroupFilename.toString();
    }
    
    
    protected static String getTeamRankingFilename(final int dayId) {
        StringBuilder rankingTeamFilename = new StringBuilder();
        CocamProps cocamProps = CocamProps.getInstance();
        //path - resources/
        rankingTeamFilename.append(cocamProps.getDataFileDir());
        // TeamStatGroup
        rankingTeamFilename.append(TEAM_RANKING);
        // hypen
        rankingTeamFilename.append(HYPEN);
        // day - jornada - R99
        rankingTeamFilename.append(Day.getDayIdOutput(dayId));
        // xml ext
        rankingTeamFilename.append(CSV_EXT);
        return rankingTeamFilename.toString();
    }
    
    protected static String getPlayerStatGroupFilename(final int dayId) {
        StringBuilder playerStatGroupFilename = new StringBuilder();
        CocamProps cocamProps = CocamProps.getInstance();
        //path - resources/
        playerStatGroupFilename.append(cocamProps.getDataFileDir());
        // TeamStatGroup
        playerStatGroupFilename.append(PLAYER_STAT_GROUP);
        // hypen
        playerStatGroupFilename.append(HYPEN);
        // day - jornada - R99
        playerStatGroupFilename.append(Day.getDayIdOutput(dayId));
        // xml ext
        playerStatGroupFilename.append(XML_EXT);
        return playerStatGroupFilename.toString();
    }
    
    protected static String getPlayerRankingFilename(final int dayId) {
        StringBuilder playerRankingFilename = new StringBuilder();
        CocamProps cocamProps = CocamProps.getInstance();
        //path - resources/
        playerRankingFilename.append(cocamProps.getDataFileDir());
        // TeamStatGroup
        playerRankingFilename.append(PLAYER_RANKING);
        // hypen
        playerRankingFilename.append(HYPEN);
        // day - jornada - R99
        playerRankingFilename.append(Day.getDayIdOutput(dayId));
        // xml ext
        playerRankingFilename.append(CSV_EXT);
        return playerRankingFilename.toString();
    }
}
