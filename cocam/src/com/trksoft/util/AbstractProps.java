/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author jasuarez
 */
public abstract class AbstractProps {
    @SuppressWarnings("NonConstantFieldWithUpperCaseName")
    private static final Logger logger =
        LogManager.getLogger(AbstractProps.class);
    
    private static final String PROPERTIES_FILE_ACCESS_ERROR =
        "PROPERTIES FILES ACCESS ERROR";
    private static final String KEY_NOT_FOUND = "KEY PROPERTY NOT FOUND";
    private static final String INVALID_FORMAT_PROPERTY =
        "INVALID FORMAT PROPERTY";
    
    private final Properties currentProperties = new Properties();

    
    
    protected AbstractProps() {
    }
    
    
    /**
     * Name of the container file properties.
     * @return name of the container file properties.
     */
    protected abstract String getPropertiesFileName();
    
    /**
     * Load current properties from properties file.
     */
    private void loadProperties() {
        InputStream istream = this.getClass().getClassLoader()
            .getResourceAsStream(getPropertiesFileName());
        try {
            currentProperties.load(istream);
        } catch (IOException ioex) {
            StringBuilder errmsg = 
                new StringBuilder(PROPERTIES_FILE_ACCESS_ERROR);
            errmsg.append(StringUtil.enclose(getPropertiesFileName()));
            logger.fatal(errmsg);
            throw new RuntimeException(errmsg.toString(), ioex);
        }
    }
    
    /**
     * Return the value for a key.
     * @param key key property.
     * @return the value for a key.
     */
    private String getValue(String key) {
        if (currentProperties.isEmpty()) {
            loadProperties();
        }
        //logger.debug("key searched(" + key + ")");
        //logger.debug("currentProperties" + currentProperties);
        if (!currentProperties.containsKey(key)) {
            StringBuilder errmsg = new StringBuilder(KEY_NOT_FOUND);
            errmsg.append(StringUtil.enclose(key));
            logger.fatal(errmsg);
            throw new RuntimeException(errmsg.toString());
        }
        return currentProperties.getProperty(key);
    }
    
    /**
     * Search the string property for the key.
     * @param key key property.
     * @return the string property for the key.
     */
    protected String getString(String key) {
        return getValue(key).trim();
    }
    
    /**
     * Search the integer number property for the key.
     * @param key key property.
     * @return the integer number property for the key.
     */
    protected int getInteger(String key) {
        int property = Integer.MIN_VALUE;
        try {
            property = Integer.parseInt(getValue(key));
        } catch (NumberFormatException nfex) {
            StringBuilder errmsg = new StringBuilder(INVALID_FORMAT_PROPERTY);
            errmsg.append(StringUtil.enclose(key));
            logger.fatal(errmsg);
            throw new RuntimeException(errmsg.toString(), nfex);
        }
        return property;
    }
}
