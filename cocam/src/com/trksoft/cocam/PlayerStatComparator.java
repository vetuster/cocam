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
public class PlayerStatComparator implements Comparator<PlayerStat> {

    // por ganadas de mayor a menor
    // coeficiente de mayor a menor
    // chico-average de mayor a menor
    // apodo por orden alfabetico
    @Override
    public int compare(PlayerStat onePlayerStat, PlayerStat otherPlayerStat) {
        // ganadas de mayor a menor
        int i = otherPlayerStat.getTableWon().compareTo(
            onePlayerStat.getTableWon());
        if (i!=0) return i;
        
        // coeficiente de ganadas de mayor (mas proximo a 100%) a menor
        i = otherPlayerStat.getWonCoefficient().compareTo(
            onePlayerStat.getWonCoefficient());
        if (i!=0) return i;
        
        // chico-average de mayor a menor
        i = otherPlayerStat.getGoalsCoefficient().compareTo(
            onePlayerStat.getGoalsCoefficient());
        if (i!=0) return i;
        
        // orden alfabetico de apodo
        return onePlayerStat.getPlayerNick().compareTo(
            otherPlayerStat.getPlayerNick());
    }
    
}
