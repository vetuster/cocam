/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import java.io.File;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
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
    "team"
})
@XmlRootElement
public class TeamEntity {
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(TeamEntity.class);
    
    @XmlElement(required = true)
    private final Set<Team> team;

    public TeamEntity() {
       team = new TreeSet<>();
    }

    public Set<Team> getTeam() {
        return team;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TeamEntity->");
        sb.append(team.stream().map(Object::toString).
            collect(Collectors.joining("->")));
        return sb.toString();
    }
    
    public static TeamEntity unmarshall(File teamEntityFile) 
        throws JAXBException {
        TeamEntity teamEntity = null;
        try {
            JAXBContext jaxbContext = 
                JAXBContext.newInstance(TeamEntity.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            teamEntity = (TeamEntity)
                jaxbUnmarshaller.unmarshal(teamEntityFile);
        } catch (JAXBException jaxbex) {
            logger.fatal(jaxbex);
            throw jaxbex;
        }
        return teamEntity;
    }
}
