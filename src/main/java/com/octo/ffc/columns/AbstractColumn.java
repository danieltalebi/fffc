package com.octo.ffc.columns;

public abstract class AbstractColumn {

    private final String columnName;
    private final int length;

    public AbstractColumn(String columnName, int length) {
        this.columnName = columnName;
        this.length = length;
    }

    public abstract String transformValue(String data);

    public String getColumnName() {
        return columnName;
    }

    public int getLength() {
        return length;
    }

}
