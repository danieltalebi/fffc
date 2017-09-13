package com.octo.ffc;

import com.octo.ffc.exceptions.ReaderException;
import com.octo.ffc.exceptions.WriterException;
import com.octo.ffc.file.reader.FakeFileReaderImpl;
import com.octo.ffc.file.reader.FileReader;
import com.octo.ffc.file.reader.ProgressiveFileReader;
import com.octo.ffc.file.writer.CsvFileWriter;
import com.octo.ffc.file.writer.FileWriter;
import com.octo.ffc.layouts.FileLayout;
import com.octo.ffc.layouts.FileLayoutCreator;
import org.apache.commons.lang3.time.StopWatch;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransformerController {


    private final FileLayoutCreator fileLayoutCreator;

    public TransformerController() {
        fileLayoutCreator = new FileLayoutCreator();
    }

    public void transformToCsv(String metadataFileName, String dataFileName, String destinationFileName, char fieldSeparator, String lineSeparator) throws Exception {
        StopWatch stopWatch = StopWatch.createStarted();
        FileReader dataFileReader = new ProgressiveFileReader(dataFileName);
        FileWriter csvFileWriter = new CsvFileWriter(destinationFileName, fieldSeparator, lineSeparator);

        FileLayout fileLayout = fileLayoutCreator.createFileLayout(metadataFileName);
        writeHeaders(csvFileWriter, fileLayout);
        writeBody(dataFileReader, csvFileWriter, fileLayout);
        stopWatch.stop();
        System.out.println(LocalDateTime.now() + " Transformation finished on " + stopWatch.getTime()/1000f + " seconds");

    }

    private void writeHeaders(FileWriter csvFileWriter, FileLayout fileLayout) throws WriterException {
        csvFileWriter.write(fileLayout.getColumnHeaders());
    }

    private void writeBody(FileReader dataFileReader, FileWriter csvFileWriter, FileLayout fileLayout) throws ReaderException, WriterException {
        List<String> rawData;
        List<String[]> rows;
        while (dataFileReader.isNotEOF()) {
            rawData = dataFileReader.readLines();
            rows = convertToRows(rawData, fileLayout);
            csvFileWriter.write(rows);
        }
    }

    private List<String[]> convertToRows(List<String> readLines, FileLayout fileLayout) {
        List<String[]> convertedCells = new ArrayList<>();
        for (String line : readLines) {
            convertedCells.add(fileLayout.convertToRow(line));
        }
        return convertedCells;
    }

}
