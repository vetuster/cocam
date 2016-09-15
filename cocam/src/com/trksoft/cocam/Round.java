/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import com.trksoft.util.StringUtil;
import java.io.File;
import java.time.LocalDate;
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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author jasuarez
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "match"
})
@XmlRootElement
public class Round {
     
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(Round.class);
    
    @XmlAttribute
    private Integer roundId;
    
    @XmlAttribute
    @XmlJavaTypeAdapter(CocamAdapter.class)
    private LocalDate roundDate;
    
    @XmlElement(required = true)
    private final SortedSet<Match> match;
    
    
    public Round() {
        match = new TreeSet<>();
    }

    public Integer getRoundId() {
        return roundId;
    }

    public void setRoundId(Integer roundId) {
        this.roundId = roundId;
    }
    
    public LocalDate getRoundDate() {
        return roundDate;
    }

    public void setRoundDate(LocalDate roundDate) {
        this.roundDate = roundDate;
    }

    public SortedSet<Match> getMatch() {
        return match;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("roundId");
        sb.append(StringUtil.enclose(roundId));
        sb.append("roundDate");
        sb.append(StringUtil.enclose(
            CocamDatatypeConverter.printLocalDate(roundDate)));
        sb.append(match.stream().map(Object::toString).
            collect(Collectors.joining("->")));
        return sb.toString();
    }

    
    public static Round unmarshall(File roundFile) throws CocamException {
        Round round = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Round.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            round = (Round) jaxbUnmarshaller.unmarshal(roundFile);
        } catch (JAXBException jaxbex) {
            logger.fatal(jaxbex);
            throw new CocamException(jaxbex);
        }
        return round;
    }
}
