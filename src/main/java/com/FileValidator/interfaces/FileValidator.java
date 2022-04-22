package com.FileValidator.interfaces;

import java.io.File;
import java.io.IOException;

public interface FileValidator {

    public boolean validate() throws IOException;

    public boolean swapSource(File source);

}
