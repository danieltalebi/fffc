package com.octo.ffc.columns;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateColumn extends AbstractColumn {

    public static final int DATE_LENGTH = 10;
    private static final String INPUT_DATE_FORMAT = "yyyy-MM-dd";
    private static final String OUTPUT_DATE_FORMAT = "dd/MM/yyyy";

    private DateTimeFormatter inputFormatter;
    private DateTimeFormatter outputFormatter;


    public DateColumn(String columnName) {
        super(columnName, DATE_LENGTH);
        inputFormatter = DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT);
        outputFormatter = DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT);


    }

    @Override
    public String transformValue(String stringDate) {
        Validate.isTrue(StringUtils.isNotBlank(stringDate), "The input date cannot be null or empty");
        return reverseDate(stringDate);
    }

    private String reverseDate(String stringDate) {
        try {
            //Date parsedDate = inputDateFormatter.parse(stringDate);
            LocalDate date = LocalDate.parse(stringDate, inputFormatter);
            return outputFormatter.format(date);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("Unable to convert to date: " + stringDate, e);
        }
    }
}
