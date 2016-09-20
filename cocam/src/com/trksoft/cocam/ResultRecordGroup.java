/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author jasuarez
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "resultRecord"
})
@XmlRootElement
public class ResultRecordGroup {
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(ResultRecordGroup.class);
    
    @XmlElement(required = true)
    private final List<ResultRecord> resultRecord;

    public ResultRecordGroup() {
        resultRecord = new LinkedList<>();
    }

    public List<ResultRecord> getResultRecord() {
        return resultRecord;
    }
    
    @Override
    public String toString() {
        return resultRecord.stream().map(Object::toString).
            collect(Collectors.joining("->"));
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
        File fixedLengthColRecordDescFile) throws JAXBException {
        ResultRecordGroup resultRecordGroup = null;
        try {
            JAXBContext jaxbContext = 
                JAXBContext.newInstance(ResultRecordGroup.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            resultRecordGroup = (ResultRecordGroup)
                jaxbUnmarshaller.unmarshal(fixedLengthColRecordDescFile);
        } catch (JAXBException jaxbex) {
            logger.fatal(jaxbex);
            throw jaxbex;
        }
        return resultRecordGroup;
    }
}
