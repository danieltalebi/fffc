package com.octo.ffc.layouts;

import com.octo.ffc.columns.AbstractColumn;
import com.octo.ffc.exceptions.ParserException;
import com.octo.ffc.exceptions.ReaderException;
import com.octo.ffc.file.reader.FileReader;
import com.octo.ffc.file.reader.FullLengthFileReader;
import com.octo.ffc.metadata.MetadataParser;

import java.util.List;

import static org.apache.commons.lang3.Validate.notBlank;

public class FileLayoutCreator {

    private MetadataParser metadataParser;

    public FileLayoutCreator() {
        metadataParser = new MetadataParser();
    }

    public FileLayout createFileLayout(String metadataFileName) throws Exception {
        List<String> metadata = getMetadata(metadataFileName);
        List<AbstractColumn> columns = convertMetadataToColumns(metadata);
        return createFileLayout(columns);
    }

    private List<String> getMetadata(String metadataFileName) throws ReaderException {
        notBlank(metadataFileName, "The Metadata file cannot be null or empty");
        FileReader metadataFileReader = new FullLengthFileReader(metadataFileName);
        return metadataFileReader.readLines();
    }

    private List<AbstractColumn> convertMetadataToColumns(List<String> metadata) throws ParserException {
        return metadataParser.parse(metadata);
    }

    private FileLayout createFileLayout(List<AbstractColumn> columns) {
        FileLayout fileLayout = new FileLayout();
        fileLayout.addColumns(columns);
        return fileLayout;
    }
}
