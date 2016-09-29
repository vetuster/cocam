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
    
    
    private final Season season;
    
    private final SeasonStat seasonStat;

    protected SeasonManager() {
        this.season = new Season();
        this.seasonStat = new SeasonStat();
    }
    
    protected final Season getSeason() {
        return season;
    }
    
    
    protected void loadResults(final List<Result> resultList)
        throws CocamException {
        Match currentMatch = null;
        for (Result result : resultList) {
            logger.debug("loading->" + result);
            
            if (season.getSeasonId() == null) {
                season.setSeasonId(result.getSeasonId());
            }
            
            if (result.isMatchHead()) {
                season.setLastDayId(result.getDayId());
                currentMatch = new Match();
                currentMatch.setLeagueType(
                    LeagueType.valueOf(result.getLeagueType()));
                currentMatch.setRoundId(result.getRoundId());
                currentMatch.setDayId(result.getDayId());
                currentMatch.setDayDate(result.getDayDate());
                currentMatch.setMatchId(result.getLocalTeamId()
                    + result.getVisitingTeamId());
                currentMatch.setLocalTeamId(result.getLocalTeamId());
                currentMatch.setLocalTeamName(result.getLocalTeamName());
                currentMatch.setLocalTeamScore(result.getLocalTeamScore());
                currentMatch.setVisitingTeamId(result.getVisitingTeamId());
                currentMatch.setVisitingTeamName(result.getVisitingTeamName());
                currentMatch.setVisitingTeamScore(result.getVisitingTeamScore());
                season.getMatch().add(currentMatch);
            }
            
            Table currentTable = new Table();
            currentTable.setTableId(result.getTableId());
            currentTable.setLocalPlayerNickOne(result.getLocalPlayerNickOne());
            currentTable.setLocalPlayerNickTwo(result.getLocalPlayerNickTwo());
            currentTable.setLocalPairScore(result.getLocalPairScore());
            currentTable.setVisitingPlayerNickOne(result.getVisitingPlayerNickOne());
            currentTable.setVisitingPlayerNickTwo(result.getVisitingPlayerNickTwo());
            currentTable.setVisitingPairScore(result.getVisitingPairScore());
            currentMatch.getTable().add(currentTable);
        }
    }
    
    protected void loadStats(final List<Team> teamList,
        final PlayerGroup playerGroup) {
        seasonStat.setSeasonId(season.getSeasonId());
    }
    
    protected TeamStatGroup getTeamStatGroup(final List<Team> teamList) {
        TeamStatGroup teamStatGroup =  new TeamStatGroup();
        teamStatGroup.setLeagueType(LeagueType.REG);

        //carga estadísticas
        // iniciar las estadísticas - así el equipo que descansa consta con 0
        // y siempre existe el equipo en la busqueda
        teamList.stream().forEach((team) -> {
            TeamStat initTeamStat = new TeamStat();
            initTeamStat.setTeamId(team.getTeamId());
            initTeamStat.setTeamDenom(team.getTeamDenom());
            initTeamStat.setLeagueType(teamStatGroup.getLeagueType());
            teamStatGroup.getTeamStat().put(team.getTeamId(), initTeamStat);
        });

        // cargar las estadisticas de cada equipo ya precargado a cero
        season.getMatch().stream().forEach((match) -> {
            //actualizar estadisticas
            teamStatGroup.update(match);
        });
        return teamStatGroup;
    }
    
    // obtener la la lista de jugadores de cada equipo a partir de los
    // partidos disputados
    protected PlayerGroup getPlayerGroup() {
        PlayerGroup playerGroup =  new PlayerGroup();
        
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
    
    protected PlayerStatGroup getPlayerStatGroup(final List<Team> teamList, 
        final PlayerGroup playerGroup) {
        PlayerStatGroup playerStatGroup =  new PlayerStatGroup();
        playerStatGroup.setLeagueType(LeagueType.REG);

        // iniciar las estadísticas, así el juagdor siempre existe en la busqueda
        playerGroup.getPlayer().stream().forEach((player) -> {
            
            // buscamos el equipo del jugador
            Optional<Team> optional = teamList.stream().
                filter(team -> team.getTeamId().equals(player.getTeamId())).
                findFirst();
            Team playerTeam = optional.get();
            
            // inicio de las estadisticas del jugador
            PlayerStat initPlayerStat = new PlayerStat();
            initPlayerStat.setTeamId(player.getTeamId());
            initPlayerStat.setTeamDenom(playerTeam.getTeamDenom());
            initPlayerStat.setPlayerNick(player.getPlayerNick());
            initPlayerStat.setLeagueType(playerStatGroup.getLeagueType());
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
