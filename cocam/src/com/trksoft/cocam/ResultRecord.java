/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import com.trksoft.flatfile.FieldDesc;
import com.trksoft.flatfile.SepColRecordDesc;
import com.trksoft.util.StringUtil;
import java.time.LocalDate;

/**
 *
 * @author PSUANZES
 */
public class ResultRecord {
    
    private String recordType;
    private String seasonId;
    private String leagueType;
    private Integer roundId;
    private LocalDate roundDate;
    private String resultTypeTeam;
    private String localTeamId;
    private String localTeamName;
    private Integer localTeamScore;
    private Integer visitingTeamScore;
    private String visitingTeamId;
    private String visitingTeamName;
    private String resultTypeSingle;
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

    public LocalDate getRoundDate() {
        return roundDate;
    }

    public void setRoundDate(LocalDate roundDate) {
        this.roundDate = roundDate;
    }

    public String getResultTypeTeam() {
        return resultTypeTeam;
    }

    public void setResultTypeTeam(String resultTypeTeam) {
        this.resultTypeTeam = resultTypeTeam;
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

    public Integer getVisitingTeamScore() {
        return visitingTeamScore;
    }

    public void setVisitingTeamScore(Integer visitingTeamScore) {
        this.visitingTeamScore = visitingTeamScore;
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

    public String getResultTypeSingle() {
        return resultTypeSingle;
    }

    public void setResultTypeSingle(String resultTypeSingle) {
        this.resultTypeSingle = resultTypeSingle;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
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

    public Integer getVisitingPairScore() {
        return visitingPairScore;
    }

    public void setVisitingPairScore(Integer visitingPairScore) {
        this.visitingPairScore = visitingPairScore;
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

    public static ResultRecord build(String record,
        SepColRecordDesc sepColRecordDesc) {
        ResultRecord resultRecord = new ResultRecord();
        String[] fieldContent = record.split(sepColRecordDesc.getCharSep());
        for (FieldDesc fieldDesc : sepColRecordDesc.getFieldDesc()) {
            
//            logger.debug("fieldDesc->" + fieldDesc);
//            logger.debug("startIndex(" + startIndex
//                + "),fieldContent(" + fieldContent + ")");
        }
        return resultRecord;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("recordType");
        sb.append(StringUtil.enclose(recordType));
        sb.append("seasonId");
        sb.append(StringUtil.enclose(seasonId));
        sb.append("leagueType");
        sb.append(StringUtil.enclose(leagueType));
        sb.append("recordType");
        sb.append(StringUtil.enclose(recordType)); 
        sb.append("roundNumber");
        sb.append(StringUtil.enclose(roundId));
        sb.append("roundDate");
        sb.append(StringUtil.enclose(
            CocamDatatypeConverter.printLocalDate(roundDate)));
        sb.append("resultTypeTeam");
        sb.append(StringUtil.enclose(resultTypeTeam));
        sb.append("localTeamId");
        sb.append(StringUtil.enclose(localTeamId));
        sb.append("localTeamName");
        sb.append(StringUtil.enclose(localTeamName));
        sb.append("localTeamScore");
        sb.append(StringUtil.enclose(localTeamScore));
        sb.append("visitingTeamScore");
        sb.append(StringUtil.enclose(visitingTeamScore));
        sb.append("visitingTeamId");
        sb.append(StringUtil.enclose(visitingTeamId));
        sb.append("visitingTeamName");
        sb.append(StringUtil.enclose(visitingTeamName));
        sb.append("resultTypeSingle");
        sb.append(StringUtil.enclose(resultTypeSingle));
        sb.append("tableId");
        sb.append(StringUtil.enclose(tableId));
        sb.append("localPayerNameOne");
        sb.append(StringUtil.enclose(localPayerNameOne));
        sb.append("localPayerNameTwo");
        sb.append(StringUtil.enclose(localPayerNameTwo));
        sb.append("localPairScore");
        sb.append(StringUtil.enclose(localPairScore));
        sb.append("visitingPairScore");
        sb.append(StringUtil.enclose(visitingPairScore));
        sb.append("visitingPayerNameOne");
        sb.append(StringUtil.enclose(visitingPayerNameOne));
        sb.append("visitingPayerNameTwo");
        sb.append(StringUtil.enclose(visitingPayerNameTwo));        
        return sb.toString();
    }
    
}
