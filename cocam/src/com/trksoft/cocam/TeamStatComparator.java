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
public class TeamStatComparator implements Comparator<TeamStat> {

    // por puntos
    // por enfrentamiento directo
    // por orden alfabetico
    // si persiste el empate habra que ver que se hace (desempate)
    @Override
    public int compare(TeamStat oneTeamStat, TeamStat otherTeamStat) {
        // porpuntos de mayor a menor
        int i = otherTeamStat.getPoints().compareTo(oneTeamStat.getPoints());
        if (i!=0) return i;
        
        // se busca el enfrentamineto directo, si existe
        if (oneTeamStat.getDirectMatch().
            containsKey(otherTeamStat.getTeamId())) {
            DirectMatch oneDirectMatch = oneTeamStat.getDirectMatch().get(
                otherTeamStat.getTeamId());
            // si existe en el primer equipo, debe existir en el segundo
            DirectMatch otherDirectMatch = otherTeamStat.getDirectMatch().get(
                oneTeamStat.getTeamId());
            // solo tiene sentido si se ha jugado ida y vuelta
            // durante la primera vuelta es vacuo
            if (oneDirectMatch.hasPlayedAsLocal()
                && oneDirectMatch.hasPlayedAsVisiting()
                && otherDirectMatch.hasPlayedAsLocal()        // redundante
                && otherDirectMatch.hasPlayedAsVisiting()) {  // redundante
                // ordenado por puntos de mayor a menor
                i = otherDirectMatch.getTotalPoints().compareTo(
                    oneDirectMatch.getTotalPoints());
                if (i != 0) {
                    return i;
                }
            }
        }
        
        // orden alfabetico de denominacionde de peña
        return oneTeamStat.getTeamDenom().compareTo(
            otherTeamStat.getTeamDenom());
    }
    
}
