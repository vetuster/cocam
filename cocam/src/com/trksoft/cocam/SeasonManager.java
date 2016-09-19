/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import com.trksoft.util.StringUtil;
import java.io.File;
import java.util.List;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
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
    
    public TeamStatGroup loadTeamStats()  throws Exception {
        TeamGroup teamGroup = loadTeamGroup();
        /*
        try {
            JAXBContext jaxbContext
                = JAXBContext.newInstance(TeamGroup.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                true);
            jaxbMarshaller.marshal(teamGroup, System.out);
        } catch (JAXBException jaxbex) {
            logger.fatal(jaxbex);
            throw new CocamException(jaxbex);
        }*/
        Season season = builFromResultFiles();
        TeamStatGroup teamStatGroup = new TeamStatGroup();
        // iniciar las estadísticas - así el equipo que descansa consta con 0
        // y siempre existe el equipo en la busqueda
        teamGroup.getTeam().stream().forEach((team) -> {
            TeamStat teamStat = new TeamStat();
            teamStat.setTeamId(team.getTeamId());
            teamStat.setTeamDenom(team.getTeamDenom());
            teamStatGroup.getTeamStat().put(team.getTeamId(), teamStat);
        });

        
        Set<Match> matchSet = season.getMatch();
        for (Match match : matchSet) {
            //obtener las estadisticas en curso del equipo local
            TeamStat localTeamStat = teamStatGroup.getTeamStat()
                .get(match.getLocalTeamId());
            //obtener las estadisticas en curso del equipo visitante
            TeamStat visitingTeamStat = teamStatGroup.getTeamStat()
                .get(match.getVisitingTeamId());
            
            //actualizar estadisticas
            localTeamStat.update(match);
            visitingTeamStat.update(match);
        }
        
        try {
            File teamStatGroupFile = new File("resources/teamStatGroup-J01.xml");
            JAXBContext jaxbContext
                = JAXBContext.newInstance(TeamStatGroup.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                true);
            jaxbMarshaller.marshal(teamStatGroup, teamStatGroupFile);
            jaxbMarshaller.marshal(teamStatGroup, System.out);
        } catch (JAXBException jaxbex) {
            logger.fatal(jaxbex);
            throw new CocamException(jaxbex);
        }
        return teamStatGroup;
    }
    
    private Season builFromResultFiles() throws CocamException {
        Season season = new Season();
        ResultFileManager rfm = new ResultFileManager();
        List<ResultRecord> resultRecordList = rfm.getResultRecord();
        if (logger.isTraceEnabled()) {
            resultRecordList.stream().forEach((resultRecord) -> {
                logger.trace(resultRecord);
            });
        }
        
        Integer lastRoundId = Integer.MAX_VALUE;
        Match currentMatch = null;
        for (ResultRecord resultRecord : resultRecordList) {
            logger.info("loading->" + resultRecord);
            
            if (season.getSeasonId() == null) {
                season.setSeasonId(resultRecord.getSeasonId());
            }
            
            if (resultRecord.isMatchHead()) {
                currentMatch = new Match();
                currentMatch.setRoundId(resultRecord.getRoundId());
                currentMatch.setRoundDate(resultRecord.getRoundDate());
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
            currentTable.setLocalPlayerNameOne(
                resultRecord.getLocalPayerNameOne());
            currentTable.setLocalPlayerNameTwo(
                resultRecord.getLocalPayerNameTwo());
            currentTable.setLocalPairScore(resultRecord.getLocalPairScore());
            currentTable.setVisitingPlayerNameOne(
                resultRecord.getVisitingPayerNameOne());
            currentTable.setVisitingPlayerNameTwo(
                resultRecord.getVisitingPayerNameTwo());
            currentTable.setVisitingPairScore(
                resultRecord.getVisitingPairScore());
            currentMatch.getTable().add(currentTable);
        }
        marshall(season,
            new File("resources/season_2016_17-J01.xml"));
        return season;
    }
    
    private void marshall(Season season, File seasonFile)
        throws CocamException {
        try {
            JAXBContext jaxbContext
                = JAXBContext.newInstance(Season.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(season, seasonFile);
            jaxbMarshaller.marshal(season, System.out);
        } catch (JAXBException jaxbex) {
            logger.error(jaxbex);
            throw  new CocamException(jaxbex);
        }
    }
    
    private TeamGroup loadTeamGroup() throws CocamException {
        return TeamGroup.unmarshall(
            new File("resources/Avilesino_55-TeamGroup.xml"));
    }
    
    private void buildTeamGroup() throws CocamException {
        TeamGroup teamGroup = TeamGroup.unmarshall(
            new File("resources/Avilesino_55-TeamGroup-IN.xml"));
        try {
            File file = new File("resources/Avilesino_55-TeamGroup.xml");
            JAXBContext jaxbContext
                = JAXBContext.newInstance(TeamGroup.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                true);

            jaxbMarshaller.marshal(teamGroup, file);
            jaxbMarshaller.marshal(teamGroup, System.out);

        } catch (JAXBException jaxbex) {
            logger.error(jaxbex);
        }
    }
}
