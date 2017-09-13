package com.octo.ffc.file.reader;

import com.octo.ffc.exceptions.ReaderException;
import org.apache.commons.lang3.Validate;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FullLengthFileReader implements FileReader {

    private final String fileName;

    public FullLengthFileReader(String fileName) {
        this.fileName = Validate.notBlank(fileName, "The File Name cannot be null");
    }

    @Override
    public List<String> readLines() throws ReaderException {
        List<String> linesRead = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new java.io.FileReader(fileName));
            for (String line; (line = br.readLine()) != null; ) {
                if (line.trim().length() > 0) {
                    linesRead.add(line);
                }
            }
        } catch (IOException e) {
            throw new ReaderException("Unable to read file " + fileName, e);
        }
        return linesRead;
    }

    @Override
    public boolean isNotEOF() {
        return true;
    }
}
