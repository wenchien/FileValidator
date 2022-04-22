package com.FileValidator.concrete;

import com.FileValidator.interfaces.FileValidator;
import com.FileValidator.util.FileUtil;

import java.io.File;
import java.io.IOException;

public class JPGFileValidator implements FileValidator {

    private File source;

    private static final int[] JPG_HEADER_BYTES = {0xFF, 0xD8};

    private static final int[] JPG_EOI_BYTES = {0xFF, 0xD9};

    public JPGFileValidator(File source) {
        this.source = source;
    }

    @Override
    public boolean validate() throws IOException {
        boolean isOk = false;

        if (source != null) {
            int[] fileBytes = convertToIntArray(FileUtil.readFileAsBytes(source));
            
            for(int i = 0; i < JPG_HEADER_BYTES.length; ++i) {
                if (fileBytes[i] != JPG_HEADER_BYTES[i] || fileBytes[fileBytes.length - JPG_EOI_BYTES.length + i] != JPG_EOI_BYTES[i]) {
                    break;
                }
            }
            isOk = true;
        }

        return isOk;
    }

    @Override
    public boolean swapSource(File source) {
        this.source = source;
        return true;
    }

    private int[] convertToIntArray(byte[] source) {

        int[] dest = new int[source.length];
        for(int i = 0 ; i < source.length; ++i) {
            dest[i] = Byte.toUnsignedInt(source[i]);
        }

        return dest;
    }
}
