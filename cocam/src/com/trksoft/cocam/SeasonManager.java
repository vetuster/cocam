/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author PSUANZES
 */
public class SeasonManager {
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(SeasonManager.class);
    
    public static void main(String[] args) throws Exception {
        SeasonManager seasonManager = new SeasonManager();
        Season season = seasonManager.builFromFiles();
    }
    
    public Season builFromFiles() throws CocamException {
        Season season = new Season();
        ResultFileManager rfm = new ResultFileManager();
        List<ResultRecord> resultRecordList = rfm.getResultRecord();
        if (logger.isDebugEnabled()) {
            for (ResultRecord resultRecord : resultRecordList) {
                logger.debug(resultRecord);
            }
        }
        
        for (ResultRecord resultRecord : resultRecordList) {
            logger.info("procesing->" + resultRecord);
            
            if (season.getSeasonId() == null) {
                season.setSeasonId(resultRecord.getSeasonId());
            }
                
        }
        
        return season;
    }
}
