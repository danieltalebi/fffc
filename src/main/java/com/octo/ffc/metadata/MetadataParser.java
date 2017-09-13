package com.octo.ffc.metadata;

import com.octo.ffc.columns.AbstractColumn;
import com.octo.ffc.columns.DateColumn;
import com.octo.ffc.columns.NumericColumn;
import com.octo.ffc.columns.StringColumn;
import com.octo.ffc.exceptions.ParserException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.List;

public class MetadataParser {

    public static final String SEPARATOR = ",";
    public static final String DATE = "DATE";
    public static final String STRING = "STRING";
    public static final String NUMERIC = "NUMERIC";

    public List<AbstractColumn> parse(List<String> columnMetadataList) throws ParserException {
        Validate.notEmpty(columnMetadataList);
        List<AbstractColumn> columnList = new ArrayList<>();
        for (String columnMetadata : columnMetadataList) {
            columnList.add(parse(columnMetadata));
        }
        return columnList;
    }

    public AbstractColumn parse(String columnMetadata) throws ParserException {
        Validate.notEmpty(columnMetadata, "The column meta data cannot be null or empty");
        String[] data = columnMetadata.split(SEPARATOR);
        if (data.length != 3) {
            throw new ParserException(new StringBuilder("Invalid format for: ")
                    .append(columnMetadata).append(" The format should be NAME,LENGTH,TYPE").toString());
        }
        try {
            String columnName = data[0];
            int length = Integer.valueOf(data[1]);
            String columnType = data[2];
            return createColumn(columnName, length, columnType);
        } catch (Exception e) {
            throw new ParserException("There was a problem creating creating the column for input: " + columnMetadata);
        }

    }

    private AbstractColumn createColumn(String name, int length, String type) throws ParserException {
        String columnType = type.toUpperCase().trim();
        if (DATE.equals(columnType)) {
            return new DateColumn(name);
        } else if (STRING.equals(columnType)) {
            return new StringColumn(name, length);
        } else if (NUMERIC.equals(columnType)) {
            return new NumericColumn(name, length);
        } else throw new ParserException("Invalid column type for: " + type);
    }
}
