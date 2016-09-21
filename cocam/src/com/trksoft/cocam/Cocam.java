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
        seasonManager.loadSeason(resultRecordList);
        String seasonFilename = FileNameManager.getSeasonFilename(
            seasonManager.getSeason().getLastRoundId());
        seasonManager.getSeason().marshall(new File(seasonFilename));
        int totalTables = 0;
        totalTables =
            seasonManager.getSeason().getMatch().stream().map((match) ->
            match.getTable().size()).reduce(totalTables, Integer::sum);
        logger.info("seasonId"
            + StringUtil.enclose(seasonManager.getSeason().getSeasonId())
            + ",lastRoundId"
            + StringUtil.enclose(seasonManager.getSeason().getLastRoundId())
            + ",TOTAL matches" 
            + StringUtil.enclose(seasonManager.getSeason().getMatch().size())
            + ",TOTAL tables"
            + StringUtil.enclose(totalTables)
        );
        
        // cargar las estadisticas de cada equipo
        TeamStatGroup teamStatGroup =
            seasonManager.getTeamStatGroup(teamGroup);
        String teamStatGroupFilename = FileNameManager.getTeamStatGroupFilename(
            seasonManager.getSeason().getLastRoundId());
        teamStatGroup.marshall(new File(teamStatGroupFilename));
        
        // ordenar las estadisticas para obtener la clasificacion ordenada
        List<TeamStat> teamStatList = 
            new LinkedList<>(teamStatGroup.getTeamStat().values());
        Collections.sort(teamStatList, new TeamStatRound1Comparator());   
        for (TeamStat teamStat : teamStatList) {
            logger.info(teamStat);
        }
        String rankingTeamFilename = FileNameManager.getRankingFilename(
            seasonManager.getSeason().getLastRoundId());
        resultFileManager.setTeamRankingFile(teamStatList, 
            new File(rankingTeamFilename));
        
        // No se dispone de fichero de jugadores porque se introduciran en la
        // excel de forma escrita diferente a la que consta en las fichas
        // los jugadores se extraen de la temporada, y se agrupan en un clase
        // interpuesta PlayerGroup cuando lo lógico es que pertenecierana Team
        // pero en este caso el Team se carga sin jugadores.
        PlayerGroup playerGroup = seasonManager.getPlayerGroup();
        logger.info("TOTAL players"
            + StringUtil.enclose(playerGroup.getPlayer().size()));
        String playerGroupFilename = FileNameManager.getPlayerGroupFilename(
            seasonManager.getSeason().getLastRoundId());
        playerGroup.marshall(new File(playerGroupFilename));
    }
    
}
