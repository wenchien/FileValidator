package com.FileValidator.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtil {

    private FileUtil() {

    }

    public static byte[] readFileAsBytes(File source) throws IOException {
        return Files.readAllBytes(source.toPath());
    }

}
