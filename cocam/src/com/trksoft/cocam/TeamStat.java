/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import com.trksoft.util.StringUtil;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
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
 * @author triki
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "teamId",
    "teamDenom",
    "leagueType",
    "points",
    "matchPlayed",
    "matchWon",
    "matchLost",
    "matchPlayedLocal",
    "matchWonLocal",
    "matchLostLocal",
    "matchPlayedVisiting",
    "matchWonVisiting",
    "matchLostVisiting",
    "tableResult40",
    "tableResult31",
    "tableResult22",
    "tableResult13",
    "tableResult04",
    "goalsFavor",
    "goalsAgainst",
    "directMatch"
})
@XmlRootElement
public class TeamStat {
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(TeamStat.class);
    
    @XmlAttribute(required = true)    
    private String teamId;
    @XmlAttribute(required = true)    
    private String teamDenom;
    @XmlAttribute(required = true)    
    private LeagueType leagueType;
    @XmlElement(required = true)
    private Integer points;
    @XmlElement(required = true)
    private Integer matchPlayed;
    @XmlElement(required = true)
    private Integer matchWon;
    @XmlElement(required = true)
    private Integer matchLost;
    @XmlElement(required = true)
    private Integer matchPlayedLocal;
    @XmlElement(required = true)
    private Integer matchWonLocal;
    @XmlElement(required = true)
    private Integer matchLostLocal;
    @XmlElement(required = true)
    private Integer matchPlayedVisiting;
    @XmlElement(required = true)
    private Integer matchWonVisiting;
    @XmlElement(required = true)
    private Integer matchLostVisiting;
    @XmlElement(required = true)
    private Integer tableResult40;
    @XmlElement(required = true)
    private Integer tableResult31;
    @XmlElement(required = true)
    private Integer tableResult22;
    @XmlElement(required = true)
    private Integer tableResult13;
    @XmlElement(required = true)
    private Integer tableResult04;
    @XmlElement(required = true)
    private Integer goalsFavor;
    @XmlElement(required = true)
    private Integer goalsAgainst;
    
    @XmlElement(required = true)
    private final Map<String, DirectMatch> directMatch;
    
    public TeamStat() {
        this.points = 0;
        this.matchPlayed = 0;
        this.matchWon = 0;
        this.matchLost = 0;
        this.matchPlayedLocal = 0;
        this.matchWonLocal = 0;
        this.matchLostLocal = 0;
        this.matchPlayedVisiting = 0;
        this.matchWonVisiting = 0;
        this.matchLostVisiting = 0;
        this.tableResult40 = 0;
        this.tableResult31 = 0;
        this.tableResult22 = 0;
        this.tableResult13 = 0;
        this.tableResult04 = 0;
        this.goalsFavor = 0;
        this.goalsAgainst = 0;
        this.directMatch = new HashMap<>();
    }
    
    
    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamDenom() {
        return teamDenom;
    }

    public void setTeamDenom(String teamDenom) {
        this.teamDenom = teamDenom;
    }

    public LeagueType getLeagueType() {
        return leagueType;
    }

    public void setLeagueType(LeagueType leagueType) {
        this.leagueType = leagueType;
    }

    public Integer getMatchPlayed() {
        return matchPlayed;
    }

    public void setMatchPlayed(Integer matchPlayed) {
        this.matchPlayed = matchPlayed;
    }
    public void incMatchPlayed() {
        ++this.matchPlayed;
    }
    
    public Integer getMatchWon() {
        return matchWon;
    }

    public void setMatchWon(Integer matchWon) {
        this.matchWon = matchWon;
    }
    public void incMatchWon() {
        ++this.matchWon;
    }

    public Integer getMatchLost() {
        return matchLost;
    }

    public void setMatchLost(Integer matchLost) {
        this.matchLost = matchLost;
    }
    public void incMatchLost() {
        ++this.matchLost;
    }

    public Integer getMatchPlayedLocal() {
        return matchPlayedLocal;
    }

    public void setMatchPlayedLocal(Integer matchPlayedLocal) {
        this.matchPlayedLocal = matchPlayedLocal;
    }
    public void incMatchPlayedLocal() {
        ++this.matchPlayedLocal;
    }

    public Integer getMatchWonLocal() {
        return matchWonLocal;
    }

    public void setMatchWonLocal(Integer matchWonLocal) {
        this.matchWonLocal = matchWonLocal;
    }
    public void incMatchWonLocal() {
        ++this.matchWonLocal;
    }

    public Integer getMatchLostLocal() {
        return matchLostLocal;
    }

