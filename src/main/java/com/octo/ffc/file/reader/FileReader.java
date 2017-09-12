package com.octo.ffc.file.reader;

import com.octo.ffc.exceptions.ReaderException;

import java.util.List;

public interface FileReader {

    List<String> readLines() throws ReaderException;
}
