package com.octo.ffc.file.reader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FakeFileReaderImpl implements FileReader {

    public static final int ONE_THOUSAND_LINES = 1000;
    private List<String> fakeFile;
    private int currentPosition;

    public FakeFileReaderImpl() {
        fakeFile = createFakeFile();
        currentPosition = 0;
    }

    @Override
    public List<String> readLines() {
        List<String> linesRead = new ArrayList<>();
        int maxPosition = determineMaxPosition();
        System.out.println(LocalDateTime.now() + " Reading lines at position " + currentPosition);
        for (int i = currentPosition; i < maxPosition; i++) {
            linesRead.add(fakeFile.get(i));
        }
        currentPosition = maxPosition;
        return linesRead;
    }

    private int determineMaxPosition() {
        return currentPosition + ONE_THOUSAND_LINES < fakeFile.size() ? currentPosition + ONE_THOUSAND_LINES : fakeFile.size();
    }

    private List<String> createFakeFile() {
        List<String> fakeLines = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            fakeLines.add("22Smith               John           1987-01-29");
        }
        return fakeLines;
    }
}
