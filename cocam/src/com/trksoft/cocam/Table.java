/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import com.trksoft.util.StringUtil;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author jasuarez
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "tableId",
    "localPlayerNickOne",
    "localPlayerNickTwo",
    "localPairScore",
    "visitingPlayerNickOne",
    "visitingPlayerNickTwo",
    "visitingPairScore"
})
@XmlRootElement
public class Table implements Comparable<Table> {
    
    @XmlAttribute
    private Integer tableId;
    
    @XmlElement(required = true)
    private String localPlayerNickOne;
        
    @XmlElement(required = true)
    private String localPlayerNickTwo;
        
    @XmlElement(required = true)
    private Integer localPairScore;
    
    @XmlElement(required = true)
    private String visitingPlayerNickOne;
        
    @XmlElement(required = true)
    private String visitingPlayerNickTwo;
        
    @XmlElement(required = true)
    private Integer visitingPairScore;
    
    public Table() {
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
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

    public Integer getVisitingPairScore() {
        return visitingPairScore;
    }

    public void setVisitingPairScore(Integer visitingPairScore) {
        this.visitingPairScore = visitingPairScore;
    }

    
    @Override
    public int compareTo(Table table) {
        return tableId.compareTo(table.getTableId());
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Table->");
        sb.append("tableId");
        sb.append(StringUtil.enclose(tableId));
        sb.append(",localPlayerNickOne");
        sb.append(StringUtil.enclose(localPlayerNickOne));
        sb.append(",localPlayerNickTwo");
        sb.append(StringUtil.enclose(localPlayerNickTwo));
        sb.append(",localPairScore");
        sb.append(StringUtil.enclose(localPairScore));
        sb.append(",visitingPlayerNickOne");
        sb.append(StringUtil.enclose(visitingPlayerNickOne));
        sb.append(",visitingPlayerNickTwo");
        sb.append(StringUtil.enclose(visitingPlayerNickTwo));
        sb.append(",visitingPairScore");
        sb.append(StringUtil.enclose(visitingPairScore));
        return sb.toString();
    }

    
    public static Table unmarshall(File tableFile) throws CocamException {
        Table table = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Table.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            table = (Table) jaxbUnmarshaller.unmarshal(tableFile);
        } catch (JAXBException jaxbex) {
            throw new CocamException(jaxbex);
        }
        return table;
    }
    
    public static Table build(Result result) {
        Table table = new Table();
        table.setTableId(result.getTableId());
        table.setLocalPlayerNickOne(result.getLocalPlayerNickOne());
        table.setLocalPlayerNickTwo(result.getLocalPlayerNickTwo());
        table.setLocalPairScore(result.getLocalPairScore());
        table.setVisitingPlayerNickOne(result.getVisitingPlayerNickOne());
        table.setVisitingPlayerNickTwo(result.getVisitingPlayerNickTwo());
        table.setVisitingPairScore(result.getVisitingPairScore());
        return table;
    }
}
