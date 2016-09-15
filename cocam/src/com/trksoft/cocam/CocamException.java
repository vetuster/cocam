/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

/**
 *
 * @author jasuarez
 */
public class CocamException extends Exception {
    
    public CocamException() {
        super();
    }

    public CocamException(String message) {
        super(message);
    }

    public CocamException(Throwable cause) {
        super(cause);
    }

    public CocamException(String message, Throwable cause) {
        super(message, cause);
    }
}
