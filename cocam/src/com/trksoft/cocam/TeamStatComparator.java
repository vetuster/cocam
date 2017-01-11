/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import java.util.Comparator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author PSUANZES
 */
public class TeamStatComparator implements Comparator<TeamStat> {
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(TeamStatComparator.class);

    // por tipo de liga: REGular PEdro Menendez, CArreño Miranda
    // por puntos, descendente
    // por enfrentamiento directo, ciando existan ambos resultados
    // por coeficiente (ganadas), descendente (es < 1)
    //   solo tiene utilidad cuando hay distindo númeto de partidas disputadas
    //   es decir, al final de vuelta no decide, es inocuo
    // por orden alfabetico
    // si persiste el empate habra que ver que se hace (desempate)
    @Override
    public int compare(TeamStat oneTeamStat, TeamStat otherTeamStat) {

        /*
        logger.info("Comparing Team Stats("
            + oneTeamStat.getTeamStatKey()
            + ") vs ("
            + otherTeamStat.getTeamStatKey()
            + ")"
        );*/

        // por tipo de liga alfabetico
         int i = oneTeamStat.getLeagueType().compareTo(
             otherTeamStat.getLeagueType());
         if (i!=0) return i;
         
        // por puntos de mayor a menor
        i = otherTeamStat.getPoints().compareTo(oneTeamStat.getPoints());
        if (i!=0) return i;
        
        /*
        logger.info("EMPATAN A PUNTOS("
            + oneTeamStat.getPoints()
            + ")");*/
        
        // se busca el enfrentamineto directo, si existe
        if (oneTeamStat.getDirectMatch().
            containsKey(otherTeamStat.getTeamId())) {
            DirectMatch oneDirectMatch = oneTeamStat.getDirectMatch().get(
                otherTeamStat.getTeamId());
            // si existe en el primer equipo, debe existir en el segundo
            DirectMatch otherDirectMatch = otherTeamStat.getDirectMatch().get(
                oneTeamStat.getTeamId());
            
            //logger.info("Comparing Direct Macth");
            
            // solo tiene sentido si se ha jugado ida y vuelta
            // durante la primera vuelta es vacuo
            if (oneDirectMatch.hasPlayedAsLocal()
                && oneDirectMatch.hasPlayedAsVisiting()
                && otherDirectMatch.hasPlayedAsLocal() // redundante
                && otherDirectMatch.hasPlayedAsVisiting()) {  // redundante
                // ordenado por puntos de mayor a menor
                i = otherDirectMatch.getTotalPoints().compareTo(
                    oneDirectMatch.getTotalPoints());

                /*
                logger.info("otherDirectMatch->points("
                    + otherDirectMatch.getTotalPoints()
                    + ") vs oneDirectMatch->points("
                    + oneDirectMatch.getTotalPoints()
                    + ")=>RESULT-i-(" + i + ")"
                );*/

                if (i != 0) {
                    return i;
                }
            }
        }
        
        // por coeficiente (ganadas), descendente (es < 1)
        i = oneTeamStat.getWonCoefficient().compareTo(
            otherTeamStat.getWonCoefficient());
        if (i!=0) return i;
            
        // orden alfabetico de denominacionde de peña
        return oneTeamStat.getTeamDenom().compareTo(
            otherTeamStat.getTeamDenom());
    }
    
}
