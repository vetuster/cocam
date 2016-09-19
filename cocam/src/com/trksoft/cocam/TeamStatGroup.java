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
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

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
        return teamStat.values().stream().map(Object::toString).
            collect(Collectors.joining("->"));
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
}
