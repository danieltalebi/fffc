package com.octo.ffc;

import com.octo.ffc.file.reader.FakeFileReaderImpl;
import com.octo.ffc.file.reader.FileReader;
import com.octo.ffc.file.reader.OnDemandFileReader;
import com.octo.ffc.file.writer.CsvFileWriter;
import com.octo.ffc.file.writer.FileWriter;
import com.octo.ffc.layouts.FileLayout;
import com.octo.ffc.layouts.FileLayoutCreator;
import org.apache.commons.lang3.time.StopWatch;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransformerController {


    public void transformToCsv(String metadataFileName, String dataFileName, String destinationFileName) throws Exception {
        StopWatch stopWatch = StopWatch.createStarted();
        FileReader dataFileReader = new OnDemandFileReader(dataFileName);

        FileWriter csvFileWriter = new CsvFileWriter(destinationFileName, ';', "\n");
        FileLayoutCreator fileLayoutCreator = new FileLayoutCreator();
        FileLayout fileLayout = fileLayoutCreator.createFileLayout(metadataFileName);
        List<String> rawData;
        List<String[]> rows;
        csvFileWriter.write(fileLayout.getColumnHeaders());
        while (true) {
            rawData = dataFileReader.readLines();
            if (rawData.size() == 0) {
                System.out.println(LocalDateTime.now() + " End of data file reached");
                break;
            }
            rows = convertToRows(rawData, fileLayout);
            rawData.clear();
            csvFileWriter.write(rows);
            rows.clear();

        }
        stopWatch.stop();
        System.out.println(LocalDateTime.now() + " Transformation finished on " + stopWatch.getTime()/1000f + " seconds");

    }

    private List<String[]> convertToRows(List<String> readLines, FileLayout fileLayout) {
        List<String[]> convertedCells = new ArrayList<>();
        for (String line : readLines) {
            convertedCells.add(fileLayout.convertToRow(line));
        }
        return convertedCells;
    }

    public static void main(String[] args) throws Exception {
        TransformerController transformerController = new TransformerController();
        transformerController.transformToCsv("metadata.txt", "data.txt", "output.csv");
    }
}
