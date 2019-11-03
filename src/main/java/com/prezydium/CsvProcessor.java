package com.prezydium;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvProcessor {


    public static List<String[]> readAll(String inputFilePath) throws Exception {
        Reader reader = Files.newBufferedReader(Paths.get(inputFilePath));
        return readAll(reader);
    }

    public static void csvWriteAll(List<String[]> stringArray, String fileName) throws Exception {
        CSVWriter writer = new CSVWriter(new FileWriter(fileName));
        writer.writeAll(stringArray);
        writer.close();
    }

    private static List<String[]> readAll(Reader reader) throws Exception {
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> list = csvReader.readAll();
        list.remove(0);
        reader.close();
        csvReader.close();
        return list;
    }
}
