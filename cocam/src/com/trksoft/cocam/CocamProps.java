/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

import com.trksoft.util.AbstractProps;
import java.util.Locale;
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
    private static final String CHAMPIONSHIP_EDITION_ID_KEY = "championship.edition.id";
    private static final String DATA_FILE_DIR_KEY = "data.file.dir";
    private static final String DATA_FILE_PREFIX_KEY = "data.file.prefix";
    private static final String RESULT_FILE_CHARSET_KEY = "result.file.charset";
    private static final String RESULT_FILE_DIR_KEY = "result.file.dir";
    private static final String RESULT_FILE_PREFIX_KEY = "result.file.prefix";
    private static final String RANKING_FILE_CHARSET_KEY = "ranking.file.charset";
    private static final String RANKING_FILE_CHARSEP_KEY = "ranking.file.charsep";
    private static final String LOCALE_LANGUAGE_DEFAULT = "locale.language.default";
    private static final String LOCALE_COUNTRY_DEFAULT = "locale.country.default";
    
    
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
    
    public String getChampionshipEditionId() {
        return getString(CHAMPIONSHIP_EDITION_ID_KEY);
    }
    
    public String getDataFilePrefix() {
        return getString(DATA_FILE_PREFIX_KEY);
    }
    
    public String getDataFileDir() {
        return getString(DATA_FILE_DIR_KEY);
    }
    
    public String getResultFileCharset() {
        return getString(RESULT_FILE_CHARSET_KEY);
    }
    
    public String getResultFileDir() {
        return getString(RESULT_FILE_DIR_KEY);
    }
    
    public String getResultFilePrefix() {
        return getString(RESULT_FILE_PREFIX_KEY);
    }
    
    public String getRankingFileCharset() {
        return getString(RANKING_FILE_CHARSET_KEY);
    }
    
    public String getRankingFileCharsep() {
        return getString(RANKING_FILE_CHARSEP_KEY);
    }
    
    public Locale getDefaultLocale() {
        String language = getString(LOCALE_LANGUAGE_DEFAULT);
        String country = getString(LOCALE_COUNTRY_DEFAULT);
        return new Locale(language,country);
    }
}
