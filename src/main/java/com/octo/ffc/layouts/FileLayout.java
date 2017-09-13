package com.octo.ffc.layouts;

import com.octo.ffc.columns.AbstractColumn;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

public class FileLayout {

    private List<AbstractColumn> columnList;
    private int maxLengthPerLine;

    public FileLayout() {
        this.columnList = new ArrayList<>();
        this.maxLengthPerLine = 0;
    }

    public void addColumns(List<AbstractColumn> columnList) {
        notEmpty(columnList, "The List of Columns cannot be null or empty");
        this.columnList.addAll(columnList);
        for (AbstractColumn column : columnList) {
            maxLengthPerLine += column.getLength();
        }
    }

    public void addColumn(AbstractColumn column) {
        notNull(column, "The Column to be added cannot be null");
        columnList.add(column);
        maxLengthPerLine += column.getLength();
    }

    public String[] getColumnHeaders() {
        String[] result = new String[columnList.size()];
        for(int i=0; i< columnList.size(); i++) {
            result[i] = columnList.get(i).getColumnName();
        }
        return result;
    }

    public int getNumberOfColumns() {
        return columnList.size();
    }

    public int getMaxLengthPerLine() {
        return maxLengthPerLine;
    }

    public String[] convertToRow(String rawData) {

        if (rawData.replaceAll("[.]", "").length() > maxLengthPerLine) {
            throw new IllegalArgumentException("Row max size exceeded for input " + rawData);
        }
        String[] values = new String[columnList.size()];
        int starting = 0;
        int ending;
        AbstractColumn column;
        for (int i = 0; i < columnList.size(); i++) {
            column = columnList.get(i);
            ending = starting + column.getLength();
            values[i] = column.transformValue(rawData.substring(starting, ending));
            starting = ending;
        }
        return values;
    }
}