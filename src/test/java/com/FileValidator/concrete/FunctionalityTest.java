package com.FileValidator.concrete;

import com.FileValidator.exceptions.InvalidFileException;
import org.junit.Test;

import java.io.File;

public class FunctionalityTest {

    @Test
    public void testJpgFunctionality() throws Exception, InvalidFileException{
        boolean validationResult = FileValidatorFactory.of(
                new File("src\\test\\test-data\\xxx.jpg")).validate();
        System.out.println(validationResult);
    }

    @Test
    public void testPdfFunctionality() throws Exception, InvalidFileException {
        boolean validationResult = FileValidatorFactory.of(
                new File("src\\test\\test-data\\xxxx.pdf")).validate();
        System.out.println(validationResult);
    }
}