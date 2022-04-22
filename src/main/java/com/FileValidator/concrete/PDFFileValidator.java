package com.FileValidator.concrete;

import com.FileValidator.exceptions.InvalidFileException;
import com.FileValidator.interfaces.FileValidator;
import com.FileValidator.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.function.Consumer;
import java.util.stream.Stream;


public class PDFFileValidator implements FileValidator {

    private File source;

    private static final byte[] PDF_HEADER_BYTES = {0x25, 0x50, 0x44, 0x46};

    public PDFFileValidator(File source) {
        this.source = source;
    }

    /***
     * Call this function after you have called FileValidatorDelegator.of(File)
     * @return true if provided source file is a valid file type of its own extension, false otherwise
     * @throws IOException
     */
    @Override
    public boolean validate() throws IOException {
        boolean isOk = false;

        if (source != null) {
            byte[] fileBytes = FileUtil.readFileAsBytes(source);
            for(int i = 0; i < PDF_HEADER_BYTES.length; ++i) {
                if (fileBytes[i] != PDF_HEADER_BYTES[i]) {
                    break;
                }
            }
            isOk = true;
        }

        return isOk;
    }

    /***
     *
     * @param source new File to validate
     * @return always true
     */
    public boolean swapSource(File source) {
        this.source = source;
        return true;
    }

}
