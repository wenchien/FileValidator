package com.FileValidator.concrete;

import com.FileValidator.exceptions.InvalidFileException;
import com.FileValidator.interfaces.FileValidator;
import com.FileValidator.util.StringUtil;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class FileValidatorFactory {

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
        return of(source);
    }

    public static FileValidator of(File source, String validatorPackagePrefix, String validatorClassSuffix) throws InvalidFileException, InstantiationException {
        return getExtensionAndValidator(source, null, null);
    }

    private static FileValidator getExtensionAndValidator(File source, String validatorPackagePrefix, String validatorClassSuffix) throws InvalidFileException, InstantiationException {
        String filename = source.getName();

        String extension = Optional.of(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1))
                .orElse("");

        if (StringUtil.isNullOrEmpty(extension)) {
            throw new InvalidFileException("Invalid File type");
        }

        FileValidator fv = getValidator(extension.toUpperCase(), source, validatorPackagePrefix, validatorClassSuffix);
        if (fv == null) {
            throw new InstantiationException("Failed to initializing FileValidator");
        }

        return fv;
    }

    private static FileValidator getValidator(String classPrefix, File source, String validatorPackagePrefix, String validatorClassSuffix) {
        try {
            if (StringUtil.isNullOrEmpty(validatorPackagePrefix)) {
                validatorPackagePrefix = FILE_VALIDATOR_PACKAGE;
            }

            if (StringUtil.isNullOrEmpty(validatorClassSuffix)) {
                validatorClassSuffix = FILE_VALIDATOR_SUFFIX;
            }

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
