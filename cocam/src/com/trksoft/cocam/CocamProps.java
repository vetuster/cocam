/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import com.trksoft.util.AbstractProps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author jasuarez
 */
public class CocamProps extends AbstractProps {
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger =
        LogManager.getLogger(CocamProps.class);
    
    private static final String PROPERTIES_FILE_NAME = "cocam.properties";
    private static final String RESULT_FILES_DIR_KEY = "result.files.dir";
    private static final String RESULT_FILE_PATTERN_KEY = "result.file.pattern";
    
    
    private CocamProps() {
    }
    
   //Bill Pugh Solution for singleton pattern
   private static class LazyHolder {
      private static final CocamProps INSTANCE = new CocamProps();
   }
    
   public static CocamProps getInstance() {
      return CocamProps.LazyHolder.INSTANCE;
   }
    
    
    @Override
    protected String getPropertiesFileName() {
        return PROPERTIES_FILE_NAME;
    }
    
    public String getResultFilesDir() {
        return getString(RESULT_FILES_DIR_KEY);
    }
    
    public String getResultFilePattern() {
        return getString(RESULT_FILE_PATTERN_KEY);
    }
    
}
