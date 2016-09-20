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

/**
 *
 * @author jasuarez
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "roundId",
    "roundDate",
    "match"
})
@XmlRootElement
public class Round implements Comparable<Round> {
    
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
    public int compareTo(Round round) {
        return roundId.compareTo(round.getRoundId());
    }

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Round->");
        sb.append("roundId");
        sb.append(StringUtil.enclose(roundId));
        sb.append(",roundDate");
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
            throw new CocamException(jaxbex);
        }
        return round;
    }
}