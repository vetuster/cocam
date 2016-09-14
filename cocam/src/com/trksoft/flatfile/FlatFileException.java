/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.flatfile;

/**
 *
 * @author jasuarez
 */
public class FlatFileException extends Exception {
    
    public FlatFileException() {
        super();
    }

    public FlatFileException(String message) {
        super(message);
    }

    public FlatFileException(Throwable cause) {
        super(cause);
    }

    public FlatFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
