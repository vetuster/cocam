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
    "leagueType",
    "roundId",
    "dayId",
    "dayDate",
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
    
    @XmlAttribute(required = true)
    private LeagueType leagueType;
    @XmlAttribute(required = true)    
    private Integer roundId;
    @XmlAttribute(required = true)    
    private Integer dayId;
    @XmlAttribute(required = true)    
    @XmlJavaTypeAdapter(CocamAdapter.class)
    private LocalDate dayDate;
    @XmlAttribute(required = true)    
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

    public LeagueType getLeagueType() {
        return leagueType;
    }

    public void setLeagueType(LeagueType leagueType) {
        this.leagueType = leagueType;
    }

    public Integer getRoundId() {
        return roundId;
    }

    public void setRoundId(Integer roundId) {
        this.roundId = roundId;
    }

    public Integer getDayId() {
        return dayId;
    }

    public void setDayId(Integer dayId) {
        this.dayId = dayId;
    }
    
    public LocalDate getDayDate() {
        return dayDate;
    }

    public void setDayDate(LocalDate dayDate) {
        this.dayDate = dayDate;
    }
    
    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }
    
    public void setMatchId(String localTeamId, String visitingTeamId) {
        StringBuilder sb = new StringBuilder(localTeamId);
        sb.append(visitingTeamId);
        setMatchId(sb.toString());
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
    
    
    public MatchScoreType getResultType() {
        if (localTeamScore == null || visitingTeamScore == null) {
            return null;
        }
        // devuelve el tipo de resultado correspondiente
        return MatchScoreType.valueOf(localTeamScore * 10 + visitingTeamScore);
    }
    
    
    @Override
    public int compareTo(Match match) {
        int i = leagueType.compareTo(match.getLeagueType());
        if (i!=0) return i;
        i = dayId.compareTo(match.getDayId());
        if (i!=0) return i;
        i = dayDate.compareTo(match.getDayDate());
        if (i!=0) return i;
        return matchId.compareTo(match.getMatchId());
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Match->");
        sb.append("leagueType");
        sb.append(StringUtil.enclose(leagueType.toString()));
        sb.append("roundId");
        sb.append(StringUtil.enclose(roundId));
        sb.append("dayId");
        sb.append(StringUtil.enclose(dayId));
        sb.append(",roundDate");
        sb.append(StringUtil.enclose(CocamDatatypeConverter.printLocalDate(dayDate)));
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
    
    public static Match build(Result result) {
        Match match = new Match();
        match.setLeagueType(LeagueType.valueOf(result.getLeagueType()));
        match.setRoundId(result.getRoundId());
        match.setDayId(result.getSeasonDayId());
        match.setDayDate(result.getSeasonDayDate());
        match.setMatchId(result.getLocalTeamId(), result.getVisitingTeamId());
        match.setLocalTeamId(result.getLocalTeamId());
        match.setLocalTeamName(result.getLocalTeamName());
        match.setLocalTeamScore(result.getLocalTeamScore());
        match.setVisitingTeamId(result.getVisitingTeamId());
        match.setVisitingTeamName(result.getVisitingTeamName());
        match.setVisitingTeamScore(result.getVisitingTeamScore());
        return match;
    }
}
