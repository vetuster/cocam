/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import com.trksoft.util.StringUtil;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
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
    "playerStatPK",
    "playerNick",
    "leagueType",
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
    private PlayerStatPK playerStatPK;
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
    
    
    public PlayerStat(PlayerStatPK playerStatPK) {
        this();
        this.playerStatPK = playerStatPK.copy();
    }
    
    
    public PlayerStatPK getPlayerStatPK() {
        return playerStatPK;
    }

    public void setPlayerStatPK(PlayerStatPK playerStatPK) {
        this.playerStatPK = playerStatPK;
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
    
    public Float getWonCoefficient() {
        return tableWon.floatValue() / tablePlayed.floatValue();
    }
    
    public Float getGoalsCoefficient() {
        return goalsFavor.floatValue() / 
            (goalsFavor.floatValue() + goalsAgainst.floatValue());
    }
    
    public Boolean hasPlayed() {
        return (tablePlayed != 0);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TeamStat->");
        sb.append("playerStatPK");
        sb.append(playerStatPK);
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

    
    public void update(Table table, boolean isLocal) {
        incTablePlayed();
        if (isLocal) {
            incTablePlayedLocal();
            setGoalsFavor(getGoalsFavor() + table.getLocalPairScore());
            setGoalsAgainst(getGoalsAgainst() + table.getVisitingPairScore());
            
            if (table.getLocalPairScore() > table.getVisitingPairScore()) {
                incTableWon();
                incTableWonLocal();
            } else {
                incTableLost();
                incTableLostLocal();
            }
        } else {
            incTablePlayedVisiting();
            setGoalsFavor(getGoalsFavor() + table.getVisitingPairScore());
            setGoalsAgainst(getGoalsAgainst() + table.getLocalPairScore());
            
            if (table.getVisitingPairScore() > table.getLocalPairScore()) {
                incTableWon();
                incTableWonVisiting();
            } else {
                incTableLost();
                incTableLostVisiting();
            }
        }
    }
    
    public String getRankingRecord(final String teamDenom,
        final String charSep) {
        List<String> rankingField = new LinkedList<>();
        rankingField.add(getPlayerStatPK().getLeagueType().toString());
        rankingField.add(getPlayerStatPK().getPlayerNick());
        rankingField.add(teamDenom);
        rankingField.add(getTablePlayed().toString());
        rankingField.add(getTableWon().toString());
        rankingField.add(getTableLost().toString());
        rankingField.add(getTablePlayedLocal().toString());
        rankingField.add(getTableWonLocal().toString());
        rankingField.add(getTableLostLocal().toString());
        rankingField.add(getTablePlayedVisiting().toString());
        rankingField.add(getTableWonVisiting().toString());
        rankingField.add(getTableLostVisiting().toString());
        rankingField.add(getGoalsFavor().toString());
        rankingField.add(getGoalsAgainst().toString());

        // formateo de cifras
        CocamProps comcaProps = CocamProps.getInstance();
        Locale defaultLocale = comcaProps.getDefaultLocale();
        NumberFormat nf = NumberFormat.getInstance(defaultLocale);
        
        rankingField.add(nf.format(getWonCoefficient()));
        
        rankingField.add(nf.format(getGoalsCoefficient()));

        return rankingField.stream().map(Object::toString).
            collect(Collectors.joining(charSep));
    }
    
    protected static String getRankingRecordHead(final String charSep) {
        List<String> rankingField = new LinkedList<>();
        rankingField.add("LeagueType");
        rankingField.add("Nick");
        rankingField.add("Team");
        rankingField.add("J");
        rankingField.add("G");
        rankingField.add("P");
        rankingField.add("JL");
        rankingField.add("GL");
        rankingField.add("PL");
        rankingField.add("JV");
        rankingField.add("GV");
        rankingField.add("PV");
        rankingField.add("Chicos F");
        rankingField.add("Chicos C");
        rankingField.add("Coef");
        rankingField.add("Chico AVG");

        return rankingField.stream().map(Object::toString).
            collect(Collectors.joining(charSep));
    }
}