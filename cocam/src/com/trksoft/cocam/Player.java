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

/**
 *
 * @author triki
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "teamId",
    "playerNick"
})
@XmlRootElement
public class Player implements Comparable<Player>{
    
    @XmlAttribute(required = true)    
    private String teamId;
    @XmlAttribute(required = true)
    private String playerNick;

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getPlayerNick() {
        return playerNick;
    }

    public void setPlayerNick(String playerNick) {
        this.playerNick = playerNick;
    }
    
    public String getPlayerKey() {
        StringBuilder playerKey = new StringBuilder(getTeamId());
        playerKey.append(getPlayerNick());
        return playerKey.toString();
    }
    
    public static String getPlayerKey(String teamId, String playerNick) {
        StringBuilder playerKey = new StringBuilder(teamId);
        playerKey.append(playerNick);
        return playerKey.toString();
    }
    
    @Override
    public int compareTo(Player otherTeam) {
        int i = this.teamId.compareTo(otherTeam.getTeamId());
        if (i!=0) return i;
        return this.playerNick.compareTo(otherTeam.getPlayerNick());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.teamId);
        hash = 79 * hash + Objects.hashCode(this.playerNick);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (!Objects.equals(this.teamId, other.teamId)) {
            return false;
        }
        if (!Objects.equals(this.playerNick, other.playerNick)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Player->");
        sb.append("teamId");
        sb.append(StringUtil.enclose(teamId));
        sb.append(",playerNick");
        sb.append(StringUtil.enclose(playerNick));
        return sb.toString();
    }
    
}