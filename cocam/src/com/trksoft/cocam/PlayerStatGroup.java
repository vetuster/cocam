/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
    "playerStat"
})
@XmlRootElement
public class PlayerStatGroup {
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(PlayerStatGroup.class);
    
    @XmlElement(required = true)
    private final Map<String, PlayerStat> playerStat;

    public PlayerStatGroup() {
        playerStat = new HashMap<>();
    }

    public Map<String, PlayerStat> getPlayerStat() {
        return playerStat;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PlayerStatGroup->");
        sb.append(playerStat.values().stream().map(Object::toString).
            collect(Collectors.joining("->")));
        return sb.toString();
    }
    
    public void marshall(File teamStatGroupFile) throws JAXBException {
        try {
            JAXBContext jaxbContext
                = JAXBContext.newInstance(PlayerStatGroup.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(this, teamStatGroupFile);
        } catch (JAXBException jaxbex) {
            logger.fatal(jaxbex);
            throw jaxbex;
        }
    }
    
    public static PlayerStatGroup unmarshall(
        File fixedLengthColRecordDescFile) throws CocamException {
        PlayerStatGroup flcrd = null;
        try {
            JAXBContext jaxbContext = 
                JAXBContext.newInstance(PlayerStatGroup.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            flcrd = (PlayerStatGroup)
                jaxbUnmarshaller.unmarshal(fixedLengthColRecordDescFile);
        } catch (JAXBException jaxbex) {
            throw new CocamException(jaxbex);
        }
        return flcrd;
    }

    public void update(final Match match) {
        // obtener y actualizar las estadisticas de los jugadores de cada mesa
        for (Table table : match.getTable()) {
            PlayerStat localPlayerOneStat = getPlayerStat().
                get(Player.getPlayerKey(match.getLocalTeamId(), 
                    table.getLocalPlayerNickOne()));
            localPlayerOneStat.update(table, true);
            
             PlayerStat localPlayerTwoStat = getPlayerStat().
                get(Player.getPlayerKey(match.getLocalTeamId(), 
                    table.getLocalPlayerNickTwo()));
             localPlayerTwoStat.update(table, true);
             
            PlayerStat visitingPlayerOneStat = getPlayerStat().
                get(Player.getPlayerKey(match.getVisitingTeamId(),
                    table.getVisitingPlayerNickOne()));
            visitingPlayerOneStat.update(table, false);
            
             PlayerStat visitingPlayerTwoStat = getPlayerStat().
                get(Player.getPlayerKey(match.getVisitingTeamId(), 
                    table.getVisitingPlayerNickTwo()));
             visitingPlayerTwoStat.update(table, false);
        }
    }
}
