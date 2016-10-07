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

    // alfabetico por tipo de liga: REGular PEdro Menendez, CArreño Miranda
    // los que NO HANJUGADO al final
    // los NO PRESENTADOS al final, antes de anteriores
    // por ganadas de mayor a menor
    // coeficiente de mayor a menor
    // partidas jugadas de menor a mayor
    // chico-average de mayor a menor (se suprime 27/9)
    // apodo por orden alfabetico
    @Override
    public int compare(PlayerStat onePlayerStat, PlayerStat otherPlayerStat) {
        
        // alfabetico por tipo de liga: REGular PEdro Menendez, CArreño Miranda
        int i = onePlayerStat.getPlayerStatPK().getLeagueType().compareTo(
            otherPlayerStat.getPlayerStatPK().getLeagueType());
        if (i != 0) return i;
        
        // los que NO HAN JUGADO al final, mesas jugadas descendente
        i = otherPlayerStat.hasPlayed().compareTo(onePlayerStat.hasPlayed());
        if (i != 0) return i;
        
        // los NO PRESENTADOS al final
        i = Player.isWO(onePlayerStat.getPlayerStatPK().getPlayerNick())
            .compareTo(
            Player.isWO(otherPlayerStat.getPlayerStatPK().getPlayerNick()));
        if (i != 0) return i;
    
        // ganadas de mayor a menor
        i = otherPlayerStat.getTableWon().compareTo(
            onePlayerStat.getTableWon());
        if (i != 0) return i;
        
        // coeficiente de ganadas de mayor (mas proximo a 100%) a menor
        i = otherPlayerStat.getWonCoefficient().compareTo(
            onePlayerStat.getWonCoefficient());
        if (i != 0) return i;
        
        // partidas jugadas de menor a mayor
        i = onePlayerStat.getTablePlayed().compareTo(
            otherPlayerStat.getTablePlayed());
        if (i != 0) return i;
        
        // chico-average de mayor a menor
        //i = otherPlayerStat.getGoalsCoefficient().compareTo(
        //    onePlayerStat.getGoalsCoefficient());
        //if (i!=0) return i;
        
        // orden alfabetico de apodo
        return onePlayerStat.getPlayerStatPK().getPlayerNick().compareTo(
            otherPlayerStat.getPlayerStatPK().getPlayerNick());
    }
    
}
