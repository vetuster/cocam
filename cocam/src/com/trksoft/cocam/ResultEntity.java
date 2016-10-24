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
    "result"
})
@XmlRootElement
public class ResultEntity {
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(ResultEntity.class);
    
    @XmlElement(required = true)
    private final List<Result> result;

    public ResultEntity() {
        result = new LinkedList<>();
    }

    public List<Result> getResult() {
        return result;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ResultEntity->");
        sb.append(result.stream().map(Object::toString).
            collect(Collectors.joining("->")));
        return sb.toString();
    }
    
    protected void marshall(File resultEntityFile) throws JAXBException {
        try {
            JAXBContext jaxbContext
                = JAXBContext.newInstance(ResultEntity.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(this, resultEntityFile);
        } catch (JAXBException jaxbex) {
            logger.fatal(jaxbex);
            throw jaxbex;
        }
    }
    
    protected void marshall(File resultEntityFile, File resultEntitySchema)
        throws JAXBException, SAXException {
        try {
            JAXBContext jaxbContext
                = JAXBContext.newInstance(ResultEntity.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            SchemaFactory schemaFactory = SchemaFactory.newInstance(
                XMLConstants.W3C_XML_SCHEMA_NS_URI); 
            Schema schema = schemaFactory.newSchema(resultEntitySchema); 
            jaxbMarshaller.setSchema(schema);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(this, resultEntityFile);
        } catch (JAXBException jaxbex) {
            logger.fatal(jaxbex);
            throw jaxbex;
        }
    }
    
    protected static ResultEntity unmarshall(
        File resultEntityFile) throws JAXBException {
        ResultEntity resultEntity = null;
        try {
            JAXBContext jaxbContext = 
                JAXBContext.newInstance(ResultEntity.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            resultEntity = (ResultEntity)
                jaxbUnmarshaller.unmarshal(resultEntityFile);
        } catch (JAXBException jaxbex) {
            logger.fatal(jaxbex);
            throw jaxbex;
        }
        return resultEntity;
    }
    
    protected static ResultEntity unmarshall(File resultEntityFile,
        File resultEntitySchema) throws JAXBException, SAXException {
        ResultEntity resultEntity = null;
        try {
            JAXBContext jaxbContext = 
                JAXBContext.newInstance(ResultEntity.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            SchemaFactory schemaFactory = SchemaFactory.newInstance(
                XMLConstants.W3C_XML_SCHEMA_NS_URI); 
            Schema schema = schemaFactory.newSchema(resultEntitySchema); 
            jaxbUnmarshaller.setSchema(schema);
            resultEntity = (ResultEntity)
                jaxbUnmarshaller.unmarshal(resultEntityFile);
        } catch (JAXBException | SAXException jaxbex) {
            logger.fatal(jaxbex);
            throw jaxbex;
        }
        return resultEntity;
    }
}
