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
import java.util.Arrays;
import javax.xml.bind.JAXBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

/**
 *
 * @author PSUANZES
 */
public class ResultRecordFileManager {
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(ResultRecordFileManager.class);
    
    //Bill Pugh Solution for singleton pattern
    protected ResultRecordFileManager() {
    }
    
    protected void loadXmlResultRecordEntityFile() throws CocamException {
        ResultRecordEntity resultRecordEntity = loadResultRecordEntityFile();
        
        // se genera el fichero xml y se valida contra xsd
        try {
            String resultRecordEntityFilename
                = FileNameManager.getResultRecordEntityFilename();
            String resultRecordEntitySchemaFilename
                = FileNameManager.getResultRecordEntitySchemaFilename();
            //resultEntity.marshall(new File("resources/TRK-ResultRecordEntity.xml"));
            resultRecordEntity.marshall(new File(resultRecordEntityFilename),
                new File(resultRecordEntitySchemaFilename));
        } catch (JAXBException | SAXException jaxbex) {
            logger.fatal(jaxbex);
            throw new CocamException(jaxbex);
        }
    }
    
    
    private ResultRecordEntity loadResultRecordEntityFile() 
        throws CocamException {
        SepColRecordDesc scrd = null;
        try {
            scrd = SepColRecordDesc.unmarshall(
                new File(FileNameManager.getResultRecordDescFilename()));
        } catch (FlatFileException ffex) {
            logger.fatal(ffex);
            throw new CocamException(ffex);
        }
        CocamProps comcaProps = CocamProps.getInstance();
        String charset = comcaProps.getResultFileCharset();
        File[] fileList = getResultRecordFileList();
        ResultRecordEntity resultRecordEntity = new ResultRecordEntity();
        // ordenar lor archivos, para tratar por orden de jornada
        Arrays.sort(fileList);
        for (File inFile : fileList) {
            logger.info("In progress" + StringUtil.enclose(inFile.getName()));
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
                ResultRecord lastHeadResultRecord = null;
                while ((inputRecord = bufferedReader.readLine()) != null
                    && hasContent) {
                    logger.trace("inputRecord"
                        + StringUtil.enclose(inputRecord));
                    recordCount++;
                    String recordType = inputRecord.substring(0, 3);
                    logger.trace("recordType" + StringUtil.enclose(recordType));
                    switch (recordType) {
                        case ResultRecord.INFO_TYPE: {
                            ResultRecord resultRecord
                                = ResultRecord.parse(inputRecord, scrd);

                            if (resultRecord.isMatchHead()) {
                                lastHeadResultRecord = resultRecord;
                            } else {
                                // copiar las propiedades de cabecera, no vienen
                                // en el XML, solo en los registros HEAD
                                resultRecord.setSeasonId(
                                    lastHeadResultRecord.getSeasonId());
                                resultRecord.setSeasonDayId(
                                    lastHeadResultRecord.getSeasonDayId());
                                resultRecord.setSeasonDayDate(
                                    lastHeadResultRecord.getSeasonDayDate());
                                resultRecord.setLocalTeamName(
                                    lastHeadResultRecord.getLocalTeamName());
                                resultRecord.setLocalTeamScore(
                                    lastHeadResultRecord.getLocalTeamScore());
                                resultRecord.setVisitingTeamName(
                                    lastHeadResultRecord.getVisitingTeamName());
                                resultRecord.setVisitingTeamScore(
                                    lastHeadResultRecord.getVisitingTeamScore());
                            }
                            resultRecordEntity.getResultRecord().
                                add(resultRecord);

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
                logger.fatal("Procesing"
                    + StringUtil.enclose(inFile.getName()));
                logger.fatal(ioex);
                throw new CocamException(ioex);
            } // try
            logger.info("Processed" + StringUtil.enclose(inFile.getName())
            + ",registros" + StringUtil.enclose(recordCount)
            + ",partidas"
            + StringUtil.enclose(resultRecordEntity.getResultRecord().size()));
        } //for ficheros de entrada con resultados
        
        return resultRecordEntity;
    }
    
    
    
    private File[] getResultRecordFileList() {
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
}
