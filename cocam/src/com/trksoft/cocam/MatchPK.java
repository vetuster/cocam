/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import com.trksoft.util.StringUtil;
import java.time.LocalDate;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author triki
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "seasonId",
    "leagueType",
    "roundId",
    "dayId",
    "dayDate",
    "localTeamId",
    "visitingTeamId"
})
@XmlRootElement
public class MatchPK {
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(MatchPK.class);
    
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
    @XmlAttribute(required = true)
    private String visitingTeamId;

    
    
    public MatchPK() {
    }

    public MatchPK(String seasonId, LeagueType leagueType, Integer roundId,
        Integer dayId, LocalDate dayDate, String localTeamId, 
        String visitingTeamId) {
        this.seasonId = seasonId;
        this.leagueType = leagueType;
        this.roundId = roundId;
        this.dayId = dayId;
        this.dayDate = dayDate;
        this.localTeamId = localTeamId;
        this.visitingTeamId = visitingTeamId;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.seasonId);
        hash = 31 * hash + Objects.hashCode(this.leagueType);
        hash = 71 * hash + Objects.hashCode(this.roundId);
        hash = 71 * hash + Objects.hashCode(this.dayId);
        hash = 31 * hash + Objects.hashCode(this.dayDate);
        hash = 71 * hash + Objects.hashCode(this.localTeamId);
        hash = 71 * hash + Objects.hashCode(this.visitingTeamId);
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
        final MatchPK other = (MatchPK) obj;
        if (!Objects.equals(this.seasonId, other.seasonId)) {
            return false;
        }
        if (this.leagueType != other.leagueType) {
            return false;
        }
        if (!Objects.equals(this.roundId, other.roundId)) {
            return false;
        }
        if (!Objects.equals(this.dayId, other.dayId)) {
            return false;
        }
        if (!Objects.equals(this.dayDate, other.dayDate)) {
            return false;
        }
        if (!Objects.equals(this.localTeamId, other.localTeamId)) {
            return false;
        }
        if (!Objects.equals(this.visitingTeamId, other.visitingTeamId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MatchPK->");
        sb.append("seasonId");
        sb.append(StringUtil.enclose(seasonId));
        sb.append("leagueType");
        sb.append(StringUtil.enclose(leagueType.toString()));
        sb.append("roundId");
        sb.append(StringUtil.enclose(roundId));
        sb.append("dayId");
        sb.append(StringUtil.enclose(dayId));
        sb.append(",roundDate");
        sb.append(StringUtil.enclose(
            CocamDatatypeConverter.printLocalDate(dayDate)));
        sb.append(",localTeamId");
        sb.append(StringUtil.enclose(localTeamId));
        sb.append(",visitingTeamId");
        sb.append(StringUtil.enclose(visitingTeamId));
        return sb.toString();
    }
}
