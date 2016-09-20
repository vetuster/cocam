/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import com.trksoft.util.StringUtil;
import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



/**
 *
 * @author PSUANZES
 */
public class Cocam {
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(Cocam.class);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        logger.debug("Vamos Cocam!");
        
        // carga lista de equipos
        String teamGroupFilename = FileNameManager.getTeamGroupFilename();
        TeamGroup teamGroup = TeamGroup.unmarshall(new File(teamGroupFilename));
        
        ResultFileManager resultFileManager = new ResultFileManager();
        // cargar los registros de los archivos de resultados
        List<ResultRecord> resultRecordList = 
            resultFileManager.getResultRecord();
        
        // carga la temporada con los resultados de las jornadas hasta el 
        // momento (ficheros de resultado disponibles)
        SeasonManager seasonManager = new SeasonManager();
        Season season = seasonManager.build(resultRecordList);
        String seasonFilename = FileNameManager.getSeasonFilename(
            season.getLastRoundId());
        season.marshall(new File(seasonFilename));
        int totalTables = 0;
        totalTables = season.getMatch().stream().map((match) ->
            match.getTable().size()).reduce(totalTables, Integer::sum);
        logger.info("seasonId" + StringUtil.enclose(season.getSeasonId())
            + ",lastRoundId" + StringUtil.enclose(season.getLastRoundId())
            + ",TOTAL matches" + StringUtil.enclose(season.getMatch().size())
            + ",TOTAL tables" + StringUtil.enclose(totalTables)
        );
        
        // cargar las estadisticas de cada equipo
        TeamStatGroup teamStatGroup =  new TeamStatGroup();
        teamStatGroup.load(teamGroup, season);
        String teamStatGroupFilename = FileNameManager.getTeamStatGroupFilename(
            season.getLastRoundId());
        teamStatGroup.marshall(new File(teamStatGroupFilename));
        
        // ordenar las estadisticas para obtener la clasificacion ordenada
        List<TeamStat> teamStatList = 
            new LinkedList<>(teamStatGroup.getTeamStat().values());
        Collections.sort(teamStatList, new TeamStatRound1Comparator());   
        for (TeamStat teamStat : teamStatList) {
            logger.debug(teamStat);
        }
        String rankingTeamFilename = FileNameManager.getRankingFilename(
            season.getLastRoundId());
        resultFileManager.setRankingTeamFile(teamStatList, 
            new File(rankingTeamFilename));
    }
    
}
