package com.octo.ffc.columns;

public class StringColumn extends AbstractColumn {

    public StringColumn(String columnName, int length) {
        super(columnName, length);
    }

    @Override
    public String transformValue(String data) {
        return data.trim();
    }
}
