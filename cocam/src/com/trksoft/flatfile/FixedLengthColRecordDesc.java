/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.flatfile;

import java.io.File;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
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
    "fieldDesc"
})
@XmlRootElement
public class FixedLengthColRecordDesc {
     
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(FixedLengthColRecordDesc.class);
    
    @XmlElement(required = true)
    private final SortedSet<FieldDesc> fieldDesc;

    public FixedLengthColRecordDesc() {
        fieldDesc = new TreeSet<>();
    }

    public SortedSet<FieldDesc> getFieldDesc() {
        return fieldDesc;
    }
    
    @Override
    public String toString() {
        return fieldDesc.stream().map(Object::toString).
            collect(Collectors.joining("->"));
    }
    
    public static FixedLengthColRecordDesc unmarshall(
        File fixedLengthColRecordDescFile) throws FlatFileException {
        FixedLengthColRecordDesc flcrd = null;
        try {
            JAXBContext jaxbContext = 
                JAXBContext.newInstance(FixedLengthColRecordDesc.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            flcrd = (FixedLengthColRecordDesc)
                jaxbUnmarshaller.unmarshal(fixedLengthColRecordDescFile);
        } catch (JAXBException jaxbex) {
            logger.fatal(jaxbex);
            throw new FlatFileException(jaxbex);
        }
        return flcrd;
    }
}
