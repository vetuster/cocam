/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import java.io.File;
import java.util.List;
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
    
    public static void main(String[] args) throws Exception {
        SeasonManager seasonManager = new SeasonManager();
        //seasonManager.buildTeamGroup();
        Season season = seasonManager.builFromFiles();
    }
    
    public Season builFromFiles() throws CocamException {
        Season season = new Season();
        ResultFileManager rfm = new ResultFileManager();
        List<ResultRecord> resultRecordList = rfm.getResultRecord();
        if (logger.isTraceEnabled()) {
            resultRecordList.stream().forEach((resultRecord) -> {
                logger.trace(resultRecord);
            });
        }
        
        Integer lastRoundId = Integer.MAX_VALUE;
        Round currentRound = null;
        Match currentMatch = null;
        for (ResultRecord resultRecord : resultRecordList) {
            logger.info("loading->" + resultRecord);
            
            if (season.getSeasonId() == null) {
                season.setSeasonId(resultRecord.getSeasonId());
            }
            
            logger.trace("Round");
            if (!resultRecord.getRoundId().equals(lastRoundId)) {
                lastRoundId = resultRecord.getRoundId();
                currentRound = new Round();
                currentRound.setRoundId(resultRecord.getRoundId());
                currentRound.setRoundDate(resultRecord.getRoundDate());
                season.getRound().add(currentRound);
            }
            
            logger.trace("Match");
            if (resultRecord.isMatchHead()) {
                currentMatch = new Match();
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
                currentRound.getMatch().add(currentMatch);
            }
            logger.trace("currentMatch->"
                + (currentMatch == null?
                    "NULL": "currentMatch OK"));
            
            logger.trace("Table");
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
        marshall(season, new File("resources/season_55-R01.xml"));
        
        return season;
    }
    
    private void marshall(Season season, File seasonFile)
        throws CocamException {
        try {
            JAXBContext jaxbContext
                = JAXBContext.newInstance(Season.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                true);

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
