package com.FileValidator.concrete;

import com.FileValidator.exceptions.InvalidFileException;
import com.FileValidator.interfaces.FileValidator;
import org.junit.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class FunctionalityTest {

    @Test
    public void testJpgFunctionality() throws Exception, InvalidFileException{
        boolean validationResult = FileValidatorDelegator.of(
                new File("src\\test\\test-data\\xxx.jpg")).validate();
        System.out.println(validationResult);
    }

    @Test
    public void testPdfFunctionality() throws Exception, InvalidFileException {
        boolean validationResult = FileValidatorDelegator.of(
                new File("src\\test\\test-data\\xxxx.pdf")).validate();
        System.out.println(validationResult);
    }
}