/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author PSUANZES
 */
public class SeasonManager {
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(SeasonManager.class);
    
    
    private Season season;

    protected SeasonManager() {
        this.season = new Season();
    }
    
    protected final Season getSeason() {
        return season;
    }
    
    
    protected void loadSeason(final List<ResultRecord> resultRecordList)
        throws CocamException {
        Match currentMatch = null;
        for (ResultRecord resultRecord : resultRecordList) {
            logger.debug("loading->" + resultRecord);
            
            if (season.getSeasonId() == null) {
                season.setSeasonId(resultRecord.getSeasonId());
            }
            
            if (resultRecord.isMatchHead()) {
                season.setLastDayId(resultRecord.getDayId());
                currentMatch = new Match();
                currentMatch.setRoundId(resultRecord.getRoundId());
                currentMatch.setDayId(resultRecord.getDayId());
                currentMatch.setDayDate(resultRecord.getDayDate());
                currentMatch.setMatchId(resultRecord.getLocalTeamId()
                    + resultRecord.getVisitingTeamId());
                currentMatch.setLocalTeamId(resultRecord.getLocalTeamId());
                currentMatch.setLocalTeamName(resultRecord.getLocalTeamName());
                currentMatch.setLocalTeamScore(
                    resultRecord.getLocalTeamScore());
                currentMatch.setVisitingTeamId(
                    resultRecord.getVisitingTeamId());
                currentMatch.setVisitingTeamName(
                    resultRecord.getVisitingTeamName());
                currentMatch.setVisitingTeamScore(
                    resultRecord.getVisitingTeamScore());
                season.getMatch().add(currentMatch);
            }
            
            Table currentTable = new Table();
            currentTable.setTableId(resultRecord.getTableId());
            currentTable.setLocalPlayerNickOne(
                resultRecord.getLocalPlayerNickOne());
            currentTable.setLocalPlayerNickTwo(
                resultRecord.getLocalPlayerNickTwo());
            currentTable.setLocalPairScore(resultRecord.getLocalPairScore());
            currentTable.setVisitingPlayerNickOne(
                resultRecord.getVisitingPlayerNickOne());
            currentTable.setVisitingPlayerNickTwo(
                resultRecord.getVisitingPlayerNickTwo());
            currentTable.setVisitingPairScore(
                resultRecord.getVisitingPairScore());
            currentMatch.getTable().add(currentTable);
        }
    }
    
    protected TeamStatGroup getTeamStatGroup(final TeamGroup teamGroup) {
        TeamStatGroup teamStatGroup =  new TeamStatGroup();

        //carga estadísticas
        // iniciar las estadísticas - así el equipo que descansa consta con 0
        // y siempre existe el equipo en la busqueda
        teamGroup.getTeam().stream().forEach((team) -> {
            TeamStat initTeamStat = new TeamStat();
            initTeamStat.setTeamId(team.getTeamId());
            initTeamStat.setTeamDenom(team.getTeamDenom());
            teamStatGroup.getTeamStat().put(team.getTeamId(), initTeamStat);
        });

        // cargar las estadisticas de cada equipo ya precargado a cero
        season.getMatch().stream().forEach((match) -> {
            //actualizar estadisticas
            teamStatGroup.update(match);
        });
        return teamStatGroup;
    }
    
    protected PlayerGroup getPlayerGroup() {
        PlayerGroup playerGroup =  new PlayerGroup();

        // cargar las estadisticas de cada jugador ya precargado a cero
        // se valida que haya 208 jugadores por jornada
        season.getMatch().stream().forEach((match) -> {
            
            for (Table table : match.getTable()) {
                // local One
                Player player = new Player();
                player.setTeamId(match.getLocalTeamId());
                player.setPlayerNick(table.getLocalPlayerNickOne());
                playerGroup.getPlayer().add(player);
                // local Two
                player = new Player();
                player.setTeamId(match.getLocalTeamId());
                player.setPlayerNick(table.getLocalPlayerNickTwo());
                playerGroup.getPlayer().add(player);
                // visiting One
                player = new Player();
                player.setTeamId(match.getVisitingTeamId());
                player.setPlayerNick(table.getVisitingPlayerNickOne());
                playerGroup.getPlayer().add(player);
                // visiting Two
                player = new Player();
                player.setTeamId(match.getVisitingTeamId());
                player.setPlayerNick(table.getVisitingPlayerNickTwo());
                playerGroup.getPlayer().add(player);
            }
        });
        return playerGroup;
    }
    
    protected PlayerStatGroup getPlayerStatGroup(final TeamGroup teamGroup, 
        final PlayerGroup playerGroup) {
        PlayerStatGroup playerStatGroup =  new PlayerStatGroup();

        // iniciar las estadísticas, así el juagdor siempre existe en la busqueda
        playerGroup.getPlayer().stream().forEach((player) -> {
            
            // buscamos el equipo del jugador
            Optional<Team> optional = teamGroup.getTeam().stream().
                filter(team -> team.getTeamId().equals(player.getTeamId())).
                findFirst();
            Team playerTeam = optional.get();
            
            // inicio de las estadisticas del jugador
            PlayerStat initPlayerStat = new PlayerStat();
            initPlayerStat.setTeamId(player.getTeamId());
            initPlayerStat.setTeamDenom(playerTeam.getTeamDenom());
            initPlayerStat.setPlayerNick(player.getPlayerNick());
            playerStatGroup.getPlayerStat().put(
                player.getPlayerKey(), initPlayerStat);
        });

        // cargar las estadisticas de cada jugador de cada mesa ya precargado
        // a cero
        season.getMatch().stream().forEach((match) -> {
            //actualizar estadisticas
            playerStatGroup.update(match);
        });
        return playerStatGroup;
    }
}
