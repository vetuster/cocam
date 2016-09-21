/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import java.io.File;
import java.util.SortedSet;
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
public class TeamGroup {
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(TeamGroup.class);
    
    @XmlElement(required = true)
    private final SortedSet<Team> team;

    public TeamGroup() {
        team = new TreeSet<>();
    }

    public SortedSet<Team> getTeam() {
        return team;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TeamGroup->");
        sb.append(team.stream().map(Object::toString).
            collect(Collectors.joining("->")));
        return sb.toString();
    }
    
    public static TeamGroup unmarshall(
        File fixedLengthColRecordDescFile) throws JAXBException {
        TeamGroup flcrd = null;
        try {
            JAXBContext jaxbContext = 
                JAXBContext.newInstance(TeamGroup.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            flcrd = (TeamGroup)
                jaxbUnmarshaller.unmarshal(fixedLengthColRecordDescFile);
        } catch (JAXBException jaxbex) {
            logger.fatal(jaxbex);
            throw jaxbex;
        }
        return flcrd;
    }
}
