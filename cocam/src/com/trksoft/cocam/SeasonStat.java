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
    "teamStatGroup",
    "playerStatGroup"
})
@XmlRootElement
public class SeasonStat {
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(SeasonStat.class);
    
    @XmlElement(required = true)
    private final Map<LeagueType, TeamStatGroup> teamStatGroup;
    
    @XmlElement(required = true)
    private final Map<LeagueType, PlayerStatGroup> playerStatGroup;

    public SeasonStat() {
        teamStatGroup = new HashMap<>();
        playerStatGroup = new HashMap<>();
    }

    public Map<LeagueType, TeamStatGroup> getTeamStatGroup() {
        return teamStatGroup;
    }

    public Map<LeagueType, PlayerStatGroup> getPlayerStatGroup() {
        return playerStatGroup;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SeasonStat->");
        sb.append(teamStatGroup.values().stream().map(Object::toString).
            collect(Collectors.joining("->")));
        sb.append(playerStatGroup.values().stream().map(Object::toString).
            collect(Collectors.joining("->")));
        return sb.toString();
    }
    
    public void marshall(File teamStatGroupFile) throws JAXBException {
        try {
            JAXBContext jaxbContext
                = JAXBContext.newInstance(SeasonStat.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(this, teamStatGroupFile);
        } catch (JAXBException jaxbex) {
            logger.fatal(jaxbex);
            throw jaxbex;
        }
    }
    
    public static SeasonStat unmarshall(
        File fixedLengthColRecordDescFile) throws CocamException {
        SeasonStat flcrd = null;
        try {
            JAXBContext jaxbContext = 
                JAXBContext.newInstance(SeasonStat.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            flcrd = (SeasonStat)
                jaxbUnmarshaller.unmarshal(fixedLengthColRecordDescFile);
        } catch (JAXBException jaxbex) {
            throw new CocamException(jaxbex);
        }
        return flcrd;
    }

}
