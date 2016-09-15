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
    "localPlayerNameOne",
    "localPlayerNameTwo",
    "localPairScore",
    "visitingPlayerNameOne",
    "visitingPlayerNameTwo",
    "visitingPairScore"
})
@XmlRootElement
public class Table {
    
    @XmlAttribute
    private Integer tableId;
    
    @XmlElement(required = true)
    private String localPlayerNameOne;
        
    @XmlElement(required = true)
    private String localPlayerNameTwo;
        
    @XmlElement(required = true)
    private Integer localPairScore;
    
    @XmlElement(required = true)
    private String visitingPlayerNameOne;
        
    @XmlElement(required = true)
    private String visitingPlayerNameTwo;
        
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

    public String getLocalPlayerNameOne() {
        return localPlayerNameOne;
    }

    public void setLocalPlayerNameOne(String localPlayerNameOne) {
        this.localPlayerNameOne = localPlayerNameOne;
    }

    public String getLocalPlayerNameTwo() {
        return localPlayerNameTwo;
    }

    public void setLocalPlayerNameTwo(String localPlayerNameTwo) {
        this.localPlayerNameTwo = localPlayerNameTwo;
    }

    public Integer getLocalPairScore() {
        return localPairScore;
    }

    public void setLocalPairScore(Integer localPairScore) {
        this.localPairScore = localPairScore;
    }

    public String getVisitingPlayerNameOne() {
        return visitingPlayerNameOne;
    }

    public void setVisitingPlayerNameOne(String visitingPlayerNameOne) {
        this.visitingPlayerNameOne = visitingPlayerNameOne;
    }

    public String getVisitingPlayerNameTwo() {
        return visitingPlayerNameTwo;
    }

    public void setVisitingPlayerNameTwo(String visitingPlayerNameTwo) {
        this.visitingPlayerNameTwo = visitingPlayerNameTwo;
    }

    public Integer getVisitingPairScore() {
        return visitingPairScore;
    }

    public void setVisitingPairScore(Integer visitingPairScore) {
        this.visitingPairScore = visitingPairScore;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("tableId");
        sb.append(StringUtil.enclose(tableId));
        sb.append("localPlayerNameOne");
        sb.append(StringUtil.enclose(localPlayerNameOne));
        sb.append("localPlayerNameTwo");
        sb.append(StringUtil.enclose(localPlayerNameTwo));
        sb.append("localPairScore");
        sb.append(StringUtil.enclose(localPairScore));
        sb.append("visitingPlayerNameOne");
        sb.append(StringUtil.enclose(visitingPlayerNameOne));
        sb.append("visitingPlayerNameTwo");
        sb.append(StringUtil.enclose(visitingPlayerNameTwo));
        sb.append("visitingPairScore");
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
}
