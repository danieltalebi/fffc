package com.octo.ffc.file.writer;

import com.octo.ffc.exceptions.WriterException;

import java.util.List;

public interface FileWriter {

    void write(String[] rows) throws WriterException;
    void write(List<String[]> rows) throws WriterException;
}
