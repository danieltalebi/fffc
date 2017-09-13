package com.octo.ffc.file.reader;

import com.octo.ffc.exceptions.ReaderException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProgressiveFileReader implements FileReader {

    public static final int CHUNK_SIZE = 10000;
    private LineIterator lineIterator;

    public ProgressiveFileReader(String filename) throws ReaderException {
        openFile(filename);
    }

    @Override
    public List<String> readLines() throws ReaderException {
        int counter = 0;
        List<String> linesRead = new ArrayList<>();
        while (lineIterator.hasNext() && counter < CHUNK_SIZE) {
            linesRead.add(lineIterator.next());
            counter++;
        }
        return linesRead;
    }

    @Override
    public boolean isNotEOF() {
        return lineIterator.hasNext();
    }

    private void openFile(String filename) throws ReaderException {
        try {
            lineIterator = FileUtils.lineIterator(new File(filename), "UTF-8");
        } catch (IOException e) {
            throw new ReaderException("Unable to open file " + filename, e);
        }
    }
}
