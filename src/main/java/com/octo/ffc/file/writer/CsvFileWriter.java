package com.octo.ffc.file.writer;

import com.octo.ffc.exceptions.WriterException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.lang3.Validate;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class CsvFileWriter implements FileWriter {

    private static final int ONE_MB_BUFFER = 1048576;

    private final java.io.FileWriter fileWriter;
    private final BufferedWriter bufferedWriter;
    private final CSVPrinter csvPrinter;

    public CsvFileWriter(String fileName, char fieldSeparator, String lineSeparator) throws WriterException {
        Validate.notEmpty(lineSeparator, "The Line Separator cannot be null or empty");
        try {
            fileWriter = new java.io.FileWriter(fileName, false);
            bufferedWriter = new BufferedWriter(fileWriter, ONE_MB_BUFFER);
            CSVFormat csvFileFormat = CSVFormat.DEFAULT
                    .withDelimiter(fieldSeparator)
                    .withRecordSeparator(lineSeparator);
            csvPrinter = new CSVPrinter(fileWriter, csvFileFormat);
        } catch (IOException e) {
            throw new WriterException("There was a problem creating the writer for " + fileName, e);
        }

    }

    @Override
    public void write(String[] row) throws WriterException {
        try {
            csvPrinter.printRecord(row);
        } catch (IOException e) {
            throw new WriterException("Unable to write records in file", e);
        }
    }

    @Override
    public void write(List<String[]> rows) throws WriterException {
        System.out.println(LocalDateTime.now() + " Writing " + rows.size() + " rows");
        for (String[] row : rows) {
            write(row);
        }
        try {
            csvPrinter.flush();
            bufferedWriter.flush();
            fileWriter.flush();
        } catch (IOException e) {
            throw new WriterException("Unable to write records in file", e);
        }
    }


}
