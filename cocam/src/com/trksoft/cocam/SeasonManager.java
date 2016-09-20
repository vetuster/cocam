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
    
    public Season builFromResultFiles() throws CocamException {
        Season season = new Season();
        ResultFileManager rfm = new ResultFileManager();
        List<ResultRecord> resultRecordList = rfm.getResultRecord();
        if (logger.isTraceEnabled()) {
            resultRecordList.stream().forEach((resultRecord) -> {
                logger.trace(resultRecord);
            });
        }
        
        Match currentMatch = null;
        for (ResultRecord resultRecord : resultRecordList) {
            logger.info("loading->" + resultRecord);
            
            if (season.getSeasonId() == null) {
                season.setSeasonId(resultRecord.getSeasonId());
            }
            
            if (resultRecord.isMatchHead()) {
                season.setLastRoundId(resultRecord.getRoundId());
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
        return season;
    }
    
}
