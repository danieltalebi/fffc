package com.octo.ffc.columns;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NumericColumnTest {

    private NumericColumn numericColumn;

    @Before
    public void setUp() throws Exception {
        numericColumn = new NumericColumn("Weight", 5);
    }

    @Test
    public void transformValue_validNumbers() throws Exception {
        assertEquals("0.1234", numericColumn.transformValue("0.1234"));
        assertEquals("1.2345", numericColumn.transformValue("1.2345"));
        assertEquals("12.345", numericColumn.transformValue("12.345"));
        assertEquals("123.45", numericColumn.transformValue("123.45"));
        assertEquals("1234.5", numericColumn.transformValue("1234.5"));
        assertEquals("12345", numericColumn.transformValue("12345."));
        assertEquals("-1234.5", numericColumn.transformValue("-1234.5"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void transformValue_nullInput() {
        numericColumn.transformValue(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void transformValue_emptyInput() {
        numericColumn.transformValue(" ");
    }

}