    public void setMatchLostLocal(Integer matchLostLocal) {
        this.matchLostLocal = matchLostLocal;
    }
    public void incMatchLostLocal() {
        ++this.matchLostLocal;
    }

    public Integer getMatchPlayedVisiting() {
        return matchPlayedVisiting;
    }

    public void setMatchPlayedVisiting(Integer matchPlayedVisiting) {
        this.matchPlayedVisiting = matchPlayedVisiting;
    }
    public void incMatchPlayedVisiting() {
        ++this.matchPlayedVisiting;
    }

    public Integer getMatchWonVisiting() {
        return matchWonVisiting;
    }

    public void setMatchWonVisiting(Integer matchWonVisiting) {
        this.matchWonVisiting = matchWonVisiting;
    }
    public void incMatchWonVisiting() {
        ++this.matchWonVisiting;
    }

    public Integer getMatchLostVisiting() {
        return matchLostVisiting;
    }

    public void setMatchLostVisiting(Integer matchLostVisiting) {
        this.matchLostVisiting = matchLostVisiting;
    }
    public void incMatchLostVisiting() {
        ++this.matchLostVisiting;
    }

    public Integer getTableResult40() {
        return tableResult40;
    }

    public void setTableResult40(Integer tableResult40) {
        this.tableResult40 = tableResult40;
    }
    public void incTableResult40() {
        ++this.tableResult40;
    }

    public Integer getTableResult31() {
        return tableResult31;
    }

    public void setTableResult31(Integer tableResult31) {
        this.tableResult31 = tableResult31;
    }
    public void incTableResult31() {
        ++this.tableResult31;
    }

    public Integer getTableResult22() {
        return tableResult22;
    }

    public void setTableResult22(Integer tableResult22) {
        this.tableResult22 = tableResult22;
    }
    public void incTableResult22() {
        ++this.tableResult22;
    }

    public Integer getTableResult13() {
        return tableResult13;
    }

    public void setTableResult13(Integer tableResult13) {
        this.tableResult13 = tableResult13;
    }
    public void incTableResult13() {
        ++this.tableResult13;
    }

    public Integer getTableResult04() {
        return tableResult04;
    }

