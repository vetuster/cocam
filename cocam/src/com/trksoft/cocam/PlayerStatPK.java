/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import com.trksoft.util.StringUtil;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
    "playerNick",
    "leagueType"
})
@XmlRootElement
public class PlayerStatPK {
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(PlayerStatPK.class);
    
    @XmlAttribute(required = true)    
    private String teamId;
    @XmlAttribute(required = true)    
    private String playerNick;
    @XmlAttribute(required = true)
    private LeagueType leagueType;
    
    
    public PlayerStatPK() {
    }
    
    public PlayerStatPK(String teamId, String playerNick,
        LeagueType leagueType) {
        this.teamId = teamId;
        this.playerNick = playerNick;
        this.leagueType = leagueType;
    }
    
    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public LeagueType getLeagueType() {
        return leagueType;
    }

    public void setLeagueType(LeagueType leagueType) {
        this.leagueType = leagueType;
    }

    public String getPlayerNick() {
        return playerNick;
    }

    public void setPlayerNick(String playerNick) {
        this.playerNick = playerNick;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.teamId);
        hash = 71 * hash + Objects.hashCode(this.playerNick);
        hash = 71 * hash + Objects.hashCode(this.leagueType);
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
        final PlayerStatPK other = (PlayerStatPK) obj;
        if (!Objects.equals(this.teamId, other.teamId)) {
            return false;
        }
        if (!Objects.equals(this.playerNick, other.playerNick)) {
            return false;
        }
        if (this.leagueType != other.leagueType) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PlayerStatPK->");
        sb.append("teamId");
        sb.append(StringUtil.enclose(teamId));
        sb.append(",playerNick");
        sb.append(StringUtil.enclose(playerNick));
        sb.append(",leagueType");
        sb.append(StringUtil.enclose(leagueType.toString()));
        return sb.toString();
    }
}
