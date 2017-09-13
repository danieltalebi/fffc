package com.octo.ffc;

public class Main {

    public static void main(String[] args) throws Exception {
        TransformerController transformerController = new TransformerController();
        transformerController.transformToCsv("metadata.txt", "data.txt",
                "output.csv", ';', "\n");
    }
}
