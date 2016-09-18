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
    "matchId",
    "localTeamId",
    "localTeamName",
    "localTeamScore",
    "visitingTeamId",
    "visitingTeamName",
    "visitingTeamScore",
    "table"
})
@XmlRootElement
public class Match  implements Comparable<Match> {

    @XmlAttribute
    private String matchId;
    
    @XmlElement(required = true)
    private String localTeamId;
        
    @XmlElement(required = true)
    private String localTeamName;
        
    @XmlElement(required = true)
    private Integer localTeamScore;
    
    @XmlElement(required = true)
    private String visitingTeamId;
        
    @XmlElement(required = true)
    private String visitingTeamName;
        
    @XmlElement(required = true)
    private Integer visitingTeamScore;
    
    @XmlElement(required = true)
    private final SortedSet<Table> table;
    
    
    public Match() {
        table = new TreeSet<>();
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getLocalTeamId() {
        return localTeamId;
    }

    public void setLocalTeamId(String localTeamId) {
        this.localTeamId = localTeamId;
    }

    public String getLocalTeamName() {
        return localTeamName;
    }

    public void setLocalTeamName(String localTeamName) {
        this.localTeamName = localTeamName;
    }

    public Integer getLocalTeamScore() {
        return localTeamScore;
    }

    public void setLocalTeamScore(Integer localTeamScore) {
        this.localTeamScore = localTeamScore;
    }

    public String getVisitingTeamId() {
        return visitingTeamId;
    }

    public void setVisitingTeamId(String visitingTeamId) {
        this.visitingTeamId = visitingTeamId;
    }

    public String getVisitingTeamName() {
        return visitingTeamName;
    }

    public void setVisitingTeamName(String visitingTeamName) {
        this.visitingTeamName = visitingTeamName;
    }

    public Integer getVisitingTeamScore() {
        return visitingTeamScore;
    }

    public void setVisitingTeamScore(Integer visitingTeamScore) {
        this.visitingTeamScore = visitingTeamScore;
    }
    
    public SortedSet<Table> getTable() {
        return table;
    }
    
    @Override
    public int compareTo(Match match) {
        return matchId.compareTo(match.getMatchId());
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Match->");
        sb.append("matchId");
        sb.append(StringUtil.enclose(matchId));
        sb.append(",localTeamId");
        sb.append(StringUtil.enclose(localTeamId));
        sb.append(",localTeamName");
        sb.append(StringUtil.enclose(localTeamName));
        sb.append(",localTeamScore");
        sb.append(StringUtil.enclose(localTeamScore));
        sb.append(",visitingTeamId");
        sb.append(StringUtil.enclose(visitingTeamId));
        sb.append(",visitingTeamName");
        sb.append(StringUtil.enclose(visitingTeamName));
        sb.append(",visitingTeamScore");
        sb.append(StringUtil.enclose(visitingTeamScore));
        sb.append(table.stream().map(Object::toString).
            collect(Collectors.joining("->")));
        return sb.toString();
    }

    
    public static Match unmarshall(File matchFile) throws CocamException {
        Match match = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Match.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            match = (Match) jaxbUnmarshaller.unmarshal(matchFile);
        } catch (JAXBException jaxbex) {
            throw new CocamException(jaxbex);
        }
        return match;
    }
}
