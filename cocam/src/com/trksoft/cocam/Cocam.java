/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



/**
 *
 * @author PSUANZES
 */
public class Cocam {
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(Cocam.class);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        logger.debug("Vamos Cocam!");
        
        SeasonManager seasonManager = new SeasonManager();
        TeamStatGroup teamStatGroup =  seasonManager.loadTeamStats();
        List<TeamStat> teamStatList = 
            new LinkedList<>(teamStatGroup.getTeamStat().values());
        Collections.sort(teamStatList, new TeamStatRoun1Comparator());
    }
    
}
