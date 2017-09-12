package com.octo.ffc.columns;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.math.BigDecimal;

public class NumericColumn extends AbstractColumn {

    public NumericColumn(String columnName, int length) {
        super(columnName, length);
    }

    @Override
    public String transformValue(String data) {
        validateInput(data);
        return new BigDecimal(data).toPlainString();
    }

    private void validateInput(String data) {
        Validate.isTrue(StringUtils.isNotBlank(data), "The input cannot be null or empty");
        String replace = data.replaceAll("[.-]", "");
        if (replace.length() > super.getLength()) {
            throw new IllegalArgumentException("The given number exceeds the maximum number of digits");
        }
    }
}
