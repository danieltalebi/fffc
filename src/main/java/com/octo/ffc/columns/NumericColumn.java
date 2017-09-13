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
        data = data.trim();
        validateInput(data);
        return new BigDecimal(data).toPlainString();
    }

    private void validateInput(String data) {
        Validate.isTrue(StringUtils.isNotBlank(data), "The input cannot be null or empty");
        String unsignedInteger = data.replaceAll("[.-]", "");
        if (unsignedInteger.length() > super.getLength()) {
            throw new IllegalArgumentException("The given number exceeds the maximum number of digits. Input: "+ data);
        }
        if (!StringUtils.isNumeric(unsignedInteger)) {
            throw new IllegalArgumentException("The given input is not numeric: "+ data);
        }

    }
}
