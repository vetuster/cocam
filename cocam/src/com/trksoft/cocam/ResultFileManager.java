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
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.JAXBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

/**
 *
 * @author PSUANZES
 */
public class ResultFileManager {
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(ResultFileManager.class);
    
    
    public List<ResultRecord> getResultRecord() throws CocamException {
        SepColRecordDesc scrd = null;
        try {
            scrd = SepColRecordDesc.unmarshall(
                new File(FileNameManager.getResultRecordDescFilename()));
        } catch (FlatFileException ffex) {
            logger.fatal(ffex);
            throw new CocamException(ffex);
        }
        List<ResultRecord> resultRecordList = new LinkedList<>();
        CocamProps comcaProps = CocamProps.getInstance();
        String charset = comcaProps.getResultFileCharset();
        File[] fileList = getResultFileList();
        for (File inFile : fileList) {
            logger.info("In progress" + StringUtil.enclose(inFile.getName()));
            ResultRecordGroup resultRecordGroup = new ResultRecordGroup();
            int recordCount = 0;
            try (BufferedReader bufferedReader
                //= new BufferedReader(new FileReader(inFile));)
                = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(inFile), charset));)
                        //new FileInputStream(inFile), "UTF-8"));)
            {
                String inputRecord;
                boolean hasContent = true;
                while ((inputRecord = bufferedReader.readLine()) != null
                    && hasContent) {
                    logger.trace("inputRecord"
                        + StringUtil.enclose(inputRecord));
                    recordCount++;
                    String recordType = inputRecord.substring(0, 3);
                    logger.trace("recordType" + StringUtil.enclose(recordType));
                    switch (recordType) {
                        case ResultRecord.INFO_TYPE: {
                            ResultRecord resultRecord = new ResultRecord();
                            resultRecord = resultRecord
                                .build(inputRecord, scrd);
                            if (resultRecordGroup.getDayId() == null) {
                                resultRecordGroup.setDayId(
                                    resultRecord.getDayId());
                            }
                            resultRecordGroup.getResultRecord()
                                .add(resultRecord);
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
            logger.info("Processed" + StringUtil.enclose(inFile.getName())
            + ",registros" + StringUtil.enclose(recordCount)
            + ",partidas" + StringUtil.enclose(
                resultRecordGroup.getResultRecord().size()));
            try {
                String resultRecordGroupFilename =
                    FileNameManager.getResultRecordGroupFilename(
                        resultRecordGroup.getDayId());
                resultRecordGroup.marshall(new File(resultRecordGroupFilename));
                
                // se valida contra schema la informacion obtenida y cargada
                // de los ficheros de entrada
                String resultRecordGroupSchemaFilename =
                    FileNameManager.getResultRecordGroupSchemaFilename();
                ResultRecordGroup.unmarshall(
                    new File(resultRecordGroupFilename),
                    new File(resultRecordGroupSchemaFilename));
            } catch (JAXBException | SAXException jaxbex) {
                logger.fatal(jaxbex);
                throw new CocamException(jaxbex);
            }

            // se incorpora la lista de la jornada a la lista global de registros
            resultRecordList.addAll(resultRecordGroup.getResultRecord());
            
        } //for ficheros de entrada con resultados
        return resultRecordList;
    }
    
    
    private File[] getResultFileList() {
        CocamProps comcaProps = CocamProps.getInstance();
        File dir = new File(comcaProps.getResultFileDir());
        
        String resultFilePattern = comcaProps.getResultFilePrefix();
        File[] fileList = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith(resultFilePattern);
            }
        });
        logger.info("fileList->length"+ StringUtil.enclose(fileList.length));
        return fileList;
    }
    
    
    public void setTeamRankingFile(final List<TeamStat> teamStatList,
        File rankingTeamFile) throws CocamException {
        CocamProps comcaProps = CocamProps.getInstance();
        String charset = comcaProps.getRankingFileCharset();
        String charsep = comcaProps.getRankingFileCharsep();

        try (PrintWriter printWriter = 
            new PrintWriter(rankingTeamFile, charset);) {

            teamStatList.stream().forEach((teamStat) -> {
                printWriter.println(teamStat.getRankingRecord(charsep));
            });
        } catch (FileNotFoundException | UnsupportedEncodingException fnfueex) {
            logger.fatal(fnfueex);
            throw new CocamException(fnfueex);
        }
    }
    
    public void setPlayerRankingFile(final List<PlayerStat> playerStatList,
        File playerRankingFile) throws CocamException {
        CocamProps comcaProps = CocamProps.getInstance();
        String charset = comcaProps.getRankingFileCharset();
        String charsep = comcaProps.getRankingFileCharsep();

        try (PrintWriter printWriter = 
            new PrintWriter(playerRankingFile, charset);) {

            playerStatList.stream().forEach((teamStat) -> {
                printWriter.println(teamStat.getRankingRecord(charsep));
            });
        } catch (FileNotFoundException | UnsupportedEncodingException fnfueex) {
            logger.fatal(fnfueex);
            throw new CocamException(fnfueex);
        }
    }
}
