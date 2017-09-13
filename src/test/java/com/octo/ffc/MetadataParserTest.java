package com.octo.ffc;

import com.octo.ffc.columns.AbstractColumn;
import com.octo.ffc.columns.DateColumn;
import com.octo.ffc.columns.NumericColumn;
import com.octo.ffc.columns.StringColumn;
import com.octo.ffc.exceptions.ParserException;
import com.octo.ffc.metadata.MetadataParser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MetadataParserTest {

    private MetadataParser metadataParser;

    @Before
    public void setUp() throws Exception {
        metadataParser = new MetadataParser();
    }

    @Test
    public void match_dateColumn() throws ParserException {
        AbstractColumn column = metadataParser.parse("Birth date,10,date");
        assertTrue(column instanceof DateColumn);
        assertEquals("Birth date", column.getColumnName());
        assertEquals(10, column.getLength());
    }

    @Test
    public void match_stringColumn() throws ParserException {
        AbstractColumn column = metadataParser.parse("Last Name,50,string");

        assertTrue(column instanceof StringColumn);
        assertEquals("Last Name", column.getColumnName());
        assertEquals(50, column.getLength());
    }

    @Test
    public void match_numericColumn() throws ParserException {
        AbstractColumn column = metadataParser.parse("Average Age,2,numeric");

        assertTrue(column instanceof NumericColumn);
        assertEquals("Average Age", column.getColumnName());
        assertEquals(2, column.getLength());
    }

    @Test(expected = NullPointerException.class)
    public void match_nullMetadata() throws ParserException {
        String nullInput = null;
        metadataParser.parse(nullInput);
    }

    @Test(expected = ParserException.class)
    public void match_emptyMetadata() throws ParserException {
        metadataParser.parse(" ");
    }

    @Test(expected = ParserException.class)
    public void match_invalidMetadataFormat() throws ParserException {
        metadataParser.parse("Name,10");
    }

    @Test(expected = ParserException.class)
    public void match_invalidColumnType() throws ParserException {
        metadataParser.parse("Age,2,float");
    }
}
