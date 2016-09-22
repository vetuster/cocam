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
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
    "seasonId",
    "lastDayId",
    "match"
})
@XmlRootElement
public class Season {
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(Season.class);
    
    @XmlAttribute
    private String seasonId;
    
    @XmlAttribute
    private Integer lastDayId;
    
    @XmlElement(required = true)
    private final SortedSet<Match> match;
    

    public Season() {
        match = new TreeSet<>();
    }
    
    public String getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(String seasonId) {
        this.seasonId = seasonId;
    }

    public Integer getLastDayId() {
        return lastDayId;
    }

    public void setLastDayId(Integer lastDayId) {
        this.lastDayId = lastDayId;
    }

    public SortedSet<Match> getMatch() {
        return match;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Season->");
        sb.append("seasonId");
        sb.append(StringUtil.enclose(seasonId));
        sb.append("lastDayId");
        sb.append(StringUtil.enclose(lastDayId));
        sb.append(match.stream().map(Object::toString).
            collect(Collectors.joining("->")));
        return sb.toString();
    }

    public void marshall(File seasonFile) throws JAXBException {
        try {
            JAXBContext jaxbContext
                = JAXBContext.newInstance(Season.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(this, seasonFile);
        } catch (JAXBException jaxbex) {
            logger.fatal(jaxbex);
            throw jaxbex;
        }
    }
    
   public static Season unmarshall(File seasonFile) throws JAXBException {
        Season season = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Season.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            season = (Season) jaxbUnmarshaller.unmarshal(seasonFile);
        } catch (JAXBException jaxbex) {
            logger.fatal(jaxbex);
            throw jaxbex;
        }
        return season;
    }
}
