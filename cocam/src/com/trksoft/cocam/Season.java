/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import com.trksoft.util.StringUtil;
import java.io.File;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author jasuarez
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "seasonId",
    "match"
})
@XmlRootElement
public class Season {
    
    @XmlAttribute
    private String seasonId;
    
    @XmlElement(required = true)
    private final SortedSet<Match> match;
    
    
    public String getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(String seasonId) {
        this.seasonId = seasonId;
    }

    public Season() {
        match = new TreeSet<>();
    }

    public SortedSet<Match> getMatch() {
        return match;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Season->");
        sb.append("seasonId");
        sb.append(StringUtil.enclose(seasonId));
        sb.append(match.stream().map(Object::toString).
            collect(Collectors.joining("->")));
        return sb.toString();
    }

   public static Season unmarshall(File seasonFile) throws CocamException {
        Season season = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Season.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            season = (Season) jaxbUnmarshaller.unmarshal(seasonFile);
        } catch (JAXBException jaxbex) {
            throw new CocamException(jaxbex);
        }
        return season;
    }
}