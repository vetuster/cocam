/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import com.trksoft.flatfile.FieldDesc;
import com.trksoft.flatfile.SepColRecordDesc;
import com.trksoft.util.StringUtil;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author PSUANZES
 */
public class ResultRecord {
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(ResultRecord.class);

    public static final String END_TYPE = "END";
    public static final String COMMENT_TYPE = "COM";
    public static final String INFO_TYPE = "INF";

    private String recordType;
    private Integer recordId;
    private String seasonId;
    private String leagueType;
    private Integer roundId;
    private LocalDate roundDate;
    private Boolean matchHead;
    private String localTeamId;
    private String localTeamName;
    private Integer localTeamScore;
    private Integer visitingTeamScore;
    private String visitingTeamId;
    private String visitingTeamName;
    private Integer tableId;
    private String localPayerNameOne;
    private String localPayerNameTwo;
    private Integer localPairScore;
    private Integer visitingPairScore;
    private String visitingPayerNameOne;
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
        setRecordId(Integer.valueOf(recordId));
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
        if (roundId == null) {
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
        if (roundDate == null) {
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
        setMatchHead(Boolean.parseBoolean(matchHead));
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
        if (localTeamScore == null) {
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
        if (visitingTeamScore == null) {
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
        if (tableId == null) {
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
        if (localPairScore == null) {
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
        if (visitingPairScore == null) {
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
                logger.fatal(iatex);
                throw new CocamException(iatex);
            }
        }
        return this;
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
    
}
