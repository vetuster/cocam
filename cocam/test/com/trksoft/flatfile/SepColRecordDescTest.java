/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.flatfile;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author PSUANZES
 */
public class SepColRecordDescTest {
    private static final String SEPCOL_RECORD_DESC_FILENAME = 
        "resources/cocam-result_record-desc.xml";
        
    public SepColRecordDescTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of unmarshall method, of class SepColRecordDesc.
     */
    @Test
    public void testUnmarshall() throws Exception {
        System.out.println("unmarshall");
        File sepColRecordDescFile = new File(SEPCOL_RECORD_DESC_FILENAME);
        SepColRecordDesc sprd = 
            SepColRecordDesc.unmarshall(sepColRecordDescFile);
        /*
        SepColRecordDesc expResult = null;
        SepColRecordDesc result = SepColRecordDesc.unmarshall(sepColRecordDescFile);
        assertEquals(expResult, result);*/
        // TODO review the generated test code and remove the default call to fail.
        System.out.println(sprd.toString());
        
        JAXBContext jaxbContext
                = JAXBContext.newInstance(SepColRecordDesc.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
            true);
            
        jaxbMarshaller.marshal(sprd, System.out);
    }
    
}
