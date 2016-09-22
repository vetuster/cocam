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
    "adversaryTeamId",
    "playedAsLocal",
    "pointsAsLocal",
    "playedAsVisiting",
    "pointsAsVisiting"
})
@XmlRootElement
public class DirectMatch {
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(DirectMatch.class);
    
    @XmlAttribute(required = true)    
    private String adversaryTeamId;
    @XmlAttribute(required = true)    
    private Boolean playedAsLocal;
    @XmlAttribute(required = true)
    private Integer pointsAsLocal;
    @XmlAttribute(required = true)
    private Boolean playedAsVisiting;
    @XmlAttribute(required = true)
    private Integer pointsAsVisiting;
    
    public DirectMatch() {
        this.playedAsLocal = false;
        this.playedAsVisiting = false;
    }
    
    
    public String getAdversaryTeamId() {
        return adversaryTeamId;
    }

    public void setAdversaryTeamId(String adversaryTeamId) {
        this.adversaryTeamId = adversaryTeamId;
    }
    
    public void setPlayedAsLocal(Boolean playedAsLocal) {
        this.playedAsLocal = playedAsLocal;
    }
    
    public Boolean getPlayedAsLocal() {
        return playedAsLocal;
    }
    public Boolean hasPlayedAsLocal() {
        return getPlayedAsLocal();
    }
    
    public Integer getPointsAsLocal() {
        return pointsAsLocal;
    }

    public void setPointsAsLocal(Integer pointsAsLocal) {
        this.pointsAsLocal = pointsAsLocal;
    }

    public void setPlayedAsVisiting(Boolean playedAsVisiting) {
        this.playedAsVisiting = playedAsVisiting;
    }
    
    public Boolean getPlayedAsVisiting() {
        return playedAsVisiting;
    }
    public Boolean hasPlayedAsVisiting() {
        return getPlayedAsVisiting();
    }

    public Integer getPointsAsVisiting() {
        return pointsAsVisiting;
    }

    public void setPointsAsVisiting(Integer pointsAsVisiting) {
        this.pointsAsVisiting = pointsAsVisiting;
    }
    
    public Integer getTotalPoints() {
        return pointsAsLocal+pointsAsVisiting;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.adversaryTeamId);
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
        final DirectMatch other = (DirectMatch) obj;
        if (!Objects.equals(this.adversaryTeamId, other.adversaryTeamId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TeamStat->");
        sb.append("adversaryTeamId");
        sb.append(StringUtil.enclose(adversaryTeamId));
        sb.append(",playedAsLocal");
        sb.append(StringUtil.enclose(playedAsLocal));
        sb.append(",pointsAsLocal");
        sb.append(StringUtil.enclose(pointsAsLocal));
        sb.append(",playedAsVisiting");
        sb.append(StringUtil.enclose(playedAsVisiting));
        sb.append(",pointsAsVisiting");
        sb.append(StringUtil.enclose(pointsAsVisiting));
        return sb.toString();
    }
    
}