    public void setTableResult04(Integer tableResult04) {
        this.tableResult04 = tableResult04;
    }
    public void incTableResult04() {
        ++this.tableResult04;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getGoalsFavor() {
        return goalsFavor;
    }

    public void setGoalsFavor(Integer goalsFavor) {
        this.goalsFavor = goalsFavor;
    }

    public Integer getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(Integer goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public Map<String, DirectMatch> getDirectMatch() {
        return directMatch;
    }
    
    public String getTeamStatKey() {
        StringBuilder teamStatKey = 
            new StringBuilder(getLeagueType().toString());
        teamStatKey.append(getTeamId());
        return teamStatKey.toString();
    }
    
    public static String getTeamStatKey(LeagueType leagueType, String teamId) {
        StringBuilder teamStatKey = new StringBuilder(leagueType.toString());
        teamStatKey.append(teamId);
        return teamStatKey.toString();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TeamStat->");
        sb.append("teamId");
        sb.append(StringUtil.enclose(teamId));
        sb.append(",teamDenom");
        sb.append(StringUtil.enclose(teamDenom));
        sb.append(",leagueType");
        sb.append(StringUtil.enclose(leagueType.toString()));
        sb.append(",points");
        sb.append(StringUtil.enclose(points));
        sb.append(",matchPlayed");
        sb.append(StringUtil.enclose(matchPlayed));
        sb.append(",matchWon");
        sb.append(StringUtil.enclose(matchWon));
        sb.append(",matchLost");
        sb.append(StringUtil.enclose(matchLost));
        sb.append(",matchPlayedLocal");
        sb.append(StringUtil.enclose(matchPlayedLocal));
        sb.append(",matchWonLocal");
        sb.append(StringUtil.enclose(matchWonLocal));
        sb.append(",matchLostLocal");
        sb.append(StringUtil.enclose(matchLostLocal));
        sb.append(",matchPlayedVisiting");
        sb.append(StringUtil.enclose(matchPlayedVisiting));
        sb.append(",matchWonVisiting");
        sb.append(StringUtil.enclose(matchWonVisiting));
        sb.append(",matchLostVisiting");
        sb.append(StringUtil.enclose(matchLostVisiting));
        sb.append(",tableResult40");
        sb.append(StringUtil.enclose(tableResult40));
        sb.append(",tableResult31");
        sb.append(StringUtil.enclose(tableResult31));
        sb.append(",tableResult22");
        sb.append(StringUtil.enclose(tableResult22));
        sb.append(",tableResult13");
        sb.append(StringUtil.enclose(tableResult13));
        sb.append("tableResult04");
        sb.append(StringUtil.enclose(tableResult04));
        sb.append(",goalsFavor");
        sb.append(StringUtil.enclose(goalsFavor));
        sb.append(",goalsAgainst");
        sb.append(StringUtil.enclose(goalsAgainst));
        sb.append(directMatch.values().stream().map(Object::toString).
            collect(Collectors.joining("->")));
        return sb.toString();
    }
    
    public void update(Match match) {
        // this es local
        Boolean local;
        if (getTeamId().equals(match.getLocalTeamId())) {
            local = true;
        // es visitante
        } else if (getTeamId().equals(match.getVisitingTeamId())) {
            local = false;
        // el partido argumento no implica a this
        } else {
            logger.warn("MATCH ARGUMENT DONT ATTACH THIS");
            return;
        }
        
        incMatchPlayed();
        if (local) {
            incMatchPlayedLocal();
            setPoints(getPoints() + match.getLocalTeamScore());
        } else {
            incMatchPlayedVisiting();
            setPoints(getPoints() + match.getVisitingTeamScore());
        }
        
        switch (match.getResultType()) {
            case R04: {
                if (local) {
                    incMatchLost();
                    incMatchLostLocal();
                    incTableResult04();
                } else {
                    incMatchWon();
                    incMatchWonVisiting();
                    incTableResult40();
                }
                break;
            }
            case R13: {
                if (local) {
                    incMatchLost();
                    incMatchLostLocal();
                    incTableResult13();
                } else {
                    incMatchWon();
                    incMatchWonVisiting();
                    incTableResult31();
                }
                break;
            }
            case R22: {
                incTableResult22();
                break;
            }
            case R31: {
                if (local) {
                    incMatchWon();
                    incMatchWonLocal();
                    incTableResult31();
                } else {
                    incMatchLost();
                    incMatchLostVisiting();
                    incTableResult13();
                }
                break;
            }
            case R40: {
                if (local) {
                    incMatchWon();
                    incMatchWonLocal();
                    incTableResult40();
                } else {
                    incMatchLost();
                    incMatchLostVisiting();
                    incTableResult04();
                }
                break;
            }
            default: {
                String errText = "UNKNOWN RESULT TYPE"
                    + StringUtil.enclose(match.getResultType().toString());
                logger.fatal(errText);
                throw new RuntimeException(errText);
            }
        } // switch
        
        match.getTable().stream().forEach((table) -> {
            if (local) {
                setGoalsFavor(getGoalsFavor() + table.getLocalPairScore());
                setGoalsAgainst(getGoalsAgainst()
                    + table.getVisitingPairScore());
            } else {
                setGoalsFavor(getGoalsFavor() + table.getVisitingPairScore());
                setGoalsAgainst(getGoalsAgainst() + table.getLocalPairScore());
            }
        });
        
        // enfrentamientos directos
        DirectMatch currentDirectMatch = new DirectMatch();
        if (local) {
            currentDirectMatch.setAdversaryTeamId(match.getVisitingTeamId());
            currentDirectMatch.setPlayedAsLocal(true);
            currentDirectMatch.setPointsAsLocal(match.getLocalTeamScore());
        } else {
            currentDirectMatch.setAdversaryTeamId(match.getLocalTeamId());
            currentDirectMatch.setPlayedAsVisiting(true);
            currentDirectMatch.setPointsAsVisiting(
                match.getVisitingTeamScore());
        }
        if (getDirectMatch().containsKey(currentDirectMatch.getAdversaryTeamId())) {
            String errText = "DUPLICATE DIRECT MACTH"
                + StringUtil.enclose(currentDirectMatch.getAdversaryTeamId());
            logger.fatal(errText);
            throw new RuntimeException(errText);
        }
        getDirectMatch().put(currentDirectMatch.getAdversaryTeamId(),
            currentDirectMatch);
    }
    
    public String getRankingRecord(final String charSep) {
        List<String> rankingField = new LinkedList<>();
        rankingField.add(getLeagueType().toString());
        rankingField.add(getMatchPlayed().toString());
        rankingField.add(getTableResult40().toString());
        rankingField.add(getTableResult31().toString());
        rankingField.add(getTableResult22().toString());
        rankingField.add(getTableResult13().toString());
        rankingField.add(getTableResult04().toString());
        rankingField.add(getPoints().toString());
        
        return rankingField.stream().map(Object::toString).
            collect(Collectors.joining(charSep));
    }
}