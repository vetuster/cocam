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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
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
        String[] fileList = getFileList();
        for (String fileName:fileList) {
            logger.debug("procesing" + StringUtil.enclose(fileName));
            
        try (BufferedReader mainBufferedReader =
                new BufferedReader(new FileReader(fileName));
                ) {
            String inputRecord;
            int recordCount = 0;
            boolean fileEND = false;
            while ((inputRecord = mainBufferedReader.readLine()) != null
                && !fileEND) {
                recordCount++;
                resultRecordList.add(ResultRecord.build(inputRecord, scrd));
            }
        } catch (IOException ioex) {
            logger.fatal(ioex);
            throw new CocamException(ioex);
        }
        }
        return resultRecordList;
    }
    
    
    private String[] getFileList() {
        File dir = new File(RESULT_PATH);
        //String[] fileList = dir.list();
        
        String[] fileList = dir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().startsWith(RESULT_FILE_PATTERN);
            }
        });
        logger.debug("fileList->length"+ StringUtil.enclose(fileList.length));
        return fileList;
    }
    
}