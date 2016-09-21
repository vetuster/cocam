/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import java.io.File;
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
    "player"
})
@XmlRootElement
public class PlayerGroup {
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(PlayerGroup.class);
    
    @XmlElement(required = true)
    private final SortedSet<Player> player;

    public PlayerGroup() {
        player = new TreeSet<>();
    }

    public SortedSet<Player> getPlayer() {
        return player;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PlayerGroup->");
        sb.append(player.stream().map(Object::toString).
            collect(Collectors.joining("->")));
        return sb.toString();
    }
    
    public void marshall(File playerGroupFile) throws JAXBException {
        try {
            JAXBContext jaxbContext
                = JAXBContext.newInstance(PlayerGroup.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(this, playerGroupFile);
        } catch (JAXBException jaxbex) {
            logger.fatal(jaxbex);
            throw jaxbex;
        }
    }
    
    public static PlayerGroup unmarshall(
        File fixedLengthColRecordDescFile) throws JAXBException {
        PlayerGroup flcrd = null;
        try {
            JAXBContext jaxbContext = 
                JAXBContext.newInstance(PlayerGroup.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            flcrd = (PlayerGroup)
                jaxbUnmarshaller.unmarshal(fixedLengthColRecordDescFile);
        } catch (JAXBException jaxbex) {
            logger.fatal(jaxbex);
            throw jaxbex;
        }
        return flcrd;
    }
}
