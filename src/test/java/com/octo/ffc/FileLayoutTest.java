package com.octo.ffc;

import com.octo.ffc.columns.AbstractColumn;
import com.octo.ffc.columns.DateColumn;
import com.octo.ffc.columns.NumericColumn;
import com.octo.ffc.columns.StringColumn;
import com.octo.ffc.layouts.FileLayout;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class FileLayoutTest {

    private FileLayout fileLayout;

    @Before
    public void setUp() throws Exception {
        this.fileLayout = new FileLayout();
    }

    @Test
    public void addColumn() {
        AbstractColumn column1 = new NumericColumn("Age", 2);
        AbstractColumn column2 = new StringColumn("Last Name", 50);
        AbstractColumn column3 = new NumericColumn("Number of Birth", 10);

        fileLayout.addColumn(column1);
        fileLayout.addColumn(column2);
        fileLayout.addColumn(column3);

        assertEquals(3, fileLayout.getNumberOfColumns());
        assertArrayEquals(new String[]{"Age", "Last Name", "Number of Birth"}, fileLayout.getColumnHeaders());
        assertEquals(62, fileLayout.getMaxLengthPerLine());
    }


    @Test
    public void transform() {
        fileLayout.addColumn(new NumericColumn("Age", 2));
        fileLayout.addColumn(new StringColumn("Last Name", 20));
        fileLayout.addColumn(new StringColumn("First Name", 15));
        fileLayout.addColumn(new DateColumn("Date of Birth"));

        String[] result = fileLayout.convertToRow("22Smith               John           1987-01-29");
        assertArrayEquals(new String[]{"22", "Smith", "John", "29/01/1987"}, result);
    }

    @Test(expected = NullPointerException.class)
    public void addColumn_nullColumn() {
        fileLayout.addColumn(null);
    }

}
