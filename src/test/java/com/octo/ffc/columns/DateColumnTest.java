package com.octo.ffc.columns;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DateColumnTest {

    private DateColumn dateColumn;

    @Before
    public void setUp() throws Exception {
        dateColumn = new DateColumn("Date of Birth");
    }

    @Test
    public void transformValue_validDate() {
        String convertedDate = dateColumn.transformValue("1987-01-29");
        assertEquals("29/01/1987", convertedDate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void transformValue_invalidDate() {
        dateColumn.transformValue("1987-13-29");
    }

    @Test(expected = IllegalArgumentException.class)
    public void transformValue_missingMonthDate() {
        dateColumn.transformValue("1987--29");
    }

    @Test(expected = IllegalArgumentException.class)
    public void transformValue_InvalidFormat() {
        dateColumn.transformValue("1987/01/29");
    }

    @Test(expected = IllegalArgumentException.class)
    public void transformValue_nullDate() {
        dateColumn.transformValue(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void transformValue_emptyDate() {
        dateColumn.transformValue(" ");
    }

}