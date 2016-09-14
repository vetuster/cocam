/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.flatfile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author jasuarez
 */
public class FlatFileManager {
    
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(FlatFileManager.class);
    
    
    public void toSepColFile(File fixedLengthColIn,
        FixedLengthColRecordDesc flcrd, File SepColOut, String sepChars)
        throws FlatFileException {
        long lastMillis = System.currentTimeMillis();
        logger.debug("toSepColFile->START");
        logger.debug("fixedLengthCol(" 
            + fixedLengthColIn.getAbsolutePath() + ")");
        logger.debug("sepCol(" + SepColOut.getAbsolutePath() + ")");
        logger.debug("sepChars(" + sepChars + ")");
        
        int recordCount = 0;
        try (BufferedReader mainBufferedReader =
                new BufferedReader(new FileReader(fixedLengthColIn));
             BufferedWriter bufferedWriter =
                 new BufferedWriter(new FileWriter(SepColOut));
            ) {
            String inputRecord;
            while ((inputRecord = mainBufferedReader.readLine()) != null) {
                recordCount++;
                if ((recordCount % 10000) == 0) {
                    logger.debug("recordCount(" + recordCount + ")...");
                }
                List<String> recordToken = parseRecord(inputRecord, flcrd);
                String outputRecord = recordToken.stream().
                    map(str -> str.trim()).
                    collect(Collectors.joining(sepChars));
                bufferedWriter.write(outputRecord);
                bufferedWriter.newLine();
            }
        } catch (IOException ioex) {
            logger.error(ioex);
        }
        logger.debug("recordCount(" + recordCount + ") FINAL");
        logger.debug("toSepColFile->END("
            + (System.currentTimeMillis() - lastMillis)
            + ")");
    }
    
    
    public List<String> parseRecord(String record,
        FixedLengthColRecordDesc flcrd) {
        List<String> recordToken = new LinkedList<>();

        int startIndex = 0;
        for (FieldDesc fieldDesc : flcrd.getFieldDesc()) {
            String fieldContent = record.substring(startIndex,
                startIndex + fieldDesc.getFieldLength());
//            logger.debug("fieldDesc->" + fieldDesc);
//            logger.debug("startIndex(" + startIndex
//                + "),fieldContent(" + fieldContent + ")");
            recordToken.add(fieldContent);
            startIndex += fieldDesc.getFieldLength();
        }
        return recordToken;
    }
}
