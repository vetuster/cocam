/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import com.trksoft.util.StringUtil;
import java.io.File;
import java.util.Collections;
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
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
                       
        // entity adm
        EntityManager entityManager = EntityManager.getInstance();
        
        
        // obtención de la lista de equipos
        List<Team> teamList = entityManager.findAllTeam();
        
        // obtención de la lista de jugadores
        List<Player> playerList = entityManager.findAllPlayer();
        
        // obtencion resultados de la temporada
        Season season = entityManager.findSeason();
        
        // generar XML con la temporada
        String seasonFilename = FileNameManager.getSeasonFilename(
            season.getLastDayId());
        season.marshall(new File(seasonFilename));
        int totalTables = 0;
        totalTables =
            season.getMatch().stream().map((match) ->
            match.getTable().size()).reduce(totalTables, Integer::sum);
        logger.info("season LOADED"
            + "->seasonId"
            + StringUtil.enclose(season.getSeasonId())
            + ",lastRoundId"
            + StringUtil.enclose(season.getLastDayId())
            + ",TOTAL matches" 
            + StringUtil.enclose(season.getMatch().size())
            + ",TOTAL tables"
            + StringUtil.enclose(totalTables)
        );

        
        // LIGA REGULAR
        // obtener y ordenar las estadisticas de los equipos 
        // para obtener la clasificacion ordenada
        List<TeamStat> teamStatList = season.getTeamStat(teamList,
            LeagueType.REG);
        Collections.sort(teamStatList, new TeamStatComparator());  
        logger.info("Team Stats OBTAINED"
            + ",TOTAL teamStat" 
            + StringUtil.enclose(teamStatList.size())
        );
        teamStatList.stream().forEach((teamStat) -> {
            logger.info(teamStat);
        });
        
        
        RankingFileManager rankingFileManager = new RankingFileManager();
        
        
        // generar CVS con clasificacion de equipos
        String teamRankingFilename = FileNameManager.getTeamRankingFilename(
            season.getLastDayId(), LeagueType.REG);
        rankingFileManager.writeTeamRankingFile(teamStatList, 
            new File(teamRankingFilename));
        
        
        // LIGA REGULAR
        // obtener y ordenar las estadisticas de los jugadores 
        // para obtener la clasificacion ordenada
        List<PlayerStat> playerStatList = 
            season.getPlayerStat(teamList, playerList, LeagueType.REG);
        Collections.sort(playerStatList, new PlayerStatComparator());
        logger.info("Player Stats OBTAINED"
            + ",TOTAL playerStat" 
            + StringUtil.enclose(playerStatList.size())
        );
        if (logger.isTraceEnabled()) {
            playerStatList.stream().forEach((playerStat) -> {
                logger.trace(playerStat);
            });
        }
        
        // generar CSV con clasificacion de jugadores
        String playerRankingFilename = FileNameManager.getPlayerRankingFilename(
            season.getLastDayId(), LeagueType.REG);
        rankingFileManager.writePlayerRankingFile(teamList, playerStatList,
            new File(playerRankingFilename));
        // generar XML
        String playerRankingFilenameXML = 
            FileNameManager.getPlayerRankingFilenameXML(
                season.getLastDayId(), LeagueType.REG);
        entityManager.marshallPlayerStat(playerStatList,
            playerRankingFilenameXML);
        
        
    }

}
