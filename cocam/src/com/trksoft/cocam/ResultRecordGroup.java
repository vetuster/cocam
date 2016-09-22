/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import com.trksoft.util.StringUtil;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

/**
 *
 * @author jasuarez
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "dayId",
    "resultRecord"
})
@XmlRootElement
public class ResultRecordGroup {
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(ResultRecordGroup.class);
    
    @XmlAttribute
    private Integer dayId;
    
    @XmlElement(required = true)
    private final List<ResultRecord> resultRecord;

    public ResultRecordGroup() {
        resultRecord = new LinkedList<>();
    }

    public Integer getDayId() {
        return dayId;
    }

    public void setDayId(Integer dayId) {
        this.dayId = dayId;
    }

    public List<ResultRecord> getResultRecord() {
        return resultRecord;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ResultRecordGroup->");
        sb.append("dayId");
        sb.append(StringUtil.enclose(dayId));
        sb.append(resultRecord.stream().map(Object::toString).
            collect(Collectors.joining("->")));
        return sb.toString();
    }
    
    public void marshall(File resultRecordGroupFile) throws JAXBException {
        try {
            JAXBContext jaxbContext
                = JAXBContext.newInstance(ResultRecordGroup.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(this, resultRecordGroupFile);
        } catch (JAXBException jaxbex) {
            logger.fatal(jaxbex);
            throw jaxbex;
        }
    }
    
    public static ResultRecordGroup unmarshall(
        File resultRecordGroupFile) throws JAXBException {
        ResultRecordGroup resultRecordGroup = null;
        try {
            JAXBContext jaxbContext = 
                JAXBContext.newInstance(ResultRecordGroup.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            resultRecordGroup = (ResultRecordGroup)
                jaxbUnmarshaller.unmarshal(resultRecordGroupFile);
        } catch (JAXBException jaxbex) {
            logger.fatal(jaxbex);
            throw jaxbex;
        }
        return resultRecordGroup;
    }
    
    public static ResultRecordGroup unmarshall(File resultRecordGroupFile,
        File resultRecordGroupSchema) throws JAXBException, SAXException {
        ResultRecordGroup resultRecordGroup = null;
        try {
            JAXBContext jaxbContext = 
                JAXBContext.newInstance(ResultRecordGroup.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            SchemaFactory schemaFactory = SchemaFactory.newInstance(
                XMLConstants.W3C_XML_SCHEMA_NS_URI); 
            Schema schema = schemaFactory.newSchema(resultRecordGroupSchema); 
            jaxbUnmarshaller.setSchema(schema);
            resultRecordGroup = (ResultRecordGroup)
                jaxbUnmarshaller.unmarshal(resultRecordGroupFile);
        } catch (JAXBException | SAXException jaxbex) {
            logger.fatal(jaxbex);
            throw jaxbex;
        }
        return resultRecordGroup;
    }
}
