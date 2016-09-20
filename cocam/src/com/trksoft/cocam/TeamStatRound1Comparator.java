/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import java.util.Comparator;

/**
 *
 * @author PSUANZES
 */
public class TeamStatRound1Comparator implements Comparator<TeamStat> {

    @Override
    public int compare(TeamStat one, TeamStat other) {
        // de mayor a menor
        int i = other.getPoints().compareTo(one.getPoints());
        if (i!=0) return i;
        return one.getTeamDenom().compareTo(other.getTeamDenom());
    }
    
}
