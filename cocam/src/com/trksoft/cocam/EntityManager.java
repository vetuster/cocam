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
    private static ResultEntity resultEntity = new ResultEntity();
    

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
            logger.info("teamEntity LOADED"
                + "->TOTAL teams"
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
    
    protected List<Result> findAllResult() throws CocamException {
        if (resultEntity.getResult().isEmpty()) {
            // carga el conjunto de resultados
            try {
                resultEntity = ResultEntity.unmarshall(
                    new File(FileNameManager.getResultEntityFilename()));
            } catch (JAXBException jaxbex) {
                logger.fatal(jaxbex);
                throw new CocamException(jaxbex);
            }
            logger.info("resultEntity LOADED"
                + "->TOTAL results"
                + StringUtil.enclose(resultEntity.getResult().size())
            );
        }
        return new LinkedList(resultEntity.getResult());
    }
    
    protected static ResultEntity getResultEntity() throws CocamException {
        if (resultEntity == null) {
            // carga el conjunto de resultados
            try {
                resultEntity = ResultEntity.unmarshall(
                    new File(FileNameManager.getResultEntityFilename()));
            } catch (JAXBException jaxbex) {
                logger.fatal(jaxbex);
                throw new CocamException(jaxbex);
            }
            logger.info("resultEntity LOADED"
                + "->TOTAL results"
                + StringUtil.enclose(resultEntity.getResult().size())
            );
        }
        return resultEntity;
    }
}
