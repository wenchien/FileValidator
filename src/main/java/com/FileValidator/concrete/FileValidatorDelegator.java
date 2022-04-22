package com.FileValidator.concrete;

import com.FileValidator.exceptions.InvalidFileException;
import com.FileValidator.interfaces.FileValidator;
import com.FileValidator.util.StringUtil;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class FileValidatorDelegator {

    private static final String FILE_VALIDATOR_SUFFIX = "FileValidator";

    private static final String FILE_VALIDATOR_PACKAGE = "com.FileValidator.concrete.";

    /***
     * Call this function first. Then call validate to start file validation process.
     * @param source file to be validated. You only need to provide the File object with its full file path
     * @return returns corresponding FileValidator for the file type provided by the source file.
     * @throws InvalidFileException
     * @throws InstantiationException
     */
    public static FileValidator of(File source) throws InvalidFileException, InstantiationException {
        return delegateValidator(source);
    }

    private static FileValidator delegateValidator(File source) throws InvalidFileException, InstantiationException {
        String filename = source.getName();

        String extension = Optional.of(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1))
                .orElse("");

        if (StringUtil.isNullOrEmpty(extension)) {
            throw new InvalidFileException("Invalid File type");
        }

        FileValidator fv = delegate(extension.toUpperCase(), source);
        if (fv == null) {
            throw new InstantiationException("Failed to initializing FileValidator");
        }

        return fv;
    }

    private static FileValidator delegate(String classPrefix, File source) {
        try {
            Class clazz = Class.forName(FILE_VALIDATOR_PACKAGE + classPrefix + FILE_VALIDATOR_SUFFIX);

            Class[] parameterTypes = new Class[1];
            parameterTypes[0] = File.class;

            return (FileValidator) clazz.getDeclaredConstructor(parameterTypes).newInstance(source);
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch(InstantiationException  e) {
            e.printStackTrace();
        } catch(IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }
}
