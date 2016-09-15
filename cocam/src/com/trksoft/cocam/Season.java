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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author jasuarez
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "round"
})
@XmlRootElement
public class Season {
     
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(Season.class);
    
    @XmlAttribute
    private Integer seasonId;
    
    @XmlElement(required = true)
    private final SortedSet<Round> round;
    
    
    public Integer getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Integer seasonId) {
        this.seasonId = seasonId;
    }

    public Season() {
        round = new TreeSet<>();
    }

    public SortedSet<Round> getRound() {
        return round;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("seasonId");
        sb.append(StringUtil.enclose(seasonId));
        sb.append(round.stream().map(Object::toString).
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
            logger.fatal(jaxbex);
            throw new CocamException(jaxbex);
        }
        return season;
    }
}
