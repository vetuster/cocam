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
        return team.stream().map(Object::toString).
            collect(Collectors.joining("->"));
    }
    
    public static TeamGroup unmarshall(
        File fixedLengthColRecordDescFile) throws CocamException {
        TeamGroup flcrd = null;
        try {
            JAXBContext jaxbContext = 
                JAXBContext.newInstance(TeamGroup.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            flcrd = (TeamGroup)
                jaxbUnmarshaller.unmarshal(fixedLengthColRecordDescFile);
        } catch (JAXBException jaxbex) {
            throw new CocamException(jaxbex);
        }
        return flcrd;
    }
}
