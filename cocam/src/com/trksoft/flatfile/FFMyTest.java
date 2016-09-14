/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.flatfile;

import java.io.File;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author jasuarez
 */
public class FFMyTest {
        
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger
        = LogManager.getLogger(FFMyTest.class);
    
    private static final String SAPFRA_FILENAME = 
        //"etc/a160403_132831-DTC.txt";
        "D:/clarcat/TC/FIJ/201608/30-Tareas_Post/a160803_095243-DTC.txt";
        //"D:/clarcat/TC/FIJ/201608/30-Tareas_Post/a160803_095243-ITC.txt";
    
    private static final String SAPFRA_RECORD_DESC_FILENAME = 
        "etc/tc-sap-dtc-record_desc.xml";
        //"etc/tc-sap-itc-record_desc.xml";
    
    private static final String SAPFRA_COL_SEP_FILENAME = 
        //"etc/tc-sap-dtc-col_sep.txt";
        "D:/clarcat/TC/FIJ/201608/30-Tareas_Post/a160803_095243-DTC-SEP.txt";
        //"D:/clarcat/TC/FIJ/201608/30-Tareas_Post/a160803_095243-ITC-SEP.txt";
    
    private static final String SEP_CHARS = ";";
    
    private static final String RECORD_LINE =
        "DTC00A160157784080420160027603136      0000000002               01CL:56442         0000000005827    15042016DCO:56725                                                            20480075       3000086302        CJA  501                0000000004815R7                                                            500000000001012R7100";
    
    public static void main(String[] args) throws FlatFileException {
        FFMyTest fFMyTest = new FFMyTest();
        fFMyTest.testFixedLengthCol2SepCol();
    }
     
    private void testRecordParse() throws FlatFileException {
        FixedLengthColRecordDesc fixedLengthColRecordDesc
            = FixedLengthColRecordDesc.unmarshall(new File(SAPFRA_RECORD_DESC_FILENAME));
        FlatFileManager fixedLengthColFile = new FlatFileManager();
        
        List recordToken = fixedLengthColFile.parseRecord(RECORD_LINE, 
            fixedLengthColRecordDesc);
    }
    
    private void testFixedLengthCol2SepCol() throws FlatFileException {
        FlatFileManager fixedLengthColFile = new FlatFileManager();
        
        File flcrdFile = new File(SAPFRA_RECORD_DESC_FILENAME);
        logger.debug("fixedLengthColRecordDesc("
            + flcrdFile.getAbsolutePath() + ")");
        FixedLengthColRecordDesc flcrd
            = FixedLengthColRecordDesc.unmarshall(flcrdFile);
                
        fixedLengthColFile.toSepColFile(new File(SAPFRA_FILENAME),
            flcrd, new File(SAPFRA_COL_SEP_FILENAME), SEP_CHARS);
    }
}
