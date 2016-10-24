/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import com.trksoft.util.StringUtil;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author jasuarez
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "seasonId",
    "lastDayId",
    "match"
})
@XmlRootElement
public class Season {
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(Season.class);
    
    @XmlAttribute (required = true)
    private String seasonId;
    
    @XmlAttribute (required = true)
    private Integer lastDayId;
    
    @XmlElement(required = true)
    private final SortedSet<Match> match;
    

    public Season() {
        match = new TreeSet<>();
    }
    
    public String getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(String seasonId) {
        this.seasonId = seasonId;
    }

    public Integer getLastDayId() {
        return lastDayId;
    }

    public void setLastDayId(Integer lastDayId) {
        this.lastDayId = lastDayId;
    }

    public SortedSet<Match> getMatch() {
        return match;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Season->");
        sb.append("seasonId");
        sb.append(StringUtil.enclose(seasonId));
        sb.append("lastDayId");
        sb.append(StringUtil.enclose(lastDayId));
        sb.append(match.stream().map(Object::toString).
            collect(Collectors.joining("->")));
        return sb.toString();
    }

    public void marshall(File seasonFile) throws JAXBException {
        try {
            JAXBContext jaxbContext
                = JAXBContext.newInstance(Season.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(this, seasonFile);
        } catch (JAXBException jaxbex) {
            logger.fatal(jaxbex);
            throw jaxbex;
        }
    }
    
   public static Season unmarshall(File seasonFile) throws JAXBException {
        Season season = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Season.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            season = (Season) jaxbUnmarshaller.unmarshal(seasonFile);
        } catch (JAXBException jaxbex) {
            logger.fatal(jaxbex);
            throw jaxbex;
        }
        return season;
    }
   
