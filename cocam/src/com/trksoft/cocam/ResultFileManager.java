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
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
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
    
    private static final String SEPCOL_RECORD_DESC_FILENAME = 
        "resources/cocam-result_record-desc.xml";
     
    private static final String RESULT_PATH = 
        "resources/";
    
    private static final String RESULT_FILE_PATTERN = 
        "cocam-2016_17-result";


    public static void main(String[] args) throws Exception {
        ResultFileManager rfm = new ResultFileManager();
        List<ResultRecord> resultRecordList = rfm.getResultRecord();
    }
    

    public List<ResultRecord> getResultRecord() throws CocamException {
        logger.debug("getResultRecord");
        SepColRecordDesc scrd = null;
        try {
            scrd = SepColRecordDesc.unmarshall(
            new File(SEPCOL_RECORD_DESC_FILENAME));
        } catch (FlatFileException ffex) {
            logger.fatal(ffex);
            throw new CocamException(ffex);
        }
        List<ResultRecord> resultRecordList = new LinkedList();
        File[] fileList = getFileList();
        for (File inFile : fileList) {
            logger.debug("procesing" + StringUtil.enclose(inFile.getName()));

            try (BufferedReader mainBufferedReader
                = new BufferedReader(new FileReader(inFile));)
                /*= new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(inFile), "UTF-8"));)*/
            {
                String inputRecord;
                int recordCount = 0;
                boolean hasContent = true;
                while ((inputRecord = mainBufferedReader.readLine()) != null
                    && hasContent) {
                    logger.debug("inputRecord"
                        + StringUtil.enclose(inputRecord));
                    recordCount++;
                    String recordType = inputRecord.substring(0, 3);
                    //logger.debug("recordType" + StringUtil.enclose(recordType));
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
                            throw new CocamException(errText);
                        }
                    } //switch
                } //while
            } catch (IOException ioex) {
                logger.fatal(ioex);
                throw new CocamException(ioex);
            }
        } //for
        for (ResultRecord resultRecord : resultRecordList) {
           logger.debug(resultRecord); 
        }
        return resultRecordList;
    }
    
    
    private File[] getFileList() {
        File dir = new File(RESULT_PATH);
        //String[] fileList = dir.list();
        
        File[] fileList = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().startsWith(RESULT_FILE_PATTERN);
            }
        });
        logger.debug("fileList->length"+ StringUtil.enclose(fileList.length));
        return fileList;
    }
    
}