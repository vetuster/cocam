package com.trksoft.cocam;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PSUANZES
 */
public class RankingFileManager {
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(RankingFileManager.class);
    
    
    public void writePlayerRankingFile(final List<Team> teamList,
        final List<PlayerStat> playerStatList, File playerRankingFile) {
        CocamProps comcaProps = CocamProps.getInstance();
        String charset = comcaProps.getRankingFileCharset();
        String charsep = comcaProps.getRankingFileCharsep();
        try (final PrintWriter printWriter = 
            new PrintWriter(playerRankingFile, charset)) {
            printWriter.println(PlayerStat.getRankingRecordHead(charsep));
            playerStatList.stream().forEach((PlayerStat playerStat) -> {
                // buscamos el equipo del jugador, para obtener el Denom
                Optional<Team> optional = teamList.stream().
                    filter(team -> team.getTeamId().equals(
                        playerStat.getPlayerStatPK().getTeamId())).findFirst();
                printWriter.println(playerStat.getRankingRecord(
                    optional.get().getTeamDenom(),charsep));
            });
        } catch (FileNotFoundException | UnsupportedEncodingException fnfueex) {
            logger.fatal(fnfueex);
            throw new RuntimeException(fnfueex);
        }
    }

    public void writeTeamRankingFile(final List<TeamStat> teamStatList,
        File rankingTeamFile){
        CocamProps comcaProps = CocamProps.getInstance();
        String charset = comcaProps.getRankingFileCharset();
        String charsep = comcaProps.getRankingFileCharsep();
        try (final PrintWriter printWriter = 
            new PrintWriter(rankingTeamFile, charset)) {
            printWriter.println(TeamStat.getRankingRecordHead(charsep));
            teamStatList.stream().forEach((TeamStat teamStat) -> {
                printWriter.println(teamStat.getRankingRecord(charsep));
            });
        } catch (FileNotFoundException | UnsupportedEncodingException fnfueex) {
            logger.fatal(fnfueex);
            throw new RuntimeException(fnfueex);
        }
    }
}
