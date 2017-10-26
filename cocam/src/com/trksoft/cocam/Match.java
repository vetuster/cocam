/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import com.trksoft.util.StringUtil;
import java.io.File;
import java.time.LocalDate;
import java.util.Set;
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
    "seasonId",
    "leagueType",
    "roundId",
    "dayId",
    "dayDate",
    "localTeamId",
    "localTeamName",
    "localTeamScore",
    "localTeamPenaltyPoint",
    "visitingTeamId",
    "visitingTeamName",
    "visitingTeamScore",
    "visitingTeamPenaltyPoint",
    "table"
})
@XmlRootElement
public class Match implements Comparable<Match> {
    
    private static final int TABLES_NUM = 4;
    
    @XmlAttribute(required = true)
    private String seasonId;
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
    private String localTeamId;
    @XmlElement(required = true)
    private String localTeamName;
    @XmlElement(required = true)
    private Integer localTeamScore;
    @XmlElement(required = true)
    private Integer localTeamPenaltyPoint;
    @XmlAttribute(required = true)
    private String visitingTeamId;
    @XmlElement(required = true)
    private String visitingTeamName;
    @XmlElement(required = true)
    private Integer visitingTeamScore;
    @XmlElement(required = true)
    private Integer visitingTeamPenaltyPoint;
    
    @XmlElement(required = true)
    private final SortedSet<Table> table;
    
    
    public Match() {
        table = new TreeSet<>();
    }

    public String getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(String seasonId) {
        this.seasonId = seasonId;
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

    public Integer getLocalTeamPenaltyPoint() {
        return localTeamPenaltyPoint;
    }

    public void setLocalTeamPenaltyPoint(Integer localTeamPenaltyPoint) {
        this.localTeamPenaltyPoint = localTeamPenaltyPoint;
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

    public Integer getVisitingTeamPenaltyPoint() {
        return visitingTeamPenaltyPoint;
    }

    public void setVisitingTeamPenaltyPoint(Integer visitingTeamPenaltyPoint) {
        this.visitingTeamPenaltyPoint = visitingTeamPenaltyPoint;
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
    
    
    public Set<String> getLocalPlayerSet() {
        Set<String> localPlayerSet = new TreeSet<>();
        for (Table table : getTable()) {
            localPlayerSet.add(table.getLocalPlayerNickOne());
            localPlayerSet.add(table.getLocalPlayerNickTwo());
        }
        return localPlayerSet;
    }
    
    public Set<String> getVisitingPlayerSet() {
        Set<String> visitingPlayerSet = new TreeSet<>();
        for (Table table : getTable()) {
            visitingPlayerSet.add(table.getVisitingPlayerNickOne());
            visitingPlayerSet.add(table.getVisitingPlayerNickTwo());
        }
        return visitingPlayerSet;
    }
    
    
    public void addTable(Table newTable) throws CocamException {
        // verificaciones previas a incorporar una nueva mesa
        
        // ya consta de las mesas por encuentro reglamentadas
        if (getTable().size() == TABLES_NUM) {
            StringBuilder sb = 
                new StringBuilder("Match max num tables EXCEEDED ");
                sb.append(this.toString());
            throw new CocamException(toString());
        }
        
        // resultado erroneo, alguna de las partes no llego a 6 chicos
        // o se excede el máximo posible de chicos
        int totalChicos = newTable.getLocalPairScore() +
            newTable.getVisitingPairScore();
        if (totalChicos <  Table.MIN_CHICOS || totalChicos > Table.MAX_CHICOS) {
            StringBuilder sb = new StringBuilder("Table Score ERROR ");
            sb.append(StringUtil.enclose(newTable.toString()));
            throw new CocamException(sb.toString());
        }
        
        // Verificar NO duplicidad de jugadores locales
        for (String localPlayerNick : getLocalPlayerSet()) {
            if (localPlayerNick.equals(newTable.getLocalPlayerNickOne())
                || localPlayerNick.equals(newTable.getLocalPlayerNickTwo())) {
                StringBuilder sb =
                    new StringBuilder("Local Player DUPLICATED ");
                sb.append(StringUtil.enclose(localPlayerNick));
                sb.append(this.toString());
                throw new CocamException(sb.toString());
            }
        }
        
        // Verificar NO duplicidad de jugadores visitantes
        for (String visitingPlayerNick : getVisitingPlayerSet()) {
            if (visitingPlayerNick.equals(newTable.getVisitingPlayerNickOne())
                || visitingPlayerNick.equals(
                    newTable.getVisitingPlayerNickTwo())) {
                StringBuilder sb =
                    new StringBuilder("Visiting Player DUPLICATED ");
                sb.append(StringUtil.enclose(visitingPlayerNick));
                sb.append(this.toString());
                throw new CocamException(sb.toString());
            }
        }
        
        // añadir la nueva Table (partida) al Match (encuentro)
        getTable().add(newTable);
   }
    
    
    @Override
    public int compareTo(Match match) {
        int i = seasonId.compareTo(match.getSeasonId());
        if (i!=0) return i;
        i = leagueType.compareTo(match.getLeagueType());
        if (i!=0) return i;
        i = roundId.compareTo(match.getRoundId());
        if (i!=0) return i;
        i = dayId.compareTo(match.getDayId());
        if (i!=0) return i;
        i = dayDate.compareTo(match.getDayDate());
        if (i!=0) return i;
        i = localTeamId.compareTo(match.getLocalTeamId());
        if (i!=0) return i;
        return visitingTeamId.compareTo(match.getVisitingTeamId());
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Match->");
        sb.append("seasonId");
        sb.append(StringUtil.enclose(seasonId));
        sb.append(",leagueType");
        sb.append(StringUtil.enclose(leagueType.toString()));
        sb.append(",roundId");
        sb.append(StringUtil.enclose(roundId));
        sb.append(",dayId");
        sb.append(StringUtil.enclose(dayId));
        sb.append(",roundDate");
        sb.append(StringUtil.enclose(
            CocamDatatypeConverter.printLocalDate(dayDate)));
        sb.append(",localTeamId");
        sb.append(StringUtil.enclose(localTeamId));
        sb.append(",localTeamName");
        sb.append(StringUtil.enclose(localTeamName));
        sb.append(",localTeamScore");
        sb.append(StringUtil.enclose(localTeamScore));
        sb.append(",localTeamPenaltyPoint");
        sb.append(StringUtil.enclose(localTeamPenaltyPoint));
        sb.append(",visitingTeamId");
        sb.append(StringUtil.enclose(visitingTeamId));
        sb.append(",visitingTeamName");
        sb.append(StringUtil.enclose(visitingTeamName));
        sb.append(",visitingTeamScore");
        sb.append(StringUtil.enclose(visitingTeamScore));
        sb.append(",visitingTeamPenaltyPoint");
        sb.append(StringUtil.enclose(visitingTeamPenaltyPoint));
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
