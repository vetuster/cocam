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
    private static final String PLAYER_ENTITY = "PlayerEntity";
    private static final String RESULT_ENTITY = "ResultEntity";
    private static final String SEASON = "Season";
    private static final String TEAM_RANKING = "TeamRanking";
    private static final String PLAYER_RANKING = "PlayerRanking";
    
    protected static String getResultRecordDescFilename() {
        return SEPCOL_RECORD_DESC_PATH;
    }
    
    protected static String getResultEntitySchemaFilename() {
        return RESULT_ENTITY_SCHEMA_PATH;
    }
    
    protected static String getTeamEntityFilename() {
        StringBuilder filename = new StringBuilder();
        CocamProps cocamProps = CocamProps.getInstance();
        //path - resources/
        filename.append(cocamProps.getDataFileDir());
        //prefix - Avilesino
        filename.append(cocamProps.getDataFilePrefix());
        // underscore
        filename.append(UNDERSCORE);
        // championship editio id
        filename.append(cocamProps.getChampionshipEditionId());
        // hypen
        filename.append(HYPEN);
        // Team Entity
        filename.append(TEAM_ENTITY);
        // xml ext
        filename.append(XML_EXT);
        return filename.toString();
    }
    
    protected static String getPlayerEntityFilename() {
        StringBuilder filename = new StringBuilder();
        CocamProps cocamProps = CocamProps.getInstance();
        //path - resources/
        filename.append(cocamProps.getDataFileDir());
        //prefix - Avilesino
        filename.append(cocamProps.getDataFilePrefix());
        // underscore
        filename.append(UNDERSCORE);
        // championship editio id
        filename.append(cocamProps.getChampionshipEditionId());
        // hypen
        filename.append(HYPEN);
        // Player Entity
        filename.append(PLAYER_ENTITY);
        // xml ext
        filename.append(XML_EXT);
        return filename.toString();
    }
    
    protected static String getResultEntityFilename() {
        StringBuilder filename = new StringBuilder();
        CocamProps cocamProps = CocamProps.getInstance();
        //path - resources/
        filename.append(cocamProps.getDataFileDir());
        // Season
        filename.append(RESULT_ENTITY);
        // xml ext
        filename.append(XML_EXT);
        return filename.toString();
    }
    
    protected static String getSeasonFilename(final int dayId) {
        StringBuilder filename = new StringBuilder();
        CocamProps cocamProps = CocamProps.getInstance();
        //path - resources/
        filename.append(cocamProps.getDataFileDir());
        // Season
        filename.append(SEASON);
        // hypen
        filename.append(HYPEN);
        // day - jornada - R99
        filename.append(SeasonDay.getDayIdOutput(dayId));
        // xml ext
        filename.append(XML_EXT);
        return filename.toString();
    }
    
    protected static String getTeamRankingFilename(final int dayId,
        LeagueType leagueType) {
        StringBuilder filename = new StringBuilder();
        CocamProps cocamProps = CocamProps.getInstance();
        //path - resources/
        filename.append(cocamProps.getDataFileDir());
        // LeagueType
        filename.append(leagueType.toString());
        // hypen
        filename.append(HYPEN);
        // Team Ranking
        filename.append(TEAM_RANKING);
        // hypen
        filename.append(HYPEN);
        // day - jornada - R99
        filename.append(SeasonDay.getDayIdOutput(dayId));
        // xml ext
        filename.append(CSV_EXT);
        return filename.toString();
    }
    
    protected static String getPlayerRankingFilename(final int dayId,
        LeagueType leagueType) {
        StringBuilder filename = new StringBuilder();
        CocamProps cocamProps = CocamProps.getInstance();
        //path - resources/
        filename.append(cocamProps.getDataFileDir());
        // LeagueType
        filename.append(leagueType.toString());
        // hypen
        filename.append(HYPEN);
        // Team Ranking
        filename.append(PLAYER_RANKING);
        // hypen
        filename.append(HYPEN);
        // day - jornada - R99
        filename.append(SeasonDay.getDayIdOutput(dayId));
        // xml ext
        filename.append(CSV_EXT);
        return filename.toString();
    }
}
