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
    "roundDate",
    "matchHead",
    "localTeamId",
    "localTeamName",
    "localTeamScore",
    "visitingTeamId",
    "visitingTeamName",
    "visitingTeamScore",
    "matchLostVisiting",
    "tableId",
    "localPayerNameOne",
    "localPayerNameTwo",
    "localPairScore",
    "visitingPairScore",
    "visitingPayerNameOne",
    "visitingPayerNameTwo"
})
@XmlRootElement
public class ResultRecord {
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
    @XmlElement(required = false)
    private Integer roundId;
    @XmlElement(required = false)
    @XmlJavaTypeAdapter(CocamAdapter.class)
    private LocalDate roundDate;
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
    private String localPayerNameOne;
    @XmlElement(required = true)
    private String localPayerNameTwo;
    @XmlElement(required = true)
    private Integer localPairScore;
    @XmlElement(required = true)
    private Integer visitingPairScore;
    @XmlElement(required = true)
    private String visitingPayerNameOne;
    @XmlElement(required = true)
    private String visitingPayerNameTwo;

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

    public void setLeagueType(String leagueType) {
        this.leagueType = leagueType;
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
    

    public LocalDate getRoundDate() {
        return roundDate;
    }

    public void setRoundDate(LocalDate roundDate) {
        this.roundDate = roundDate;
    }
    public void setRoundDate(String roundDate) {
        if (StringUtil.isNullOrEmpty(roundDate)) {
            this.roundDate = null;
        } else {
            setRoundDate(CocamDatatypeConverter.parseLocalDate(roundDate));
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

    public String getLocalPayerNameOne() {
        return localPayerNameOne;
    }

    public void setLocalPayerNameOne(String localPayerNameOne) {
        this.localPayerNameOne = localPayerNameOne;
    }

    public String getLocalPayerNameTwo() {
        return localPayerNameTwo;
    }

    public void setLocalPayerNameTwo(String localPayerNameTwo) {
        this.localPayerNameTwo = localPayerNameTwo;
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

    public String getVisitingPayerNameOne() {
        return visitingPayerNameOne;
    }

    public void setVisitingPayerNameOne(String visitingPayerNameOne) {
        this.visitingPayerNameOne = visitingPayerNameOne;
    }

    public String getVisitingPayerNameTwo() {
        return visitingPayerNameTwo;
    }

    public void setVisitingPayerNameTwo(String visitingPayerNameTwo) {
        this.visitingPayerNameTwo = visitingPayerNameTwo;
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
        sb.append(",roundDate");
        sb.append((roundDate == null?
            "NULL"
            : StringUtil.enclose(
                CocamDatatypeConverter.printLocalDate(roundDate))));
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
        sb.append(",localPayerNameOne");
        sb.append(StringUtil.enclose(localPayerNameOne));
        sb.append(",localPayerNameTwo");
        sb.append(StringUtil.enclose(localPayerNameTwo));
        sb.append(",localPairScore");
        sb.append(StringUtil.enclose(localPairScore));
        sb.append(",visitingPairScore");
        sb.append(StringUtil.enclose(visitingPairScore));
        sb.append(",visitingPayerNameOne");
        sb.append(StringUtil.enclose(visitingPayerNameOne));
        sb.append(",visitingPayerNameTwo");
        sb.append(StringUtil.enclose(visitingPayerNameTwo));        
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
