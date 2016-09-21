/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import com.trksoft.util.StringUtil;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
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
    "playerDenom",
    "tablePlayed",
    "tableWon",
    "tableLost",
    "tablePlayedLocal",
    "tableWonLocal",
    "tableLostLocal",
    "tablePlayedVisiting",
    "tableWonVisiting",
    "tableLostVisiting",
    "goalsFavor",
    "goalsAgainst"
})
@XmlRootElement
public class PlayerStat {
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(PlayerStat.class);
    
    @XmlAttribute(required = true)    
    private String teamId;
    @XmlAttribute(required = true)    
    private String playerDenom;
    @XmlElement(required = true)
    private Integer tablePlayed;
    @XmlElement(required = true)
    private Integer tableWon;
    @XmlElement(required = true)
    private Integer tableLost;
    @XmlElement(required = true)
    private Integer tablePlayedLocal;
    @XmlElement(required = true)
    private Integer tableWonLocal;
    @XmlElement(required = true)
    private Integer tableLostLocal;
    @XmlElement(required = true)
    private Integer tablePlayedVisiting;
    @XmlElement(required = true)
    private Integer tableWonVisiting;
    @XmlElement(required = true)
    private Integer tableLostVisiting;
    @XmlElement(required = true)
    private Integer goalsFavor;
    @XmlElement(required = true)
    private Integer goalsAgainst;
    
    public PlayerStat() {
        this.tablePlayed = 0;
        this.tableWon = 0;
        this.tableLost = 0;
        this.tablePlayedLocal = 0;
        this.tableWonLocal = 0;
        this.tableLostLocal = 0;
        this.tablePlayedVisiting = 0;
        this.tableWonVisiting = 0;
        this.tableLostVisiting = 0;
        this.goalsFavor = 0;
        this.goalsAgainst = 0;
    }
    
    
    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getPlayerDenom() {
        return playerDenom;
    }

    public void setPlayerDenom(String playerDenom) {
        this.playerDenom = playerDenom;
    }

    public Integer getTablePlayed() {
        return tablePlayed;
    }

    public void setTablePlayed(Integer tablePlayed) {
        this.tablePlayed = tablePlayed;
    }
    public void incTablePlayed() {
        ++this.tablePlayed;
    }
    
    public Integer getTableWon() {
        return tableWon;
    }

    public void setTableWon(Integer tableWon) {
        this.tableWon = tableWon;
    }
    public void incTableWon() {
        ++this.tableWon;
    }

    public Integer getTableLost() {
        return tableLost;
    }

    public void setTableLost(Integer tableLost) {
        this.tableLost = tableLost;
    }
    public void incTableLost() {
        ++this.tableLost;
    }

    public Integer getTablePlayedLocal() {
        return tablePlayedLocal;
    }

    public void setTablePlayedLocal(Integer tablePlayedLocal) {
        this.tablePlayedLocal = tablePlayedLocal;
    }
    public void incTablePlayedLocal() {
        ++this.tablePlayedLocal;
    }

    public Integer getTableWonLocal() {
        return tableWonLocal;
    }

    public void setTableWonLocal(Integer tableWonLocal) {
        this.tableWonLocal = tableWonLocal;
    }
    public void incTableWonLocal() {
        ++this.tableWonLocal;
    }

    public Integer getTableLostLocal() {
        return tableLostLocal;
    }

    public void setTableLostLocal(Integer tableLostLocal) {
        this.tableLostLocal = tableLostLocal;
    }
    public void incTableLostLocal() {
        ++this.tableLostLocal;
    }

    public Integer getTablePlayedVisiting() {
        return tablePlayedVisiting;
    }

    public void setTablePlayedVisiting(Integer tablePlayedVisiting) {
        this.tablePlayedVisiting = tablePlayedVisiting;
    }
    public void incTablePlayedVisiting() {
        ++this.tablePlayedVisiting;
    }

    public Integer getTableWonVisiting() {
        return tableWonVisiting;
    }

    public void setTableWonVisiting(Integer tableWonVisiting) {
        this.tableWonVisiting = tableWonVisiting;
    }
    public void incTableWonVisiting() {
        ++this.tableWonVisiting;
    }

    public Integer getTableLostVisiting() {
        return tableLostVisiting;
    }

    public void setTableLostVisiting(Integer tableLostVisiting) {
        this.tableLostVisiting = tableLostVisiting;
    }
    public void incTableLostVisiting() {
        ++this.tableLostVisiting;
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
    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.teamId);
        hash = 37 * hash + Objects.hashCode(this.playerDenom);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PlayerStat other = (PlayerStat) obj;
        if (!Objects.equals(this.teamId, other.teamId)) {
            return false;
        }
        if (!Objects.equals(this.playerDenom, other.playerDenom)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TeamStat->");
        sb.append("teamId");
        sb.append(StringUtil.enclose(teamId));
        sb.append(",playerDenom");
        sb.append(StringUtil.enclose(playerDenom));
        sb.append(",tablePlayed");
        sb.append(StringUtil.enclose(tablePlayed));
        sb.append(",tableWon");
        sb.append(StringUtil.enclose(tableWon));
        sb.append(",tableLost");
        sb.append(StringUtil.enclose(tableLost));
        sb.append(",tablePlayedLocal");
        sb.append(StringUtil.enclose(tablePlayedLocal));
        sb.append(",tableWonLocal");
        sb.append(StringUtil.enclose(tableWonLocal));
        sb.append(",tableLostLocal");
        sb.append(StringUtil.enclose(tableLostLocal));
        sb.append(",tablePlayedVisiting");
        sb.append(StringUtil.enclose(tablePlayedVisiting));
        sb.append(",tableWonVisiting");
        sb.append(StringUtil.enclose(tableWonVisiting));
        sb.append(",tableLostVisiting");
        sb.append(StringUtil.enclose(tableLostVisiting));
        sb.append(",goalsFavor");
        sb.append(StringUtil.enclose(goalsFavor));
        sb.append(",goalsAgainst");
        sb.append(StringUtil.enclose(goalsAgainst));
        return sb.toString();
    }
    
    public void update(Match match) {
        
        
        //*************** SIN IMPLEMENTAR ************************************
        
        
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
        
        incTablePlayed();
        if (local) {
            incTablePlayedLocal();
        } else {
            incTablePlayedVisiting();
        }
        
        switch (match.getResultType()) {
            case R04: {
                if (local) {
                    incTableLost();
                    incTableLostLocal();
                } else {
                    incTableWon();
                    incTableWonVisiting();
                }
                break;
            }
            case R13: {
                if (local) {
                    incTableLost();
                    incTableLostLocal();
                } else {
                    incTableWon();
                    incTableWonVisiting();
                }
                break;
            }
            case R22: {
                break;
            }
            case R31: {
                if (local) {
                    incTableWon();
                    incTableWonLocal();
                } else {
                    incTableLost();
                    incTableLostVisiting();
                }
                break;
            }
            case R40: {
                if (local) {
                    incTableWon();
                    incTableWonLocal();
                } else {
                    incTableLost();
                    incTableLostVisiting();
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
    }
    
    public String getRankingRecord(final String charSep) {
        List<String> rankingField = new LinkedList<>();
        rankingField.add(getPlayerDenom());
        rankingField.add(getTablePlayed().toString());
        
        return rankingField.stream().map(Object::toString).
            collect(Collectors.joining(charSep));
    }
}