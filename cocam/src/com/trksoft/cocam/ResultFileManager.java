/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import com.trksoft.flatfile.FlatFileException;
import com.trksoft.flatfile.SepColRecordDesc;
import com.trksoft.util.StringUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author PSUANZES
 */
public class ResultFileManager {
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(ResultFileManager.class);
    
    private static final String SEPCOL_RECORD_DESC_PATH = 
        "resources/cocam-result_record-desc.xml";
     

    public List<ResultRecord> getResultRecord() throws CocamException {
        SepColRecordDesc scrd = null;
        try {
            scrd = SepColRecordDesc.unmarshall(
                new File(SEPCOL_RECORD_DESC_PATH));
        } catch (FlatFileException ffex) {
            logger.fatal(ffex);
            throw new CocamException(ffex);
        }
        List<ResultRecord> resultRecordList = new LinkedList<>();
        CocamProps comcaProps = CocamProps.getInstance();
        String charset = comcaProps.getResultFilesCharset();
        File[] fileList = getFileList();
        for (File inFile : fileList) {
            logger.info("procesing" + StringUtil.enclose(inFile.getName()));
            int recordCount = 0;
            try (BufferedReader mainBufferedReader
                //= new BufferedReader(new FileReader(inFile));)
                = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(inFile), charset));)
                        //new FileInputStream(inFile), "UTF-8"));)
            {
                String inputRecord;
                boolean hasContent = true;
                while ((inputRecord = mainBufferedReader.readLine()) != null
                    && hasContent) {
                    logger.trace("inputRecord"
                        + StringUtil.enclose(inputRecord));
                    recordCount++;
                    String recordType = inputRecord.substring(0, 3);
                    logger.trace("recordType" + StringUtil.enclose(recordType));
                    switch (recordType) {
                        case ResultRecord.INFO_TYPE: {
                            ResultRecord resultRecord = new ResultRecord();
                            resultRecordList.add(
                               resultRecord.build(inputRecord, scrd));
                            break;
                        }
                            
                        case ResultRecord.COMMENT_TYPE:
                            // nada, es un registro-comentario
                            break;
                            
                        case ResultRecord.END_TYPE:
                            hasContent = false;
                            break;
                            
                        default: {
                            String errText = "UNKNOWN RECORD TYPE"
                                + StringUtil.enclose(recordType);
                            logger.fatal(errText);
                            throw new RuntimeException(errText);
                        }
                    } //switch
                } //while
            } catch (IOException ioex) {
                logger.fatal(ioex);
                throw new CocamException(ioex);
            } // try
            logger.info("Procesado" + StringUtil.enclose(inFile.getName())
            + ",registros" + StringUtil.enclose(recordCount)
            + ",partidas" + StringUtil.enclose(resultRecordList.size()));
        } //for
        return resultRecordList;
    }
    
    
    private File[] getFileList() {
        CocamProps comcaProps = CocamProps.getInstance();
        File dir = new File(comcaProps.getResultFilesDir());
        
        String resultFilePattern = comcaProps.getResultFilePattern();
        File[] fileList = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith(resultFilePattern);
            }
        });
        logger.info("fileList->length"+ StringUtil.enclose(fileList.length));
        return fileList;
    }
    
}