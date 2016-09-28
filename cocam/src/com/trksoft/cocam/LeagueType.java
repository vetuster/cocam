/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author PSUANZES
 */
@XmlType(name = "LeagueType")
@XmlEnum
public enum LeagueType {
    REG("REGULAR"),
    PEM("PEDRO MENÉNDEZ"),
    CAM("CARREÑO MIRANDA");
    
    private final String leagueName;

    LeagueType(String leagueName) {
        this.leagueName = leagueName;
    }

}
