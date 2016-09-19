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
    "teamName",
    "teamDenom",
    "teamSize"
})
@XmlRootElement
public class Team implements Comparable<Team>{
    
    @XmlAttribute(required = true)    
    private String teamId;
    @XmlAttribute(required = true)
    private String teamName;
    @XmlAttribute(required = true)
    private String teamDenom;
    @XmlAttribute(required = true)
    private Integer teamSize;

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamDenom() {
        return teamDenom;
    }

    public void setTeamDenom(String teamDenom) {
        this.teamDenom = teamDenom;
    }

    public Integer getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(Integer teamSize) {
        this.teamSize = teamSize;
    }


    @Override
    public int compareTo(Team otherTeam) {
        return this.teamId.compareTo(otherTeam.getTeamId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.teamId);
        hash = 79 * hash + Objects.hashCode(this.teamName);
        hash = 79 * hash + Objects.hashCode(this.teamDenom);
        hash = 79 * hash + Objects.hashCode(this.teamSize);
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
        final Team other = (Team) obj;
        if (!Objects.equals(this.teamId, other.teamId)) {
            return false;
        }
        if (!Objects.equals(this.teamName, other.teamName)) {
            return false;
        }
        if (!Objects.equals(this.teamDenom, other.teamDenom)) {
            return false;
        }
        if (!Objects.equals(this.teamSize, other.teamSize)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "teamId" + StringUtil.enclose(teamId)
            + ",teamName" + StringUtil.enclose(teamName)
            + ",teamDenom" + StringUtil.enclose(teamDenom)
            + ",teamSize" + StringUtil.enclose(teamSize);
    }
    
    
}