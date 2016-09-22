/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import com.trksoft.flatfile.FieldDesc;
import com.trksoft.flatfile.SepColRecordDesc;
import com.trksoft.util.StringUtil;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author PSUANZES
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "recordType",
    "recordId",
    "seasonId",
    "leagueType",
    "roundId",
    "dayId",
    "dayDate",
    "matchHead",
    "localTeamId",
    "localTeamName",
    "localTeamScore",
    "visitingTeamId",
    "visitingTeamName",
    "visitingTeamScore",
    "tableId",
    "localPlayerNickOne",
    "localPlayerNickTwo",
    "localPairScore",
    "visitingPairScore",
    "visitingPlayerNickOne",
    "visitingPlayerNickTwo"
})
@XmlRootElement
public class ResultRecord implements Comparable<ResultRecord>{
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(ResultRecord.class);

    public static final String END_TYPE = "END";
    public static final String COMMENT_TYPE = "COM";
    public static final String INFO_TYPE = "INF";

    @XmlElement(required = true)
    private String recordType;
    @XmlElement(required = true)
    private Integer recordId;
    @XmlElement(required = false)
    private String seasonId;
    @XmlElement(required = true)
    private String leagueType;
    @XmlElement(required = true)
    private Integer roundId;
    @XmlElement(required = false)
    private Integer dayId;
    @XmlElement(required = false)
    @XmlJavaTypeAdapter(CocamAdapter.class)
    private LocalDate dayDate;
    @XmlElement(required = true)
    private Boolean matchHead;
    @XmlElement(required = true)
    private String localTeamId;
    @XmlElement(required = false)
    private String localTeamName;
    @XmlElement(required = false)
    private Integer localTeamScore;
    @XmlElement(required = false)
    private Integer visitingTeamScore;
    @XmlElement(required = true)
    private String visitingTeamId;
    @XmlElement(required = false)
    private String visitingTeamName;
    @XmlElement(required = true)
    private Integer tableId;
    @XmlElement(required = true)
    private String localPlayerNickOne;
    @XmlElement(required = true)
    private String localPlayerNickTwo;
    @XmlElement(required = true)
    private Integer localPairScore;
    @XmlElement(required = true)
    private Integer visitingPairScore;
    @XmlElement(required = true)
    private String visitingPlayerNickOne;
    @XmlElement(required = true)
    private String visitingPlayerNickTwo;

