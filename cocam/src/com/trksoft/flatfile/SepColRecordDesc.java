/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.flatfile;

import com.trksoft.util.StringUtil;
import java.io.File;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
public class SepColRecordDesc {
     
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(SepColRecordDesc.class);
    
    @XmlAttribute
    private String charSep;
    
    @XmlElement(required = true)
    private final SortedSet<FieldDesc> fieldDesc;

    public SepColRecordDesc() {
        fieldDesc = new TreeSet<>();
    }

    public SortedSet<FieldDesc> getFieldDesc() {
        return fieldDesc;
    }

    public String getCharSep() {
        return charSep;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("charSep");
        sb.append(StringUtil.enclose(charSep));
        sb.append(fieldDesc.stream().map(Object::toString).
            collect(Collectors.joining("->")));
        return sb.toString();
    }

    
    public static SepColRecordDesc unmarshall(
        File sepColRecordDescFile) throws FlatFileException {
        SepColRecordDesc scrd = null;
        try {
            JAXBContext jaxbContext = 
                JAXBContext.newInstance(SepColRecordDesc.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            scrd = (SepColRecordDesc)
                jaxbUnmarshaller.unmarshal(sepColRecordDescFile);
        } catch (JAXBException jaxbex) {
            logger.fatal(jaxbex);
            throw new FlatFileException(jaxbex);
        }
        return scrd;
    }
}
