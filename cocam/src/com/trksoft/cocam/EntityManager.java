/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import com.trksoft.util.StringUtil;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author PSUANZES
 */
public class EntityManager {
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(EntityManager.class);
    
    private static TeamEntity teamEntity = new TeamEntity();
    private static PlayerEntity playerEntity = new PlayerEntity();
    private static ResultRecordEntity resultRecordEntity = 
        new ResultRecordEntity();
    

    //Bill Pugh Solution for singleton pattern
    private EntityManager() {
    }

    private static class LazyHolder {
        private static final EntityManager INSTANCE = new EntityManager();
    }

    protected static EntityManager getInstance() {
        return EntityManager.LazyHolder.INSTANCE;
    }
    
    
    protected List<Team> findAllTeam() throws CocamException {
        if (teamEntity.getTeam().isEmpty()) {
            // carga el conjunto de equipos
            try {
                teamEntity = TeamEntity.unmarshall(
                    new File(FileNameManager.getTeamEntityFilename()));
            } catch (JAXBException jaxbex) {
                logger.fatal(jaxbex);
                throw new CocamException(jaxbex);
            }
            logger.info("teamEntity LOADED ("
                + FileNameManager.getTeamEntityFilename()
                + ")->TOTAL teams"
                + StringUtil.enclose(teamEntity.getTeam().size())
            );
        }
        return new LinkedList(teamEntity.getTeam());
    }
    
    protected List<Player> findAllPlayer() throws CocamException {
        if (playerEntity.getPlayer().isEmpty()) {
            // carga el conjunto de equipos
            try {
                playerEntity = PlayerEntity.unmarshall(
                    new File(FileNameManager.getPlayerEntityFilename()));
            } catch (JAXBException jaxbex) {
                logger.fatal(jaxbex);
                throw new CocamException(jaxbex);
            }
            logger.info("playerEntity LOADED"
                + "->TOTAL players"
                + StringUtil.enclose(playerEntity.getPlayer().size())
            );
        }
        return new LinkedList(playerEntity.getPlayer());
    }
    
    
    protected Season findSeason() throws CocamException {
        // carga los resultados que conforman la temporada
        loadResultRecordEntity();
        
        Season season = new Season();

        Map<MatchPK, Match> matchHash = new HashMap<>();
        for (ResultRecord resultRecord : resultRecordEntity.getResultRecord()) {
            logger.debug("loading->" + resultRecord);
            
            if (season.getSeasonId() == null) {
                season.setSeasonId(resultRecord.getSeasonId());
            }
            
            if (resultRecord.isMatchHead()) {
                season.setLastDayId(resultRecord.getSeasonDayId());
            }
            
            MatchPK matchPK = resultRecord.getMatchPK();
            Match match;
            if (matchHash.containsKey(matchPK)) {
                match = matchHash.get(matchPK);
            } else {
                match = resultRecord.getMatch();
                matchHash.put(matchPK, match);
            }
            match.addTable(resultRecord.getTable());
            
        }
        season.getMatch().addAll(matchHash.values());
        return season;
    }
    
    private void loadResultRecordEntity() throws CocamException {
        if (resultRecordEntity.getResultRecord().isEmpty()) {
            
            // convierte los archivos de resultados procedentes del excel (.CSV)
            // a un archivo xml de tipo ResultRecord
            ResultRecordFileManager resultRecordFileManager
                = new ResultRecordFileManager();
            resultRecordFileManager.loadXmlResultRecordEntityFile();
            
            // carga el conjunto de resultados a partir del archivo XML
            try {
                resultRecordEntity = ResultRecordEntity.unmarshall(
                    new File(FileNameManager.getResultRecordEntityFilename()));
            } catch (JAXBException jaxbex) {
                logger.fatal(jaxbex);
                throw new CocamException(jaxbex);
            }
            logger.info("resultRecordEntity LOADED"
                + "->TOTAL results"
                + StringUtil.enclose(
                    resultRecordEntity.getResultRecord().size())
            );
        }
    }
    
    protected void marshallPlayerStat(List<PlayerStat> playerStatList,
        String fileName) throws CocamException {
        PlayerStatEntity pse = new PlayerStatEntity();
        pse.getPlayerStat().addAll(playerStatList);
        // graba el conjunto de estadisticas
        try {
            pse.marshall(new File(fileName));
        } catch (JAXBException jaxbex) {
            logger.fatal(jaxbex);
            throw new CocamException(jaxbex);
        }
        logger.info("playerStatEntity CREATED"
            + "->TOTAL playerStat"
            + StringUtil.enclose(pse.getPlayerStat().size())
        );
    }
    
}
