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
    "teamStat"
})
@XmlRootElement
public class TeamStatGroup {
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(TeamStatGroup.class);
    
    @XmlElement(required = true)
    private final Map<String, TeamStat> teamStat;

    public TeamStatGroup() {
        teamStat = new HashMap<>();
    }

    public Map<String, TeamStat> getTeamStat() {
        return teamStat;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TeamStatGroup->");
        sb.append(teamStat.values().stream().map(Object::toString).
            collect(Collectors.joining("->")));
        return sb.toString();
    }
    
    public void marshall(File teamStatGroupFile) throws JAXBException {
        try {
            JAXBContext jaxbContext
                = JAXBContext.newInstance(TeamStatGroup.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(this, teamStatGroupFile);
        } catch (JAXBException jaxbex) {
            logger.fatal(jaxbex);
            throw jaxbex;
        }
    }
    
    public static TeamStatGroup unmarshall(
        File fixedLengthColRecordDescFile) throws CocamException {
        TeamStatGroup flcrd = null;
        try {
            JAXBContext jaxbContext = 
                JAXBContext.newInstance(TeamStatGroup.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            flcrd = (TeamStatGroup)
                jaxbUnmarshaller.unmarshal(fixedLengthColRecordDescFile);
        } catch (JAXBException jaxbex) {
            throw new CocamException(jaxbex);
        }
        return flcrd;
    }

    public void update(final Match match) {
        //obtener las estadisticas en curso del equipo local
        TeamStat localTeamStat = getTeamStat().get(match.getLocalTeamId());
        //obtener las estadisticas en curso del equipo visitante
        TeamStat visitingTeamStat = 
            getTeamStat().get(match.getVisitingTeamId());

        //actualizar estadisticas de local y visitante
        localTeamStat.update(match);
        visitingTeamStat.update(match);
    }
}