    public ResultRecord() {
    }
    
    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }
    public void setRecordId(String recordId) {
        if (StringUtil.isNullOrEmpty(recordId)) {
            this.recordId = null;
        } else {
            setRecordId(Integer.valueOf(recordId));
        }
    }
    
    public String getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(String seasonId) {
        this.seasonId = seasonId;
    }

    public String getLeagueType() {
        return leagueType;
    }

    public Integer getRoundId() {
        return roundId;
    }

    public void setRoundId(Integer roundId) {
        this.roundId = roundId;
    }
    public void setRoundId(String roundId) {
        if (StringUtil.isNullOrEmpty(roundId)) {
            this.roundId = null;
        } else {
            setRoundId(Integer.valueOf(roundId));
        }
    }
    
    public void setLeagueType(String leagueType) {
        this.leagueType = leagueType;
    }

    public Integer getDayId() {
        return dayId;
    }

    public void setDayId(Integer dayId) {
        this.dayId = dayId;
    }
    public void setDayId(String dayId) {
        if (StringUtil.isNullOrEmpty(dayId)) {
            this.dayId = null;
        } else {
            setDayId(Integer.valueOf(dayId));
        }
    }
    

    public LocalDate getDayDate() {
        return dayDate;
    }

    public void setDayDate(LocalDate dayDate) {
        this.dayDate = dayDate;
    }
    public void setDayDate(String dayDate) {
        if (StringUtil.isNullOrEmpty(dayDate)) {
            this.dayDate = null;
        } else {
            setDayDate(CocamDatatypeConverter.parseLocalDate(dayDate));
        }
    }
    
    public Boolean getMatchHead() {
        return matchHead;
    }
    public Boolean isMatchHead() {
        return matchHead;
    }

    public void setMatchHead(Boolean matchHead) {
        this.matchHead = matchHead;
    }
    public void setMatchHead(String matchHead) {
        if (StringUtil.isNullOrEmpty(matchHead)) {
            this.matchHead = null;
        } else {
            setMatchHead(Boolean.parseBoolean(matchHead));
        }
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
    public void setLocalTeamScore(String localTeamScore) {
        if (StringUtil.isNullOrEmpty(localTeamScore)) {
            this.localTeamScore = null;
        } else {
            setLocalTeamScore(Integer.valueOf(localTeamScore));
        }
    }

    public Integer getVisitingTeamScore() {
        return visitingTeamScore;
    }

    public void setVisitingTeamScore(Integer visitingTeamScore) {
        this.visitingTeamScore = visitingTeamScore;
    }
    public void setVisitingTeamScore(String visitingTeamScore) {
        if (StringUtil.isNullOrEmpty(visitingTeamScore)) {
            this.visitingTeamScore = null;
        } else {
            setVisitingTeamScore(Integer.valueOf(visitingTeamScore));
        }
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

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }
    public void setTableId(String tableId) {
        if (StringUtil.isNullOrEmpty(tableId)) {
            this.tableId = null;
        } else {
            setTableId(Integer.valueOf(tableId));
        }
    }

    public String getLocalPlayerNickOne() {
        return localPlayerNickOne;
    }

    public void setLocalPlayerNickOne(String localPlayerNickOne) {
        this.localPlayerNickOne = localPlayerNickOne;
    }

    public String getLocalPlayerNickTwo() {
        return localPlayerNickTwo;
    }

    public void setLocalPlayerNickTwo(String localPlayerNickTwo) {
        this.localPlayerNickTwo = localPlayerNickTwo;
    }

    public Integer getLocalPairScore() {
        return localPairScore;
    }

    public void setLocalPairScore(Integer localPairScore) {
        this.localPairScore = localPairScore;
    }
    public void setLocalPairScore(String localPairScore) {
        if (StringUtil.isNullOrEmpty(localPairScore)) {
            this.localPairScore = null;
        } else {
            setLocalPairScore(Integer.valueOf(localPairScore));
        }
    }

    public Integer getVisitingPairScore() {
        return visitingPairScore;
    }

    public void setVisitingPairScore(Integer visitingPairScore) {
        this.visitingPairScore = visitingPairScore;
    }
    public void setVisitingPairScore(String visitingPairScore) {
        if (StringUtil.isNullOrEmpty(visitingPairScore)) {
            this.visitingPairScore = null;
        } else {
            setVisitingPairScore(Integer.valueOf(visitingPairScore));
        }
    }

    public String getVisitingPlayerNickOne() {
        return visitingPlayerNickOne;
    }

    public void setVisitingPlayerNickOne(String visitingPlayerNickOne) {
        this.visitingPlayerNickOne = visitingPlayerNickOne;
    }

    public String getVisitingPlayerNickTwo() {
        return visitingPlayerNickTwo;
    }

    public void setVisitingPlayerNickTwo(String visitingPlayerNickTwo) {
        this.visitingPlayerNickTwo = visitingPlayerNickTwo;
    }

    public void marshall(File seasonFile) throws JAXBException {
        try {
            JAXBContext jaxbContext
                = JAXBContext.newInstance(ResultRecord.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(this, seasonFile);
        } catch (JAXBException jaxbex) {
            logger.fatal(jaxbex);
            throw jaxbex;
        }
    }
    
    @Override
    public int compareTo(ResultRecord other) {
        int i = this.dayId.compareTo(other.getDayId());
        if (i!=0) return i;
        return this.recordId.compareTo(other.getRecordId());
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ResultRecord->");
        sb.append("recordType");
        sb.append(StringUtil.enclose(recordType));
        sb.append(",recordId");
        sb.append(StringUtil.enclose(recordId));
        sb.append(",seasonId");
        sb.append(StringUtil.enclose(seasonId));
        sb.append(",leagueType");
        sb.append(StringUtil.enclose(leagueType));
        sb.append(",roundId");
        sb.append(StringUtil.enclose(roundId));
        sb.append(",dayId");
        sb.append(StringUtil.enclose(dayId));
        sb.append(",dayDate");
        sb.append((dayDate == null?
            StringUtil.enclose(StringUtil.NULL_LIT)
            : StringUtil.enclose(
                CocamDatatypeConverter.printLocalDate(dayDate))));
        sb.append(",matchHead");
        sb.append(StringUtil.enclose(matchHead));
        sb.append(",localTeamId");
        sb.append(StringUtil.enclose(localTeamId));
        sb.append(",localTeamName");
        sb.append(StringUtil.enclose(localTeamName));
        sb.append(",localTeamScore");
        sb.append(StringUtil.enclose(localTeamScore));
        sb.append(",visitingTeamScore");
        sb.append(StringUtil.enclose(visitingTeamScore));
        sb.append(",visitingTeamId");
        sb.append(StringUtil.enclose(visitingTeamId));
        sb.append(",visitingTeamName");
        sb.append(StringUtil.enclose(visitingTeamName));
        sb.append(",tableId");
        sb.append(StringUtil.enclose(tableId));
        sb.append(",localPlayerNickOne");
        sb.append(StringUtil.enclose(localPlayerNickOne));
        sb.append(",localPlayerNickTwo");
        sb.append(StringUtil.enclose(localPlayerNickTwo));
        sb.append(",localPairScore");
        sb.append(StringUtil.enclose(localPairScore));
        sb.append(",visitingPairScore");
        sb.append(StringUtil.enclose(visitingPairScore));
        sb.append(",visitingPlayerNickOne");
        sb.append(StringUtil.enclose(visitingPlayerNickOne));
        sb.append(",visitingPlayerNickTwo");
        sb.append(StringUtil.enclose(visitingPlayerNickTwo));        
        return sb.toString();
    }
    
    public ResultRecord build(String record,
        SepColRecordDesc sepColRecordDesc) throws CocamException {
        String[] fieldContent = record.split(sepColRecordDesc.getCharSep());
        for (FieldDesc fieldDesc : sepColRecordDesc.getFieldDesc()) {
            String fieldName = fieldDesc.getFieldName();
            String methodName = "set" + fieldName;
            String fieldValue = null;
            try {
                fieldValue = fieldContent[fieldDesc.getFieldNo()-1].trim();
                
                // si está vacio se considera no presente -null-
                if (StringUtil.isNullOrEmpty(fieldValue)) {
                    fieldValue = null;
                }
            } catch (ArrayIndexOutOfBoundsException aioobex) {
                logger.fatal("record" + record);
                logger.fatal("fieldNo" + fieldDesc.getFieldNo());
                logger.fatal(aioobex);
                throw new CocamException(aioobex);
            }
            Method method = null;
            try {
                method = getClass().getMethod(methodName, String.class);
            } catch (NoSuchMethodException nsmex) {
                logger.fatal(nsmex);
                throw new CocamException(nsmex);
            }
            logger.trace("field" + StringUtil.enclose(fieldName)
                + ",method" + StringUtil.enclose(method.getName())
                + ",value->" + StringUtil.enclose(fieldValue));
            try {
                method.invoke(this, fieldValue);
            } catch(IllegalAccessException | InvocationTargetException iatex) {
                logger.fatal("field" + StringUtil.enclose(fieldName)
                    + ",method" + StringUtil.enclose(method.getName())
                    + ",value->" + StringUtil.enclose(fieldValue));
                logger.fatal(iatex);
                throw new CocamException(iatex);
            }
        }
        return this;
    }

}
