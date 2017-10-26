/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
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
    "playerStat"
})
@XmlRootElement
public class PlayerStatEntity {
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(PlayerStatEntity.class);
    
    @XmlElement(required = true)
    private final List<PlayerStat> playerStat;

    public PlayerStatEntity() {
        playerStat = new LinkedList<>();
    }

    public List<PlayerStat> getPlayerStat() {
        return playerStat;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PlayerStatEntity->");
        sb.append(playerStat.stream().map(Object::toString).
            collect(Collectors.joining("->")));
        return sb.toString();
    }
    
    protected void marshall(File resultEntityFile) throws JAXBException {
        try {
            JAXBContext jaxbContext
                = JAXBContext.newInstance(PlayerStatEntity.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(this, resultEntityFile);
        } catch (JAXBException jaxbex) {
            logger.fatal(jaxbex);
            throw jaxbex;
        }
    }
    
    
    protected static PlayerStatEntity unmarshall(
        File resultEntityFile) throws JAXBException {
        PlayerStatEntity resultEntity = null;
        try {
            JAXBContext jaxbContext = 
                JAXBContext.newInstance(PlayerStatEntity.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            resultEntity = (PlayerStatEntity)
                jaxbUnmarshaller.unmarshal(resultEntityFile);
        } catch (JAXBException jaxbex) {
            logger.fatal(jaxbex);
            throw jaxbex;
        }
        return resultEntity;
    }
    
}