    public static Season build(final List<Result> resultList)
        throws CocamException {
        Season season = new Season();
        Match match = null;
        for (Result result : resultList) {
            logger.debug("loading->" + result);
            
            if (season.getSeasonId() == null) {
                season.setSeasonId(result.getSeasonId());
            }
            
            if (result.isMatchHead()) {
                season.setLastDayId(result.getSeasonDayId());
                match = Match.build(result);
                season.getMatch().add(match);
            }
            
            match.getTable().add(Table.build(result));
        }
        return season;
    }
    
    
    public List<TeamStat> getTeamStat(final List<Team> teamList,
        LeagueType leagueType) {
        Map<String, TeamStat> teamStatHash = new java.util.HashMap<>();

        // partiendo de la lista de equipo iniciamos las de estadisticas
        // para cada tipo de liga y para cada equipo.
        // Aprovechando para informar la Denom y asegurando
        // que las busquedas en el hash siempre encuentran
        teamList.stream().forEach((team) -> {
            TeamStat teamStat = new TeamStat();
            teamStat.setTeamId(team.getTeamId());
            teamStat.setTeamDenom(team.getTeamDenom());
            teamStat.setLeagueType(leagueType);
            teamStatHash.put(teamStat.getTeamStatKey(), teamStat);
        });


        for (Match match : getMatch()) {
            // equipo local
            String teamStatKey = TeamStat.getTeamStatKey(match.getLeagueType(),
                match.getLocalTeamId());
            if (!teamStatHash.containsKey(teamStatKey)) {
                StringBuilder sb = new StringBuilder("TEAM DOES NOT EXISTS");
                sb.append(StringUtil.enclose(teamStatKey));
                logger.fatal(sb.toString());
                throw new RuntimeException(sb.toString());
            }
            TeamStat localTeamStat = teamStatHash.get(teamStatKey);
            localTeamStat.update(match);

            //equipo visitante
            teamStatKey = TeamStat.getTeamStatKey(match.getLeagueType(),
                match.getVisitingTeamId());
            TeamStat visitingTeamStat = teamStatHash.get(teamStatKey);
            visitingTeamStat.update(match);
        }
        return new LinkedList(teamStatHash.values());
    }
    
    
    public List<PlayerStat> getPlayerStat(final List<Team> teamList,
        final List<Player> playerList, LeagueType leagueType) throws CocamException {
        Map<PlayerStatPK, PlayerStat> playerStatHash = new java.util.HashMap<>();

        // partiendo de la lista de juagdores iniciamos las de estadisticas
        // para cada tipo de liga y para cada jugador.
        // Aprovechando para informar el NIck del jugaor, la Denom del equipo
        // y asegurando que las busquedas en el hash siempre encuentran
        for (Player player : playerList) {
            PlayerStatPK playerStatPK
                = new PlayerStatPK(player.getTeamId(),
                    player.getPlayerNick(),
                    leagueType);
            PlayerStat playerStat = new PlayerStat(playerStatPK);
            playerStatHash.put(playerStatPK, playerStat);
        }
        
        // para cada mesa de cada encuentro
        for (Match match : getMatch()) {
            for (Table table : match.getTable()) {
                // jugador LOCAL ONE
                PlayerStatPK playerStatPK = new PlayerStatPK(
                    match.getLocalTeamId(),
                    table.getLocalPlayerNickOne(),
                    match.getLeagueType());
                if (!playerStatHash.containsKey(playerStatPK)) {
                    // si es NO PRESENTADO, se incorpora jugador "virtual" 
                    // para que cuadren las estadísticas
                    if (table.isLocalWO()) {
                        playerStatHash.put(playerStatPK,
                            new PlayerStat(playerStatPK));
                    } else {
                        StringBuilder sb = new StringBuilder(
                            "LOCAL PLAYER ONE DOES NOT EXISTS->");
                        sb.append(playerStatPK.toString());
                        logger.fatal(sb.toString());
                        throw new CocamException(sb.toString());
                    }
                }
                PlayerStat localPlayerOneStat
                    = playerStatHash.get(playerStatPK);
                localPlayerOneStat.update(table, true);
                if (localPlayerOneStat.getTablePlayed() > getLastDayId()) {
                    StringBuilder sb = new StringBuilder(
                        "LOCAL PLAYER ONE PLAYED MORE THA POSSIBLE->");
                    sb.append(localPlayerOneStat.getPlayerStatPK().toString());
                    sb.append(",played");
                    sb.append(StringUtil.enclose(localPlayerOneStat.toString()));
                    sb.append(",last season day");
                    sb.append(StringUtil.enclose(getLastDayId()));
                    logger.fatal(sb.toString());
                    throw new CocamException(sb.toString());
                }

                // jugador LOCAL TWO
                playerStatPK = new PlayerStatPK(
                    match.getLocalTeamId(),
                    table.getLocalPlayerNickTwo(),
                    match.getLeagueType());
                if (!playerStatHash.containsKey(playerStatPK)) {
                    // si es NO PRESENTADO, se incorpora jugador "virtual" 
                    // para que cuadren las estadísticas
                    if (table.isLocalWO()) {
                        playerStatHash.put(playerStatPK,
                            new PlayerStat(playerStatPK));
                    } else {
                        StringBuilder sb = new StringBuilder(
                            "LOCAL PLAYER TWO DOES NOT EXISTS->");
                        sb.append(playerStatPK.toString());
                        logger.fatal(sb.toString());
                        throw new RuntimeException(sb.toString());
                    }
                }
                PlayerStat localPlayerTwoStat
                    = playerStatHash.get(playerStatPK);
                localPlayerTwoStat.update(table, true);
                if (localPlayerTwoStat.getTablePlayed() > getLastDayId()) {
                    StringBuilder sb = new StringBuilder(
                        "LOCAL PLAYER TWO PLAYED MORE THA POSSIBLE->");
                    sb.append(localPlayerTwoStat.getPlayerStatPK().toString());
                    sb.append(",played");
                    sb.append(StringUtil.enclose(localPlayerTwoStat.toString()));
                    sb.append(",last season day");
                    sb.append(StringUtil.enclose(getLastDayId()));
                    logger.fatal(sb.toString());
                    throw new CocamException(sb.toString());
                }

                // jugador VISITING ONE
                playerStatPK = new PlayerStatPK(
                    match.getVisitingTeamId(),
                    table.getVisitingPlayerNickOne(),
                    match.getLeagueType());
                if (!playerStatHash.containsKey(playerStatPK)) {
                    // si es NO PRESENTADO, se incorpora jugador "virtual" 
                    // para que cuadren las estadísticas
                    if (table.isVisitingWO()) {
                        playerStatHash.put(playerStatPK,
                            new PlayerStat(playerStatPK));
                    } else {
                        StringBuilder sb = new StringBuilder(
                            "VISITING PLAYER ONE DOES NOT EXISTS->");
                        sb.append(playerStatPK.toString());
                        logger.fatal(sb.toString());
                        throw new CocamException(sb.toString());
                    }
                }
                PlayerStat visitingPlayerOneStat
                    = playerStatHash.get(playerStatPK);
                visitingPlayerOneStat.update(table, false);
                if (visitingPlayerOneStat.getTablePlayed() > getLastDayId()) {
                    StringBuilder sb = new StringBuilder(
                        "VISITING PLAYER ONE PLAYED MORE THA POSSIBLE->");
                    sb.append(visitingPlayerOneStat.getPlayerStatPK().toString());
                    sb.append(",played");
                    sb.append(StringUtil.enclose(visitingPlayerOneStat.toString()));
                    sb.append(",last season day");
                    sb.append(StringUtil.enclose(getLastDayId()));
                    logger.fatal(sb.toString());
                    throw new CocamException(sb.toString());
                }

                // jugador VISITING TWO
                  playerStatPK = new PlayerStatPK(
                        match.getVisitingTeamId(),
                        table.getVisitingPlayerNickTwo(),
                        match.getLeagueType());
                if (!playerStatHash.containsKey(playerStatPK)) {
                    // si es NO PRESENTADO, se incorpora jugador "virtual" 
                    // para que cuadren las estadísticas
                    if (table.isVisitingWO()) {
                        playerStatHash.put(playerStatPK,
                            new PlayerStat(playerStatPK));
                    } else {
                        StringBuilder sb = new StringBuilder(
                            "VISITING PLAYER TWO DOES NOT EXISTS->");
                        sb.append(playerStatPK.toString());
                        logger.fatal(sb.toString());
                        throw new CocamException(sb.toString());
                    }
                }
                PlayerStat visitingPlayerTwoStat
                    = playerStatHash.get(playerStatPK);
                visitingPlayerTwoStat.update(table, false);
                if (visitingPlayerTwoStat.getTablePlayed() > getLastDayId()) {
                    StringBuilder sb = new StringBuilder(
                        "VISITING PLAYER TWO PLAYED MORE THA POSSIBLE->");
                    sb.append(visitingPlayerTwoStat.getPlayerStatPK().toString());
                    sb.append(",played");
                    sb.append(StringUtil.enclose(visitingPlayerTwoStat.toString()));
                    sb.append(",last season day");
                    sb.append(StringUtil.enclose(getLastDayId()));
                    logger.fatal(sb.toString());
                    throw new CocamException(sb.toString());
                }
            } // for table
        } // for match
        return new LinkedList(playerStatHash.values());
    }
    
}